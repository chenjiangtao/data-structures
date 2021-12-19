package chapter08;

public class BinaryNode<T extends Comparable<T>, E> implements Comparable<BinaryNode<T, E>> {
    public T key;
    public E data;
    public BinaryNode<T, E> leftChild;
    public BinaryNode<T, E> rightChild;
    public BinaryNode<T, E> parent;


    public BinaryNode() {

    }

    public BinaryNode(T key, E data) {
        this.key = key;
        this.data = data;
    }


    @Override
    public int compareTo(BinaryNode<T, E> node) {
        return this.key.compareTo(node.key);
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public BinaryNode<T, E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryNode<T, E> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryNode<T, E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryNode<T, E> rightChild) {
        this.rightChild = rightChild;
    }

    public BinaryNode<T, E> getParent() {
        return parent;
    }

    public void setParent(BinaryNode<T, E> parent) {
        this.parent = parent;
    }


}
