package chapter06;


public class HuffmanTreeArrayNode<T>  implements Comparable<HuffmanTreeArrayNode<T>> {
	private double weight=0;
	private int leftChild =-1;
	private int rightChild=-1;
	private int parent=-1;
	private T data;

	public HuffmanTreeArrayNode(){

	}

	public HuffmanTreeArrayNode(T data,double weight){
		this.data = data;
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(int leftChild) {
		this.leftChild = leftChild;
	}

	public int getRightChild() {
		return rightChild;
	}

	public void setRightChild(int rightChild) {
		this.rightChild = rightChild;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public int compareTo(HuffmanTreeArrayNode<T> node) {

		if (this.weight < node.weight) {
			return -1;
		}

		if (this.weight > node.weight) {
			return 1;
		}

		return 0;
	}
}
