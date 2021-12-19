package chapter07;

/* 定义边结点结构 */
public class EdgeListNode {
    public int vertexIndex;
    public double weight;
    public EdgeListNode next;

    public EdgeListNode() {

    }

    public EdgeListNode(int vertexIndex, double weight, EdgeListNode next) {
        this.vertexIndex = vertexIndex;
        this.weight = weight;
        this.next = next;
    }

}
