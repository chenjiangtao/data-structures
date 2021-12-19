package chapter06;

import chapter03.LinkedStack;
import chapter03.QueueArray;

import java.util.ArrayList;

public class BinaryTree<T> {

    private BinaryTreeNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    //算法6-1  创建一棵只有根结点的二叉树算法
    public BinaryTree(T x) {
        this.root = new BinaryTreeNode<T>(x);
    }

    //计算二叉树的高度
    public int Height() {
        return Height(root);
    }

    public int Height(BinaryTreeNode<T> node) {
        int hl = 0, hr = 0, h = 0;
        if (node == null)
            return h;
        else {
            hl = Height(node.getLeftChild());
            hr = Height(node.getRightChild());
            return (hl >= hr ? hl + 1 : hr + 1);
        }
    }

    //计算二叉树结点个数
    public int CountNode() {
        return CountNode(root);
    }

    public int CountNode(BinaryTreeNode<T> node) {
        int nl = 0, nr = 0, n = 0;
        if (node == null)
            return n;
        else {
            nl = CountNode(node.getLeftChild());
            nr = CountNode(node.getRightChild());
            return nl + nr + 1;
        }
    }

    //算法6-2  在指定左子结点处插入一个新结点。
    public BinaryTreeNode<T> insertAsLeftChild(BinaryTreeNode<T> parent, T data) {

        if (parent == null) {
            System.out.print("位置错！");
            return null;
        }

        BinaryTreeNode<T> node = new BinaryTreeNode<T>(data);

        if (parent.getLeftChild() == null) {
            parent.setLeftChild(node);
        } else {
            node.setLeftChild(parent.getLeftChild());
            parent.setLeftChild(node);
        }
        return node;
    }

    public BinaryTreeNode<T> insertAsRightChild(BinaryTreeNode<T> parent, T data) {

        if (parent == null) {
            System.out.print("位置错！");
            return null;
        }

        BinaryTreeNode<T> node = new BinaryTreeNode<T>(data);

        if (parent.getRightChild() == null) {
            parent.setRightChild(node);
        } else {
            node.setRightChild(parent.getRightChild());
            parent.setRightChild(node);
        }
        return node;
    }

    public void levelTraversal() {
        QueueArray<BinaryTreeNode<T>> myqueue = new QueueArray(10);
        myqueue.enQueue(root);
        while (!myqueue.isEmpty()) {
            BinaryTreeNode<T> node = myqueue.deQueue();
            if (node.getLeftChild() != null)
                myqueue.enQueue(node.getLeftChild());
            if (node.getRightChild() != null)
                myqueue.enQueue(node.getRightChild());
            System.out.print(node.getData());
        }
    }

    public void preorderTraversal() {
        this.preorderTraversal(this.root);
    }

