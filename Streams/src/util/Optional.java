package util;

import java.util.NoSuchElementException;

public class Optional<T> {
    private T value;

    private Optional(T value) {
        this.value = value;
    }

    public static <T> Optional<T> empty() {
        return new Optional<>(null);
    }

    public static <T> Optional<T> of(T x) {
        return new Optional<>(x);
    }

    public boolean isPresent() {
        return value != null;
    }

    public T get() {
        if (value != null) return value;
        throw new NoSuchElementException();
    }

    public T orElse(T x) {
        if (value == null) return x;
        else return value;
    }

}
