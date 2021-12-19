package chapter06;

public class BinaryTreeNode<T> {
    private T data;  /* 定义数据域，T代表实际需要的类型 */
    private BinaryTreeNode<T> leftChild; /* 定义左孩子域，指向左孩子 对象*/
    private BinaryTreeNode<T> rightChild; /* 定义右孩子域，指向右孩子对象 */

    public BinaryTreeNode() {

    }

    public BinaryTreeNode(T data) {
        this.data = data;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }
}