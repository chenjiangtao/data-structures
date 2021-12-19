package chapter02;

public class DoubleLinkedListNode<T> {
    private T data;
    private DoubleLinkedListNode<T> next;
    private DoubleLinkedListNode<T> prior;

    public DoubleLinkedListNode<T> getPrior() {
        return prior;
    }

    public void setPrior(DoubleLinkedListNode<T> prior) {
        this.prior = prior;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DoubleLinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleLinkedListNode<T> next) {
        this.next = next;
    }

}
