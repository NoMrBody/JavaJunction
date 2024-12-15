package collections;

import stream.Stream;
import iter.Iterator;
import iter.Iterable;

public class List<T> implements Iterable<T> {
    public T info;
    public List<T> next;

    public List(T info) {
        this.info = info;
    }

    public List(T info, List<T> next) {
        this.info = info;
        this.next = next;
    }

    public void insert(T x) {
        this.next = new List<T>(x);
    }

    public void delete() {
        if (next != null)
            this.next = next.next;
    }

    public static boolean isEmpty(List lst) {
        return lst == null;
    }

    static class ListIterator<T> implements Iterator<T> {
        List<T> current;

        public ListIterator(List<T> lst) {
            this.current = lst;
        }


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T value = current.info;
            current = current.next;
            return value;
        }
    }

    public Iterator<T> iterator() {
        return new ListIterator<>(this);
    }

    public Stream<T> stream() {
        class State {
            Iterator<T> iter = iterator();

            Stream<T> stream() {
                return () -> iter.hasNext() ? new Stream.Pair<>(iter.next(), this.stream()) : null;
            }
        }
        return new State().stream();
    }

    public void printList(){
        Iterator<T> it = iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }

}
