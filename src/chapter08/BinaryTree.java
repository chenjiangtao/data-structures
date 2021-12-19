package chapter08;

public class BinaryTree<T extends Comparable<T>, E> {
	private BinaryNode<T, E> root;

	public BinaryTree() {
		root = null;
	}

	public void insert(T key, E data) {
		root = insert(key, data, root);
	}

	public void insertNode(T key, E data) {
		if (root == null) {
			root = new BinaryNode<T, E>(key, data);
		}
		BinaryNode<T, E> currentNode = root;
		BinaryNode<T, E> insertedNode = root;
		while (currentNode != null) {
			if (key.compareTo(currentNode.key) == 0) {
				return;
			}
			insertedNode = currentNode;
			if (key.compareTo(currentNode.key) < 0) {
				currentNode = currentNode.leftChild;
			} else {
				currentNode = currentNode.rightChild;
			}
		}

		if (key.compareTo(insertedNode.key) < 0) {
			insertedNode.leftChild = new BinaryNode<T, E>(key, data);
		} else {
			insertedNode.rightChild = new BinaryNode<T, E>(key, data);
		}
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * 
	 * @param x
	 *            the item to remove.
	 */
	public void remove(T key) {
		root = remove(key, root);
	}

	public boolean removeNode(T key) {
		BinaryNode<T, E> node = find(key);
		if (node == null) {
			return false;
		}
		// 待删除结点为根结点root
		if (node.compareTo(root) == 0) {
			// 待删除结点为根叶子结点
			if (node.getLeftChild() == null && node.getRightChild() == null) {
				root = null;
				return true;
			}
			// 待删除结点有两个子结点
			if (node.leftChild != null && node.rightChild != null) {
				// 将右子树的最小结点替换当前待删除结点
				BinaryNode<T, E> minNode = findMin(node.getRightChild());
				node.setData(minNode.getData());
				// 删除最小结点（最小结点肯定为做左结点，且没有左子结点）
				// 删除的方法：最小结点的父结点的左子结点指向最小结点的右结点
				minNode.getParent().setLeftChild(minNode.getRightChild());
				return true;
			}
			// 待删除结点有一个子结点
			root = node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild();
			return true;
		}
		BinaryNode<T, E> nodeParent = node.getParent();
		// 待删除结点为叶子结点,根据key与父结点的key比较结果，决定其为左结点还是右结点
		if (node.getLeftChild() == null && node.getRightChild() == null) {
			if (node.getKey().compareTo(nodeParent.key) < 0) {
				nodeParent.setLeftChild(null);
			} else {
				nodeParent.setRightChild(null);
			}
			return true;
		}
		// 待删除结点有两个子结点
		if (node.leftChild != null && node.rightChild != null) {
			// 将右子树的最小结点替换当前待删除结点
			BinaryNode<T, E> minNode = findMin(node.getRightChild());
			node.setData(minNode.getData());
			// 删除最小结点（最小结点肯定为做左结点，且没有左子结点）
			// 删除的方法：最小结点的父结点的左子结点指向最小结点的右结点
			minNode.getParent().setLeftChild(minNode.getRightChild());
			return true;
		}
		// 待删结点只有一个子结点的情况，需记录待删结点的子结点
		BinaryNode<T, E> nodeChild = node.getRightChild();
		if (node.getLeftChild() != null) {
			nodeChild = node.getLeftChild();
		}
		// 判断待删结点为其父结点的左子结点还是右子结点
		if (node.getKey().compareTo(nodeParent.key) < 0) {
			nodeParent.setLeftChild(nodeChild);
		} else {
			nodeParent.setRightChild(nodeChild);
		}
		return true;
	}

	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 */
	public BinaryNode<T, E> findMin() {
		return findMin(root);
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item of null if empty.
	 */
	public BinaryNode<T, E> findMax() {
		return findMax(root);
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return the matching item or null if not found.
	 */
	public BinaryNode<T, E> find(T key) {
		return find(key, root);
	}

	public BinaryNode<T, E> search(T key, BinaryNode<T, E> root) {
		while (root != null) {
			int equalValue = key.compareTo(root.key);
			switch (equalValue) {
			case 0:
				return root;
			case -1:
				root = root.rightChild;
				break;
			case 1:
				root = root.leftChild;
				break;
			}
		}
		return null;
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	private BinaryNode<T, E> insert(T key, E data, BinaryNode<T, E> root) {
		if (root == null)
			root = new BinaryNode<T, E>(key, data);
		else if (key.compareTo(root.key) < 0)
			root.leftChild = insert(key, data, root.leftChild);
		else if (key.compareTo(root.key) > 0)
			root.rightChild = insert(key, data, root.rightChild);
		return root;
	}

	private BinaryNode<T, E> remove(T key, BinaryNode<T, E> root) {
		BinaryNode<T, E> node = this.find(key, root);

		if (node == null)
			return root;
		if (key.compareTo(root.key) < 0)
			root.leftChild = remove(key, root.leftChild);
		else if (key.compareTo(root.key) > 0)
			root.rightChild = remove(key, root.rightChild);
		else if (root.leftChild != null && root.rightChild != null) // Two
																	// children
		{
			BinaryNode<T, E> minNode = findMin(root.rightChild);
			root.key = minNode.key;
			root.data = minNode.data;
			root.rightChild = remove(root.key, root.rightChild);
		} else
			root = (root.leftChild != null) ? root.leftChild : root.rightChild;
		return root;
	}

	private BinaryNode<T, E> findMin(BinaryNode<T, E> root) {
		if (root == null)
			return null;
		else if (root.getLeftChild() == null)
			return root;
		return findMin(root.getLeftChild());
	}

	private BinaryNode<T, E> findMax(BinaryNode<T, E> root) {
		if (root != null)
			while (root.rightChild != null)
				root = root.rightChild;

		return root;
	}

	private BinaryNode<T, E> find(T key, BinaryNode<T, E> root) {
		if (root == null)
			return null;
		if (key.compareTo(root.key) < 0)
			return find(key, root.leftChild);
		else if (key.compareTo(root.key) > 0)
			return find(key, root.rightChild);
		else
			return root;
	}

	private void printTree(BinaryNode<T, E> root) {
		if (root != null) {
			printTree(root.getLeftChild());
			System.out.println("[key:" + root.key + ",data:" + root.data + "]");
			printTree(root.rightChild);
		}
	}

	// Test program
	public static void main(String[] args) {
		BinaryTree<Integer, Integer> tree = new BinaryTree<Integer, Integer>();
		tree.insert(38, 38);
		tree.insert(30, 30);
		tree.insert(41, 41);
		tree.insert(24, 24);
		tree.insert(35, 35);
		tree.insert(32, 32);
		tree.insert(56, 56);
		return;
	}
}
