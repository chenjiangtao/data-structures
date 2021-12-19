package chapter07;

/* 定义顶点结点结构 */
public class VetexNode {
    public int index;
    public EdgeNode firstEdege;

    public VetexNode() {

    }

    public VetexNode(int index, EdgeNode firstEdege) {
        this.index = index;
        this.firstEdege = firstEdege;
    }


    public EdgeNode getFirstEdege() {
        return firstEdege;
    }

    public void setFirstEdege(EdgeNode firstEdege) {
        this.firstEdege = firstEdege;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
