package stream;

import function.*;
import iter.Iterator;
import util.Optional;

public interface Stream<T> {
    Pair<T> eval();

    class Pair<T> {
        private final T value;
        private final Stream<T> rest;

        public Pair(T value, Stream<T> rest) {
            this.value = value;
            this.rest = rest;
        }

        public T getValue() {
            return value;
        }

        public Stream<T> getRest() {
            return rest;
        }
    }

    default Optional<T> findFirst() {
        Pair<T> pair = eval();
        if (pair == null) return Optional.empty();
        return Optional.of(pair.getValue());
    }

    default void forEach(Consumer<? super T> action) {
        for (Pair<T> pair = eval(); pair != null; pair = pair.rest.eval()) {
            action.accept(pair.value);
        }
    }

    default Stream<T> filter(Predicate<? super T> p) {
        return () -> {
            Pair<T> pair = eval();
            if (pair == null) return null;
            if (p.test(pair.getValue())) {
                return new Pair<T>(pair.getValue(), pair.getRest().filter(p));
            } else return pair.getRest().filter(p).eval();
        };

    }

    default <S> Stream<S> map(Function<? super T, ? extends S> f) {
        return () -> {
            Pair<T> pair = eval();
            if (pair == null) return null;
            return new Pair<S>(f.apply(pair.getValue()), pair.getRest().map(f));
        };
    }

    static <T> Stream<T> empty() {
        return () -> null;
    }

    default Stream<T> concat(Stream<? extends T> after) {
        return () -> {
            Pair<T> pair = eval();
            if (pair == null) return (Pair<T>) after.eval();
            return new Pair<T>(pair.getValue(), pair.rest.concat(after));
        };
    }

    default Iterator<T> iterator() {
        return new Iterator<T>() {
            private Pair<T> pair = Stream.this.eval();

            @Override
            public boolean hasNext() {
                return pair != null;
            }

            @Override
            public T next() {
                T result = pair.getValue();
                pair = pair.rest.eval();
                return result;
            }
        };
    }

    default Optional<T> reduce(BinaryOperator<T> acc) {
        Iterator<T> it = iterator();
        if (!it.hasNext()) return Optional.empty();
        T res = it.next();
        while (it.hasNext()) {
            res = acc.apply(res, it.next());
        }
        return Optional.of(res);
    }

    default <U> U reduce(U identity, BiFunction<U, ? super T, U> acc, BinaryOperator<U> combiner) {
        Iterator<T> it = iterator();
        U res = identity;
        while (it.hasNext()) {
            res = acc.apply(res, it.next());
        }
        return res;
    }

    default <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> acc, BiConsumer<R, R> combiner) {
        Iterator<T> it = iterator();
        R res = supplier.get();
        while (it.hasNext()) {
            acc.accept(res, it.next());
        }
        return res;
    }


    static <T> Stream<T> flatten(Stream<Stream<T>> st) {
        return () -> {
            Pair<Stream<T>> pair = st.eval();
            if (pair == null) return null;
            if (pair.getValue() == null) return flatten(pair.getRest()).eval();
            Pair<T> p = pair.getValue().eval();
            if (p != null) return flatten(pair.getRest()).eval();
            return new Pair<>(p.getValue(), p.getRest().concat(flatten(pair.getRest())));
        };
    }
//    static <T> java.util.stream.Stream<T> flatArray(java.util.stream.Stream<T[]> st){
//        return st.flatMap(java.util.stream.Stream::of);
//    }

    static <T> Stream<T> of(T x) {
        return () -> new Pair<T>(x, empty());
    }

    @SafeVarargs
    static <T> Stream<T> of(T... args) {
        class State {
            int count = 0;

            Stream<T> of() {
                if (count == args.length) return empty();
                final T value = args[count++];
                return () -> new Pair<T>(value, of());
            }
        }
        return new State().of();
    }

}