    private void preorderTraversal(BinaryTreeNode<T> node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            preorderTraversal(node.getLeftChild());
            preorderTraversal(node.getRightChild());
        }
    }

    public void inorderTraversal() {
        this.inorderTraversal(this.root);
    }

    private void inorderTraversal(BinaryTreeNode<T> node) {
        if (node != null) {
            inorderTraversal(node.getLeftChild());
            System.out.print(node.getData());
            inorderTraversal(node.getRightChild());
        }
    }

    public void postorderTraversal() {
        this.postorderTraversal(this.root);
    }

    private void postorderTraversal(BinaryTreeNode<T> node) {
        if (node != null) {
            postorderTraversal(node.getLeftChild());
            postorderTraversal(node.getRightChild());
            System.out.print(node.getData());
        }
    }

    public void nonrecursionPreorderTraversal() {
        LinkedStack<BinaryTreeNode<T>> stack = new LinkedStack<BinaryTreeNode<T>>();
        BinaryTreeNode<T> p = root;
        do {
            while (p != null) {
                System.out.print(p.getData());
                if (p.getRightChild() != null) {
                    stack.push(p.getRightChild());
                }
                p = p.getLeftChild();
            }
            if (!stack.isEmpty()) {
                p = stack.getTop();
                stack.pop();
            }
        } while (!stack.isEmpty() || p != null);
    }

    public void nonrecursionInorderTraversal() {
        LinkedStack<BinaryTreeNode<T>> stack = new LinkedStack<BinaryTreeNode<T>>();
        BinaryTreeNode<T> p = root;
        do {
            while (p != null) {
                stack.push(p);
                p = p.getLeftChild();
            }
            if (!stack.isEmpty()) {
                // System.out.print(stack.getSize());
                p = stack.getTop();
                stack.pop();
                System.out.print(p.getData());
                p = p.getRightChild();
            }
        } while (!stack.isEmpty() || p != null);
    }

    public void nonrecursionPostorderTraversal() {
        LinkedStack<BinaryTreeNode<T>> nodeStack = new LinkedStack<BinaryTreeNode<T>>();
        LinkedStack<Integer> countStack = new LinkedStack<Integer>();
        BinaryTreeNode<T> p = root;
        do {
            while (p != null) {
                nodeStack.push(p);
                countStack.push(0);
                p = p.getLeftChild();
            }
            if (!nodeStack.isEmpty()) {
                int count = countStack.getTop();
                countStack.pop();
                p = nodeStack.getTop();
                nodeStack.pop();
                if (count == 0) {
                    nodeStack.push(p);
                    countStack.push(1);
                    p = p.getRightChild();
                } else {
                    System.out.print(p.getData());
                    p = null;
                }
            }
        } while (!nodeStack.isEmpty() || p != null);
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    //递归打印所有路径
    public void AllPath1(BinaryTreeNode<T> node, ArrayList<String> array, int pathlen) {
        if (node == null)
            return;
        array.add(pathlen, (String) node.getData());
        pathlen++;
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            int i = 0;
            for (; i < pathlen - 1; i++)
                System.out.print(array.get(i).toString() + "-->");
            System.out.println(array.get(i).toString());
        } else {
            AllPath1(node.getLeftChild(), array, pathlen);
            AllPath1(node.getRightChild(), array, pathlen);
        }

    }

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList();
        BinaryTree<String> binaryTree = new BinaryTree<String>("A");
        BinaryTreeNode<String> nodeB =
                binaryTree.insertAsLeftChild(binaryTree.root, "B");
        BinaryTreeNode<String> nodeC =
                binaryTree.insertAsRightChild(binaryTree.root, "C");
        BinaryTreeNode<String> nodeD = new BinaryTreeNode<String>("D");
        nodeB.setRightChild(nodeD);
        BinaryTreeNode<String> nodeE = binaryTree.insertAsLeftChild(nodeC, "E");
        BinaryTreeNode<String> nodeF = binaryTree.insertAsRightChild(nodeC, "F");
       /* System.out.println("--------递归--------");
        System.out.print("前序遍历：");
        binaryTree.preorderTraversal();
        System.out.println();
        System.out.print("中序遍历：");
        binaryTree.inorderTraversal();
        System.out.println();
        System.out.print("后序遍历：");
        binaryTree.postorderTraversal();
        System.out.println();+*/
        System.out.println("--------非递归--------");
        binaryTree.insertAsLeftChild(nodeD, "G");
        System.out.print("前序遍历：");
        binaryTree.nonrecursionPreorderTraversal();
        System.out.println();
        System.out.print("中序遍历：");
        binaryTree.nonrecursionInorderTraversal();
        System.out.println();
        System.out.print("后序遍历：");
        binaryTree.nonrecursionPostorderTraversal();
        System.out.print("层序遍历：");
        binaryTree.levelTraversal();
        System.out.print("所有叶子到根路径：");
        binaryTree.AllPath1(binaryTree.getRoot(), a, 0);
        return;
    }
}