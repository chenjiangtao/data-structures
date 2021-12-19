package chapter04.array;

import java.io.IOException;
import java.util.Scanner;


public class SparseCrossingMatrix<T> {
	private int row;
	private int column;
	private int nonZeroElementCount;
	private Object[] rowColumnHeads;

	public SparseCrossingMatrix(int nonZeroElementCount, int row, int column) {
		this.nonZeroElementCount = nonZeroElementCount;
		this.row = row;
		this.column = column;
		if (row > column) {
			this.rowColumnHeads = new Object[row];
		} else {
			this.rowColumnHeads = new Object[column];
		}
		for (int i = 0; i < this.rowColumnHeads.length; i++) {
			this.rowColumnHeads[i] = new SparseCrossingMatrixNode<Integer>();
			((SparseCrossingMatrixNode<T>) this.rowColumnHeads[i]).setNext(null);
			((SparseCrossingMatrixNode<T>) this.rowColumnHeads[i]).setRight(null);
			((SparseCrossingMatrixNode<T>) this.rowColumnHeads[i]).setDown(null);
		}
	}

	public SparseCrossingMatrixNode<T> getElement(int row, int column) {
		SparseCrossingMatrixNode<T> rowHead = (SparseCrossingMatrixNode<T>) this.rowColumnHeads[row];
		int columnIndex = 0;
		for (SparseCrossingMatrixNode<T> columnNode = ((SparseCrossingMatrixNode<T>) this.rowColumnHeads[column]).getDown(); columnNode != null; columnNode = columnNode.getRight()) {
			if (column == columnIndex) {
				return columnNode;
			}
		}
		return null;
	}

	public SparseCrossingMatrixNode<T> getRowColumnHead(int i) {
		if (i < 0 || i > this.rowColumnHeads.length) {
			System.out.print("下标越界，获取元素失败！");
			return null;
		}
		return (SparseCrossingMatrixNode<T>) this.rowColumnHeads[i];
	}


	public boolean setRowColumnHead(int i, SparseCrossingMatrixNode<T> node) {
		if (i < 0 || i > this.rowColumnHeads.length) {
			System.out.print("下标越界，获取元素失败！");
			return false;
		}
		this.rowColumnHeads[i] = node;
		return true;
	}

	public void addElement(SparseCrossingMatrixNode<T> node) {
		int rowIndex = node.getRow();
		int columnIndex = node.getColumn();

		SparseCrossingMatrixNode<T> rowColumnHead = this.getRowColumnHead(rowIndex - 1);
		if (rowColumnHead.getRight() == null) {
			node.setRight(rowColumnHead.getRight());
			rowColumnHead.setRight(node);
		} else {
			for (SparseCrossingMatrixNode<T> currentNode = rowColumnHead.getRight(); currentNode.getRight() != rowColumnHead && node.getColumn() > currentNode.getRight().getColumn(); currentNode = currentNode.getRight()) {
				node.setRight(currentNode.getRight());
				currentNode.setRight(node);
			}
		}

		SparseCrossingMatrixNode<T> columnHead = this.getRowColumnHead(columnIndex - 1);
		if (rowColumnHead.getDown() == columnHead) {
			node.setDown(columnHead.getDown());
			columnHead.setDown(node);
		} else {
			SparseCrossingMatrixNode<T> currentNode = columnHead.getDown();
			while (currentNode.getDown() != columnHead && node.getRow() > currentNode.getRight().getRow()) {
				node.setDown(currentNode.getDown());
				currentNode.setDown(node);
				currentNode = currentNode.getDown();
			}
		}
	}

	public void addElement(int rowIndex, int columnIndex, T value) {
		SparseCrossingMatrixNode<T> node = new SparseCrossingMatrixNode<T>(rowIndex, columnIndex, value);
		this.addElement(node);
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入非零元素个数：");
		int nonZeroElementCount = scanner.nextInt();
		System.out.print("请输入矩阵行数：");
		int row = scanner.nextInt();
		System.out.print("请输入矩阵列数：");
		int column = scanner.nextInt();


		SparseCrossingMatrix<Integer> matrix = new SparseCrossingMatrix<Integer>(nonZeroElementCount, row, column);


		for (int i = 0; i < nonZeroElementCount; i++) {
			System.out.print("请输入行号：");
			int rowIndex = scanner.nextInt();
			System.out.print("请输入列号：");
			int columnIndex = scanner.nextInt();
			System.out.print("请输入数值：");
			int value = scanner.nextInt();

			matrix.addElement(rowIndex,columnIndex,value);

		}
	}
}
