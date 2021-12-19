package chapter08;

public class AVLTree<T extends Comparable<T>, E> {
    private AVLNode<T, E> root;

    public AVLTree() {
        root = null;
    }

    public void insert(T key, E data) {
        root = insert(key, data, root);
    }

    public AVLNode<T, E> findMin() {
        return findMin(root);
    }

    public AVLNode<T, E> findMax() {
        return findMax(root);
    }

    public AVLNode<T, E> find(T key) {
        return find(key, root);
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    private AVLNode<T, E> insert(T key, E data, AVLNode<T, E> root) {
        if (root == null) {
            root = new AVLNode<T, E>(key, data);
        } else if (key.compareTo(root.key) < 0) {
            root.leftChild = insert(key, data, root.leftChild);
            if (height(root.leftChild) - height(root.rightChild) == 2) {
                if (key.compareTo(root.leftChild.key) < 0) {
                    root = rotateWithLeftLeft(root);
                } else {
                    root = rotateWithLeftRight(root);
                }
            }
        } else if (key.compareTo(root.key) > 0) {
            root.rightChild = insert(key, data, root.rightChild);
            if (height(root.rightChild) - height(root.leftChild) == 2) {
                if (key.compareTo(root.rightChild.key) > 0) {
                    root = rotateWithRightRight(root);
                } else {
                    root = rotateWithRightLeft(root);
                }
            }
        }
        root.height = max(height(root.leftChild), height(root.rightChild)) + 1;
        return root;
    }

    private int height(AVLNode<T, E> node) {
        return node == null ? -1 : node.height;
    }

    private static int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

    // 实现LL型旋转
    private AVLNode<T, E> rotateWithLeftLeft(AVLNode<T, E> nodeA) {
        AVLNode<T, E> nodeB = nodeA.leftChild;
        nodeA.leftChild = nodeB.rightChild;
        nodeB.rightChild = nodeA;
        nodeA.height = max(height(nodeA.leftChild), height(nodeA.rightChild)) + 1;
        nodeB.height = max(height(nodeB.leftChild), nodeA.height) + 1;
        return nodeB;
    }

    // 实现LR型旋转
    private AVLNode<T, E> rotateWithLeftRight(AVLNode<T, E> nodeA) {
        nodeA.leftChild = rotateWithRightRight(nodeA.leftChild);
        return rotateWithLeftLeft(nodeA);
    }

    // 实现RR型旋转
    private AVLNode<T, E> rotateWithRightRight(AVLNode<T, E> nodeA) {
        AVLNode<T, E> nodeB = nodeA.rightChild;
        nodeA.rightChild = nodeB.leftChild;
        nodeB.leftChild = nodeA;
        nodeA.height = max(height(nodeA.leftChild), height(nodeA.rightChild)) + 1;
        nodeB.height = max(height(nodeB.rightChild), nodeA.height) + 1;
        return nodeB;
    }

    // 实现RL型旋转
    private AVLNode<T, E> rotateWithRightLeft(AVLNode<T, E> nodeA) {
        nodeA.rightChild = rotateWithLeftLeft(nodeA.rightChild);
        return rotateWithRightRight(nodeA);
    }

    private AVLNode<T, E> findMin(AVLNode<T, E> root) {
        if (root == null) {
            return null;
        } else if (root.leftChild == null) {
            return root;
        }
        return findMin(root.leftChild);
    }

    private AVLNode<T, E> findMax(AVLNode<T, E> root) {
        if (root != null) {
            while (root.rightChild != null) {
                root = root.rightChild;
            }
        }

        return root;
    }

    private AVLNode<T, E> find(T key, AVLNode<T, E> root) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.key) < 0) {
            return find(key, root.leftChild);
        } else if (key.compareTo(root.key) > 0) {
            return find(key, root.rightChild);
        } else {
            return root; // Match
        }
    }

    private void printTree(AVLNode<T, E> root) {
        if (root != null) {
            printTree(root.leftChild);
            System.out.println("[key:" + root.key + ",data:" + root.data + "]");
            printTree(root.rightChild);
        }
    }

    public static void main(String[] args) {

    }
}
