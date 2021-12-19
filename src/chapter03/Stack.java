package chapter03;

public interface Stack<T> {
    void clear();

    boolean isEmpty();

    boolean isFull();

    int getSize();

    void push(T element);

    T pop();

    T getTop();
}
