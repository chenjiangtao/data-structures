package chapter02;

public class LinkedListNode<T> {
    private T data;
    private LinkedListNode<T> next;

    public LinkedListNode(){

    }

    public LinkedListNode(T data,LinkedListNode<T> next){
        this.setData(data);
        this.setNext(next);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

}