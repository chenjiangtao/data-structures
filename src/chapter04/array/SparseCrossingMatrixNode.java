package chapter04.array;


public class SparseCrossingMatrixNode<T> {
    private int row;
    private int column;
    private T value;
    private SparseCrossingMatrixNode<T> next;
    private SparseCrossingMatrixNode<T> right;
    private SparseCrossingMatrixNode<T> down;


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SparseCrossingMatrixNode<T> getNext() {
        return next;
    }

    public void setNext(SparseCrossingMatrixNode<T> next) {
        this.next = next;
    }

    public SparseCrossingMatrixNode<T> getRight() {
        return right;
    }

    public void setRight(SparseCrossingMatrixNode<T> right) {
        this.right = right;
    }

    public SparseCrossingMatrixNode<T> getDown() {
        return down;
    }

    public void setDown(SparseCrossingMatrixNode<T> down) {
        this.down = down;
    }

    public SparseCrossingMatrixNode() {

    }

    public SparseCrossingMatrixNode(int row, int column, T value) {
        this.setRow(row);
        this.setColumn(column);
        this.setValue(value);

    }

}
