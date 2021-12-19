package chapter03;

public class SeqStack<T> implements Stack<T> {
    private int capacity;
    private int top;
    private Object elements[];

    public SeqStack(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.top = -1;
    }
    @Override
    public int getSize() {
        return top + 1;
    }

    @Override
    public void clear() {
        this.top = -1;
    }

    @Override
    public boolean isEmpty() {

        return (this.top == -1) ? true : false;
    }

    @Override
    public boolean isFull() {
        return (this.top == this.capacity) ? true : false;
    }

    @Override
    public void push(T e) {
        if (this.getSize() == this.capacity) {
            expandSpace();
        }
        this.top++;
        this.elements[this.top] = e;
    }

    private void expandSpace() {
        Object[] a = new Object[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            a[i] = elements[i];
        }
        elements = a;
    }

    @Override
    public T pop() {
        if (this.top == -1) {
            System.out.print("栈是空的，无法出栈！");
            return null;
        }
        this.top = this.top - 1;

        return (T) elements[top--];
    }

    @Override
    public T getTop() {
        if (this.top == -1) {
            System.out.print("栈是空的，无法取栈顶元素！");
            return null;
        }
        return (T) this.elements[this.top];
    }

    public static void main(String[] args) {
        SeqStack<Integer> stack = new SeqStack<Integer>(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.getTop();
        stack.pop();
        stack.pop();
        stack.getTop();

        return;
    }

}