package chapter06;

public class ThreadedBinaryTreeNode<T> {
    private T data;  /* 定义数据域，T代表实际需要的类型 */
    private ThreadedBinaryTreeNode<T> leftChild; /* 定义左孩子域，指向左孩子 对象*/
    private ThreadedBinaryTreeNode<T> rightChild; /* 定义右孩子域，指向右孩子对象 */
    private boolean leftIsPrior;
    private boolean rightIsNext;

    public ThreadedBinaryTreeNode() {

    }

    public ThreadedBinaryTreeNode(T data) {
        this.data = data;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ThreadedBinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(ThreadedBinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public ThreadedBinaryTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(ThreadedBinaryTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isLeftIsPrior() {
        return leftIsPrior;
    }

    public void setLeftIsPrior(boolean leftIsPrior) {
        this.leftIsPrior = leftIsPrior;
    }

    public boolean isRightIsNext() {
        return rightIsNext;
    }

    public void setRightIsNext(boolean rightIsNext) {
        this.rightIsNext = rightIsNext;
    }
}
