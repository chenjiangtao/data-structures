package chapter04.array;

import java.io.IOException;


public class SparseMatrix<T> {
	private int row;
	private int column;
	private int nonZeroElementCount;
	private int size;
	private Object[] elements;

	public SparseMatrix(int nonZeroElementCount, int row, int column) {
		this.row = row;
		this.column = column;
		this.nonZeroElementCount = nonZeroElementCount;
		this.size = 0;
		this.elements = new Object[this.nonZeroElementCount];
		for (int i = 0; i < this.elements.length; i++) {
			this.elements[i] = null;
		}
	}

	public boolean addElement(SparseMatrixNode<T> element) {
		if (this.size >= this.nonZeroElementCount) {
			System.out.print("矩阵容量已满，添加失败！");
			return false;
		}
		this.elements[this.size] = element;
		this.size++;
		return true;
	}

	public boolean addElement(int i, SparseMatrixNode<T> element) {
		if (this.size >= this.nonZeroElementCount) {
			System.out.print("矩阵容量已满，添加失败！");
			return false;
		}
		if (i >= this.nonZeroElementCount) {
			System.out.print("索引位置超出矩阵容量，添加失败！");
			return false;
		}
		this.elements[i] = element;
		this.size++;
		return true;
	}


	public boolean addElement(SparseMatrixNode<T>[] elements) {
		if (elements.length > this.nonZeroElementCount) {
			System.out.print("元素数量超过矩阵容量，添加失败！");
			return false;
		}
		for (int i = 0; i < elements.length; i++) {
			this.elements[i] = elements[i];
		}
		this.size = elements.length;
		return true;
	}

	public SparseMatrixNode<T> getElement(int i) {
		if (i < 0 || i > this.elements.length) {
			System.out.print("下标越界，获取元素失败！");
			return null;
		}
		return (SparseMatrixNode<T>) this.elements[i];
	}

	public void display() {
		System.out.println("  row  column  value");
		System.out.println("--------------------------");
		for (int i = 0; i < this.size; i++) {
			System.out.printf("%4d%5d%8d", this.getElement(i).getRowIndex(), this.getElement(i).getColumnIndex(), this.getElement(i).getValue());
			System.out.println();
		}
	}

	public SparseMatrix<T> transpose() {
		SparseMatrix<T> matrix = new SparseMatrix<T>(this.nonZeroElementCount, this.row, this.column);
		if (matrix.nonZeroElementCount > 0) {
			int matrixIndex = 0;
			for (int columnIndex = 0; columnIndex < this.column; columnIndex++) {
				for (int eIndex = 0; eIndex < this.row; eIndex++) {
					if (this.getElement(eIndex).getColumnIndex() == columnIndex) {
						SparseMatrixNode<T> node = new SparseMatrixNode<T>();
						node.setRowIndex(this.getElement(eIndex).getColumnIndex());
						node.setColumnIndex(this.getElement(eIndex).getRowIndex());
						node.setValue(this.getElement(eIndex).getValue());
						matrix.addElement(node);
						matrixIndex++;
					}
				}
			}
		}
		return matrix;
	}

	public SparseMatrix<T> fastTranspose() {
		SparseMatrix<T> matrix = new SparseMatrix<T>(this.nonZeroElementCount, this.row, this.column);
		int[] rowSize = new int[this.column];
		int[] rowStart = new int[this.column];
		if (matrix.nonZeroElementCount > 0) {
			for (int i = 0; i < this.nonZeroElementCount; i++) {
				int j = this.getElement(i).getColumnIndex();
				rowSize[j] = rowSize[j] + 1;
			}
			rowStart[0] = 0;
			for (int i = 1; i < this.column; i++) {
				rowStart[i] = rowStart[i - 1] + rowSize[i - 1];
			}
			for (int i = 0; i < this.nonZeroElementCount; i++) {
				int j = this.getElement(i).getColumnIndex();
				int k = rowStart[j];
				SparseMatrixNode<T> node = new SparseMatrixNode<T>();
				node.setRowIndex(this.getElement(i).getColumnIndex());
				node.setColumnIndex(this.getElement(i).getRowIndex());
				node.setValue(this.getElement(i).getValue());
				matrix.addElement(k, node);
				rowStart[j] = rowStart[j] + 1;
			}

		}
		return matrix;
	}

	public static void main(String[] args) throws IOException {
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(6, 6, 5);
		matrix.addElement(new SparseMatrixNode<Integer>(0, 1, 8));
		matrix.addElement(new SparseMatrixNode<Integer>(1, 0, 7));
		matrix.addElement(new SparseMatrixNode<Integer>(2, 3, 12));
		matrix.addElement(new SparseMatrixNode<Integer>(4, 2, -9));
		matrix.addElement(new SparseMatrixNode<Integer>(4, 4, 4));
		matrix.addElement(new SparseMatrixNode<Integer>(5, 0, 7));
		matrix.display();

		SparseMatrix<Integer> tranposeMatrix = matrix.transpose();
		SparseMatrix<Integer> fastTranposeMatrix = matrix.fastTranspose();


		return;
	}
}
