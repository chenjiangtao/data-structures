package chapter03;

public interface Queue<T> {

    void clear();

    boolean isEmpty();

    boolean isFull();

    boolean enQueue(T data);

    T deQueue();

    T getFront();

}
