package chapter07;

/* 定义顶点结点结构 */
public class VertexList<T>{
	public T data;
	public EdgeListNode firstEdege;

	public VertexList(){

	}

	public VertexList(T data,EdgeListNode firstEdege){
		this.data = data;
		this.firstEdege = firstEdege;
	}
}
