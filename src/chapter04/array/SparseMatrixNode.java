package chapter04.array;

public class SparseMatrixNode<T> {
    private int rowIndex;
    private int columnIndex;
    private  T value;

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SparseMatrixNode(){

    }

    public SparseMatrixNode(int rowIndex,int columnIndex,T value){
        this.rowIndex=rowIndex;
        this.columnIndex = columnIndex;
        this.value = value;
    }
}
