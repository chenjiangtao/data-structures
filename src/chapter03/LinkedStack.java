package chapter03;

public class LinkedStack<T> implements Stack<T> {
    private LinkedStackNode<T> top;

    public LinkedStack() {
        this.top = new LinkedStackNode<T>();
        this.top.setData(null);
        this.top.setNext(null);
    }

    @Override
    public void clear() {
        this.top.setData(null);
        this.top.setNext(null);
    }

    @Override
    public boolean isEmpty() {

        return (this.top).getNext() == null ? true : false;
    }

    @Override
    public boolean isFull() {

        return false;
    }

    @Override
    public void push(T e) {
        LinkedStackNode<T> p = new LinkedStackNode<T>();
        p.setData(e);
        p.setNext(this.top);
        this.top = p;
    }

    @Override
    public int getSize() {
        LinkedStackNode<T> p = top;
        int count = 0;
        if (top == null) {
            return count;
        }
        while (p != null) {
            p = p.getNext();
            count++;
        }
        return count;
    }

    @Override
    public T pop() {
        if (this.top == null)        /* 判断栈是否为空 */ {
            System.out.printf("栈空，无法出栈！");
            return null;
        }
        T nodeData = top.getData();
        top = this.top.getNext();          /* top指向top的后继 */
        return nodeData;
    }

    @Override
    public T getTop() {
        if (this.top == null)        /* 判断栈是否为空 */ {
            System.out.printf("栈空，无法取栈顶！");
            return (null);
        }
        return this.top.getData();
    }

    public void Display() {
        LinkedStackNode<T> p = top.getNext();
        while (p != null) {
            System.out.print(p.getData() + "  ");
            p = p.getNext();
        }
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        int topNodeData = stack.getTop();
        stack.pop();
        stack.pop();
        return;
    }

}