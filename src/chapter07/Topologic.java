package chapter07;

import java.lang.reflect.Array;
import java.util.Stack;


public class Topologic<T> {
	
	public void sort(TopoNode<T>[] vertexes){
		Stack<TopoNode<T>> zeroDegreeStack = new Stack<TopoNode<T>>();
		int vertexCount = 0;
		for(int i=0;i<vertexes.length;i++){
			if(vertexes[i].inDegree==0){
				zeroDegreeStack.add(vertexes[i]);
			}
			while(!zeroDegreeStack.isEmpty()){
				TopoNode<T> element = zeroDegreeStack.pop();
				if(vertexCount>0){
					System.out.print("->");
				}
				System.out.print(element.data);
				vertexCount++;
				EdgeListNode node = element.firstEdge;
				while(node!=null){
					int j = node.vertexIndex;
					vertexes[j].inDegree--;
					if(vertexes[j].inDegree==0){
						zeroDegreeStack.add(vertexes[j]);
					}
					node = node.next;
				}
			}
			if(vertexCount==vertexes.length){
				System.out.println();
				break;
			}
		}
	}
	
	public void sortTest(){
//		Object[] vertexes =  (TopoNode<String>[]) Array.newInstance(TopoNode.class, 10);
//		vertexes[0]=new TopoNode<String>("C0",0,new EdgeListNode(2,0,new EdgeListNode(7,0,null)));
//		vertexes[1]=new TopoNode<String>("C1",0,new EdgeListNode(2,0,new EdgeListNode(4,0,null)));
//		vertexes[2]=new TopoNode<String>("C2",2,new EdgeListNode(3,0,null));
//		vertexes[3]=new TopoNode<String>("C3",2,new EdgeListNode(5,0,new EdgeListNode(6,0,new EdgeListNode(9,0,null))));
//		vertexes[4]=new TopoNode<String>("C4",1,new EdgeListNode(3,0,new EdgeListNode(5,0,null)));
//		vertexes[5]=new TopoNode<String>("C5",2,null);
//		vertexes[6]=new TopoNode<String>("C6",2,null);
//		vertexes[7]=new TopoNode<String>("C7",1,new EdgeListNode(8,0,null));
//		vertexes[8]=new TopoNode<String>("C8",1,new EdgeListNode(6,0,null));
//		vertexes[9]=new TopoNode<String>("C9",1,null);
//		
//		Topologic<String> topo = new Topologic<String>();
//		topo.sort((TopoNode<String>[])vertexes);
		
		Object[] vertexes =  (TopoNode<String>[]) Array.newInstance(TopoNode.class, 7);
		vertexes[1]=new TopoNode<String>("V0",0,new EdgeListNode(2,0,new EdgeListNode(3,0,null)));
		vertexes[0]=new TopoNode<String>("V1",0,new EdgeListNode(3,0,new EdgeListNode(4,0,null)));
		vertexes[2]=new TopoNode<String>("V2",1,new EdgeListNode(5,0,new EdgeListNode(6,0,null)));
		vertexes[3]=new TopoNode<String>("V3",2,new EdgeListNode(6,0,null));
		vertexes[4]=new TopoNode<String>("V4",1,new EdgeListNode(6,0,null));
		vertexes[5]=new TopoNode<String>("V5",1,null);
		vertexes[6]=new TopoNode<String>("V6",3,null);
		
		Topologic<String> topo = new Topologic<String>();
		topo.sort((TopoNode<String>[])vertexes);

	}
	
	public double[] earlistTimeOfVertex(TopoNode<T>[] vertexes){
		double[] ve = new double[vertexes.length];
		for(int i=0;i<vertexes.length;i++){
			EdgeListNode edgeNode =vertexes[i].firstEdge;
			while(edgeNode!=null){
				if(ve[i]+edgeNode.weight>ve[edgeNode.vertexIndex]){
					ve[edgeNode.vertexIndex] = ve[i]+edgeNode.weight;
				}	
				edgeNode = edgeNode.next;
			}
		}
		
		return ve;
	}
	
	public double[] lastestTimeOfVertex(TopoNode<T>[] vertexes,double lastv){
		double[] vl = new double[vertexes.length];
		
		for(int i=1;i<vertexes.length;i++){
			vl[i] = lastv;
		}
		for(int j= vertexes.length-1;j>0;j--){
			EdgeListNode edgeNode =vertexes[j].firstEdge;
			while(edgeNode!=null){
				double temp = vl[edgeNode.vertexIndex]-edgeNode.weight;
				if(temp<vl[j]){
					vl[j] = temp;
				}
				edgeNode = edgeNode.next;
			}
		}
		
		return vl;
		
	}
	
	public void compare(double[] ve,double[] vl){
		System.out.println("关键路径节点为：");
		for(int i=0;i<ve.length;i++){
			if(ve[i]==vl[i]){
				System.out.print("v"+i+",");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		TopoNode<String>[] vertexes =  (TopoNode<String>[]) Array.newInstance(TopoNode.class, 10);
		vertexes[0]=new TopoNode<String>("V0",0,new EdgeListNode(1,6,new EdgeListNode(2,2,new EdgeListNode(3,4,null))));
		vertexes[1]=new TopoNode<String>("V1",1,new EdgeListNode(4,3,null));
		vertexes[2]=new TopoNode<String>("V2",1,new EdgeListNode(4,5,null));
		vertexes[3]=new TopoNode<String>("V3",1,new EdgeListNode(5,8,null));
		vertexes[4]=new TopoNode<String>("V4",2,new EdgeListNode(6,7,new EdgeListNode(7,2,null)));
		vertexes[5]=new TopoNode<String>("V5",1,new EdgeListNode(7,6,null));
		vertexes[6]=new TopoNode<String>("V6",1,new EdgeListNode(8,4,null));
		vertexes[7]=new TopoNode<String>("V7",2,new EdgeListNode(9,7,null));
		vertexes[8]=new TopoNode<String>("V8",1,new EdgeListNode(9,5,null));
		vertexes[9]=new TopoNode<String>("V9",2,null);
		
		Topologic<String> topo = new Topologic<String>();
		double[] ve = topo.earlistTimeOfVertex(vertexes);
		double[] vl = topo.lastestTimeOfVertex(vertexes, ve[ve.length-1]);
		topo.compare(ve, vl);
		return;
	}

}
