package chapter03;

public class QueueArray<T> implements Queue<T> {

    private int capacity;
    private Object[] elements;
    private int front;
    private int rear;

    public QueueArray(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[this.capacity + 1];
        this.front = 0;
        this.rear = 0;
    }

    public static void main(String[] args) {
        java.util.Queue<Integer> queue;

    }

    @Override
    public void clear() {
        this.front = 0;
        this.rear = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.rear == this.front ? true : false;
    }

    @Override
    public boolean isFull() {
        return ((this.rear + 1) % this.capacity) == this.front ? true : false;
    }

    @Override
    public boolean enQueue(T e) {
        if ((rear + 1) % capacity == front)
            expandSpace();
        elements[rear] = e;
        rear = (rear + 1) % capacity;
        return true;
    }

    private void expandSpace() {
        Object[] a = new Object[elements.length * 2];
        int i = front;
        int j = 0;
        while (i != rear) {           //将从front 开始到rear 前一个存储单元的元素复制到新数组
            a[j++] = elements[i];
            i = (i + 1) % capacity;
        }
        elements = a;
        capacity = elements.length;
        front = 0;
        rear = j;            //设置新的队首、队尾指针
    }

    @Override
    public T deQueue() {
        if (rear == front) {
            System.out.printf("队列是空的，无法出队！");
            return null;      // 返回出队失败标志
        } else {
            T data = (T) elements[front];
            front = (front + 1) % capacity;
            return data;  // 返回出队成功标志
        }
    }

    @Override
    public T getFront() {
        if (rear == front) {
            System.out.printf("队列是空的，无法出队！");
            return null;      // 返回出队失败标志
        } else {
            T data = (T) elements[front];

            return data;  // 返回出队成功标志
        }
    }
}