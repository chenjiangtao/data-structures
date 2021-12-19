package chapter07;

public class TopoNode<T> {
	public T data;
	public int inDegree;
	public EdgeListNode firstEdge;
	
	public TopoNode(T data,int inDegree,EdgeListNode firstEdge){
		this.data =data;
		this.inDegree = inDegree;
		this.firstEdge = firstEdge;
	}
}
