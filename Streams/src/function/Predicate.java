package function;

public interface Predicate<T> {
    boolean test(T t);
    default Predicate<T> and(Predicate<? super T> other){
//        return (T t) -> test(t) ? other.test(t) : false;
        return (T t) -> test(t) && other.test(t);
    }
}
