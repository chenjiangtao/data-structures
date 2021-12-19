package chapter07;

/* 定义边结点结构 */
public class EdgeNode {
    public int adjacencyVetexIndex;
    public double weight;
    public EdgeNode next;

    public EdgeNode() {

    }

    public EdgeNode(int adjacencyVetexIndex, EdgeNode next) {
        this.adjacencyVetexIndex = adjacencyVetexIndex;
        this.next = next;
    }

    public int getAdjacencyVetexIndex() {
        return adjacencyVetexIndex;
    }

    public void setAdjacencyVetexIndex(int adjacencyVetexIndex) {
        this.adjacencyVetexIndex = adjacencyVetexIndex;
    }

    public EdgeNode getNext() {
        return next;
    }

    public void setNext(EdgeNode next) {
        this.next = next;
    }
}
