package chapter08;

public class AVLNode<T extends Comparable<T>, E> implements Comparable<AVLNode<T, E>> {
    public T key;
    public E data;
    public int height;
    public AVLNode<T, E> leftChild;
    public AVLNode<T, E> rightChild;

    public AVLNode() {

    }

    public AVLNode(T key, E data) {
        this.key = key;
        this.data = data;
    }

    @Override
    public int compareTo(AVLNode<T, E> node) {
        return this.key.compareTo(node.key);
    }

}
