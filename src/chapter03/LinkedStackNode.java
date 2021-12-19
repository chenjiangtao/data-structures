package chapter03;
public class LinkedStackNode<T> {
    private T data;
    private LinkedStackNode<T> next;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkedStackNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedStackNode<T> next) {
        this.next = next;
    }
}
