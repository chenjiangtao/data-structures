package chapter06;

import chapter03.LinkedStack;


public class ThreadedBinaryTree<T> {
	private ThreadedBinaryTreeNode<T> rootNode;

	public ThreadedBinaryTree(){
		this.rootNode = new ThreadedBinaryTreeNode<T>();
		this.rootNode.setData(null);
		this.rootNode.setLeftChild(null);
		this.rootNode.setRightChild(null);
	}

	public ThreadedBinaryTreeNode<T> insertAsLeftChild(ThreadedBinaryTreeNode<T> parent, T data) {

		if (parent == null) {
			System.out.print("位置错！");
			return null;
		}

		ThreadedBinaryTreeNode<T> node = new ThreadedBinaryTreeNode<T>(data);

		if (parent.getLeftChild() == null) {
			parent.setLeftChild(node);
		} else {
			node.setLeftChild(parent.getLeftChild());
			parent.setLeftChild(node);
		}
		return node;
	}

	public ThreadedBinaryTreeNode<T> insertAsRightChild(ThreadedBinaryTreeNode<T> parent, T data) {

		if (parent == null) {
			System.out.print("位置错！");
			return null;
		}

		ThreadedBinaryTreeNode<T> node = new ThreadedBinaryTreeNode<T>(data);

		if (parent.getRightChild() == null) {
			parent.setRightChild(node);
		} else {
			node.setRightChild(parent.getRightChild());
			parent.setRightChild(node);
		}
		return node;
	}

	public ThreadedBinaryTreeNode<T> inorderThreaded(){
		LinkedStack<ThreadedBinaryTreeNode<T>> stack = new LinkedStack<ThreadedBinaryTreeNode<T>>();
		ThreadedBinaryTreeNode<T> priorNode = this.rootNode;
		ThreadedBinaryTreeNode<T> p = this.rootNode.getLeftChild();

		if(p==null){
			return priorNode;
		}
		do {
			while (p != null) {
				stack.push(p);
				priorNode = p;
				p = p.getLeftChild();
			}

			if (!stack.isEmpty()) {
				p = stack.getTop();
                                stack.pop();
			}
			if(priorNode!=null){
				if(priorNode.getRightChild()==null){
					priorNode.setRightChild(p);
					priorNode.setRightIsNext(true);
				}
				if(p.getLeftChild()==null){
					p.setLeftChild(priorNode);
					p.setLeftIsPrior(true);
				}

			}
			priorNode =p;
			p = p.getRightChild();
		} while (!stack.isEmpty() || p != null);
		return this.rootNode;
	}

	public void inorderTraversal() {
		ThreadedBinaryTreeNode<T> p = this.rootNode.getLeftChild();
		if(p==null){
			return;
		}
		while (p.getLeftChild() != null && !p.isLeftIsPrior()) {
			p = p.getLeftChild();
		}
		while (p!=this.rootNode){
			System.out.print(p.getData().toString());
			if(p.getRightChild()!=null && !p.isRightIsNext()){
				p = p.getRightChild();
				while(p.getLeftChild()!=null && !p.isLeftIsPrior()){
					p = p.getLeftChild();
				}
			}
			else {
				p = p.getRightChild();
			}
		}
	}
	public static void main(String[] args) {
		ThreadedBinaryTree<String> threadedBinaryTree = new ThreadedBinaryTree<String>();
		ThreadedBinaryTreeNode<String> nodeA =threadedBinaryTree.insertAsLeftChild(threadedBinaryTree.rootNode,"A");
		ThreadedBinaryTreeNode<String> nodeG =new ThreadedBinaryTreeNode<String>("G");
		ThreadedBinaryTreeNode<String> nodeD =new ThreadedBinaryTreeNode<String>("D");
		nodeD.setLeftChild(nodeG);
		ThreadedBinaryTreeNode<String> nodeB =threadedBinaryTree.insertAsLeftChild(nodeA, "B");
		nodeB.setRightChild(nodeD);
		ThreadedBinaryTreeNode<String> nodeC =threadedBinaryTree.insertAsRightChild(nodeA, "C");
		ThreadedBinaryTreeNode<String> nodeE =threadedBinaryTree.insertAsLeftChild(nodeC, "E");
		ThreadedBinaryTreeNode<String> nodeF =threadedBinaryTree.insertAsRightChild(nodeC, "F");

		threadedBinaryTree.inorderThreaded();
		threadedBinaryTree.inorderTraversal();


	}

}
