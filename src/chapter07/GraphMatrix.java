package chapter07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class GraphMatrix<T> {
	public Object[] vertexes;
	public double[][] matrix;
	private boolean isDirectedGraph;
	public int edgeCount;

	private void createGraph(Object[] vertexes, double[][] matrix, int edgeCount, boolean isDirectedGraph) {
		this.vertexes = vertexes;
		this.matrix = matrix;
		this.edgeCount = edgeCount;
		this.isDirectedGraph = isDirectedGraph;
	}

	private GraphMatrix() {

	}

	public GraphMatrix(T[] vertexes, double[][] matrix, boolean isDirectedGraph) {
		int edgeCount = this.calculateEdgeCount(matrix, isDirectedGraph);
		createGraph(vertexes, matrix, edgeCount, isDirectedGraph);
	}

	public GraphMatrix(double[][] matrix, boolean isDirectedGraph) {
		int edgeCount = this.calculateEdgeCount(matrix, isDirectedGraph);
		this.vertexes = new Object[matrix.length];
		for (int i = 0; i < vertexes.length; i++) {
			this.vertexes[i] = "v" + i;
		}
		createGraph(this.vertexes, matrix, edgeCount, isDirectedGraph);
	}

	public static GraphMatrix<String> createFromConsole() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("是否为有向图(true:有向图;false:无向图):");
		boolean isDirectedGraph = scanner.nextBoolean();
		System.out.print("请输入顶点数:");
		int vertexCount = scanner.nextInt();
		Object[] vertexes = new Object[vertexCount];
		for (int i = 0; i < vertexes.length; i++) {
			vertexes[i] = "v" + i;
		}
		double[][] matrix = new double[vertexCount][vertexCount];
		int edgeCount = 0;
		for (int row = 0; row < edgeCount; row++) {
			for (int col = 0; col < edgeCount; col++) {
				if (row != col) {
					if (!isDirectedGraph) {
						if (row < col) {
							continue;
						}
					}
					System.out.print("请输入<" + row + "," + col + ">边的权值(-1:表示不存在边)：");
					double weight = scanner.nextDouble();
					if (weight == -1) {
						weight = Double.POSITIVE_INFINITY;
					}
					matrix[row][col] = weight;
					edgeCount++;
					if (!isDirectedGraph) {
						matrix[col][row] = weight;
					}
				} else {
					matrix[row][col] = 0;
				}

			}
		}
		GraphMatrix<String> graph = new GraphMatrix();
		graph.createGraph(vertexes, matrix, edgeCount, isDirectedGraph);
		return graph;
	}

	private int calculateEdgeCount(double[][] matrix, boolean isDirectedGraph) {
		int edgeCount = 0;
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				if (matrix[row][col] != 0 && matrix[row][col] != Double.POSITIVE_INFINITY) {
					edgeCount++;
				}
			}
		}
		if (!isDirectedGraph) {
			edgeCount = edgeCount / 2;
		}
		return edgeCount;
	}

        public T getVertex(int index) {
		if (index < 0 || index >= this.vertexes.length) {
			System.out.print("索引越界！");
			return null;
		}
		return (T) this.vertexes[index];

	}

	/*
	 * 从lowestCost查找权值最小的元素，返回其在lowestCost数组中的下标值
	 */
	private static int findMin(double[] lowestCost) {
		double minCost = Double.POSITIVE_INFINITY;
		int nodeIndex = -1;
		for (int j = 0; j < lowestCost.length; j++) {
			if (lowestCost[j] < minCost && lowestCost[j] > 0) {
				minCost = lowestCost[j];
				nodeIndex = j;
			}
		}
		return nodeIndex;
	}

	/**
	 * 求图最小生成树的PRIM算法 基本思想： 假设N=(V,{E})是连通网，TE是N上的最想生成树T中的边集合。算法从U={u0}(u0属于V)，
	 * TE={}开始，重复执行下述操作： 在所有的顶点u属于U，v属于V-U的边（u，v）属于E中找到一条代价最小的边(u0，v0)，
	 * 并将(u0，v0)入集合TE； 同时，v0并入U，直至U=V为止。此时TE中必有n-1条边，则T=(V,{TE}) 为N的最小生成树。
	 * 
	 * @param matrix
	 *            图的矩阵（元素值为边的权值） 0：表示起点终点为同一节点；∞：表示两节点之间不存在边。
	 * @param startVertexIndex
	 *            开始节点的索引号
	 */
	public static void prim(double[][] matrix, int startVertexIndex) {
		// vertexCount：图的顶点数
		int vertexCount = matrix.length;
		/*
		 * lowestCost[i]：i号V-U集合中的节点与当前子树所有邻接边的最小权值 
		 * 0：表示i顶点已经属于U集合
		 * Double.POSITIVE_INFINITY:表示i节点与子树无邻接边
		 */
		double[] lowestCost = new double[vertexCount];
		/*
		 * parentNodeIndex：节点在最小生成树生长过程中的父节点
		 * -1:表示为生成树的根节点
		 */
		int[] parentNodeIndex = new int[vertexCount];
		
		 // 初始化parentNodeIndex与lowestCost数组元素
		 
		for (int i = 0; i < vertexCount; i++) {
			lowestCost[i] = matrix[startVertexIndex][i];
			parentNodeIndex[i] = startVertexIndex;
		}
		
		 //设置startVertexIndex为起点元素
		lowestCost[startVertexIndex] = 0;
		parentNodeIndex[startVertexIndex] = -1;
		
		 //搜索图中所有顶点，查找最小边构建最小生成树
		for (int i = 1; i < vertexCount; i++) {
			int minNodeIndex = findMin(lowestCost);
			if (minNodeIndex > 0) {
				lowestCost[minNodeIndex] = 0;
				// 更新当前最新生成树
				for (int j = 0; j < vertexCount; j++) {
					// 如果在U集合中存在到非U集合节点j的更小的边，则更新
					if (lowestCost[j] > 0 && matrix[minNodeIndex][j] < lowestCost[j]) {
						lowestCost[j] = matrix[minNodeIndex][j];
						parentNodeIndex[j] = minNodeIndex;
					}
				}
			} else {
				System.out.println("图不连通！");
				break;
			}
		}
		printPrimTree(parentNodeIndex);
	}

	static void printPrimTree(int[] array) {
		System.out.print("{");
		for (int i = 0; i < array.length; i++) {
			if(array[i]!=-1){
				System.out.print("(v"+array[i]+",v"+i+")");
			}
			
		}
		System.out.println("}");
	}
	
	public void testPrim() {
		double oo = Double.POSITIVE_INFINITY;
		double[][] matrix = { 
				{  0,  6,  5, oo, oo, oo, oo }, 
				{  6,  0,  9, oo, 13, oo, oo }, 
				{  5,  9,  0, 16, oo, 12, oo },
				{ oo, oo, 16,  0, 14,  7, oo }, 
				{ oo, 13, oo, 15,  0, oo,  8 }, 
				{ oo, oo, oo,  7, oo,  0,  4 },
				{ oo, oo, oo, oo,  8,  4,  0 } 
				};
		GraphMatrix graph = new GraphMatrix(matrix, false);
		graph.prim(0);
	}

	public void prim(int startVertex) {
		prim(this.matrix, startVertex);
	}

	/**
	 * 求最小树的Kruskal算法 算法思想：	 * 克鲁斯卡尔算法从另一个途径求网中的最小生成树。
	 * 假设联通网N=(V,{E})，则令	最小生成树的初始状态为只有n个顶点而无边的非连通图T=(V,{})
	 * 途中每个顶点自称一个连通分量。
	 * 在E中选择代价最小的边，若该边的顶点落在T中不同的连通分量上，则将此边加入到T中，
	 * 否则舍去此边而选择下一条最小的边。
	 * 以此类推，直至T中所有的顶点都在同一连通分量上为止。
	 * 
	 * @param vertexCount
	 *            图中的节点集合
	 * @param edges
	 *            图中边的集合
	 */
	public void kruskal(int vertexCount, Edge[] edges) {
		Arrays.sort(edges);// 将边按照权重w升序排序
		// 连通分量列表
		ArrayList<ArrayList<Integer>> connectedComponents = new ArrayList<ArrayList<Integer>>();
		// 初始化连通分量列表，每个顶点为一个独立的连通分量
		for (int i = 0; i < vertexCount; i++) {
			ArrayList<Integer> set = new ArrayList<Integer>();
			set.add(i);
			connectedComponents.add(set);
		}
		System.out.print("{");
		for (int i = 0; i < edges.length; i++) {
			int start = edges[i].start, end = edges[i].end;
			int startCompoment = -1, endComponent = -2;
			for (int j = 0; j < connectedComponents.size(); j++) {
				ArrayList<Integer> compoment = connectedComponents.get(j);
				if (compoment.contains(start)) {
					startCompoment = j;
				}

				if (compoment.contains(end)) {
					endComponent = j;
				}
			}
			if (startCompoment < 0 || endComponent < 0) {
				System.err.println("没有在子树中找到节点，错误");
			}

			if (startCompoment != endComponent) {
				System.out.print("(start:" + start + ",end:" + end + ",weight:" + edges[i].weight+"),");
				ArrayList<Integer> setj = connectedComponents.get(endComponent);
				ArrayList<Integer> seti = connectedComponents.get(startCompoment);
				seti.addAll(setj);
				//connectedComponents.add(seti);
				connectedComponents.remove(endComponent);
			} else {
				//System.out.println("他们在一棵子树中，不能输出start=" + start + "||end=" + end + "||w=" + edges[i].weight);

			}
		}
		System.out.println("}");

	}

	public void kruskal() {
		Edge[] edges = new Edge[this.edgeCount];
		int edgeIndex = 0;
		for (int row = 0; row < this.vertexes.length; row++) {
			for (int column = 0; column < this.vertexes.length; column++) {
				if(!this.isDirectedGraph){
					if(row<column){
						continue;
					}
				}
				if (this.matrix[row][column] != 0 && this.matrix[row][column] != Double.POSITIVE_INFINITY) {
					edges[edgeIndex] = new Edge(row, column, matrix[row][column]);
					edgeIndex++;
				}
			}
		}
		kruskal(this.vertexes.length, edges);
	}
	
	public void testKruscal(){
		double oo = Double.POSITIVE_INFINITY;
		double[][] matrix = { 
				{  0,  7, oo, oo,  9, oo}, 
				{  7,  0,  5,  1, oo,  2}, 
				{ oo,  5,  0, oo, oo,  6},
				{ oo,  1, oo,  0, oo,  2}, 
				{  9, oo, oo, oo,  0,  1}, 
				{ oo,  2,  6,  2,  1,  0}
				};
		GraphMatrix graph = new GraphMatrix(matrix, false);
		graph.kruskal();		
	}

	static void printDijkstra(int[] parent,double[] distant,int start,int end) {
			int p=parent[end];
			String path="v"+end;
			while(p!=-1){
				path = "v"+p+"-->"+path;
				p =parent[p];
			}
			System.out.println("v"+start+"->v"+end+"["+path+"]:"+distant[end]+";");
	}

	/*
	 * 计算图matrix中节点v0到其他所有节点的最短距离
	 */
	public static void dijkstra(double[][] matrix, int v0) {
		int vertexCount = matrix.length;
		// 记录顶点是否属于U集合
		boolean[] isInUSet = new boolean[vertexCount];
		/*
		 * 记录v0到节点i的最短距离
		 */
		double[] distant = new double[vertexCount];
		/*
		 * 记录v0到节点i的最短路径的父节点
		 */
		int[] parent = new int[vertexCount];
		//初始化
		for (int i = 0; i < vertexCount; i++) {
			isInUSet[i] = false;
			distant[i] = matrix[v0][i];
			parent[i] = v0;
		}
		// 初始化起点值
		isInUSet[v0] = true;
		distant[v0] = 0;
		parent[v0] = -1;

		for (int i = 0; i < vertexCount; i++) {
			double minCost = Double.POSITIVE_INFINITY;
			int minIndex = v0;
			for (int w = 0; w < vertexCount; w++) {
				if (!isInUSet[w]) {
					if (distant[w] < minCost) {
						minIndex = w;
						minCost = distant[w];
					}
				}
			}
			if (minCost < Double.POSITIVE_INFINITY) {
				isInUSet[minIndex] = true;

			} else {
				break;
			}
			
			for (int w = 0; w < vertexCount; w++) {
				if (!isInUSet[w] && (minCost + matrix[minIndex][w] < distant[w])) {
					distant[w] = minCost + matrix[minIndex][w];
					parent[w] = minIndex;
				}
			}
			printDijkstra(parent,distant,v0,i);
		}
		
	}
	
	public void testDijkstra(){
		double oo = Double.POSITIVE_INFINITY;
		double[][] matrix = { 
				{  0, oo,  1, oo, oo, oo, oo, oo, oo, oo}, 
				{  4,  0, 10, oo, oo, oo, oo, oo, oo, oo}, 
				{ oo, oo,  0,  3, oo,  9, oo, oo, oo, oo}, 
				{ oo,  1, oo,  0,  3, oo, oo, oo, oo, oo}, 
				{ oo, oo, oo, oo,  0,  1,  1,  3,  7, oo}, 
				{ oo, oo, oo, oo, oo,  0,  2, oo, oo,  2}, 
				{ oo, oo, oo, oo, oo, oo,  0, oo, oo, oo}, 
				{ oo, oo, oo, oo, oo, oo, oo,  0, oo, oo}, 
				{ oo, oo, oo, oo, oo, oo, oo, oo,  0, oo}, 
				{ oo, oo, oo, oo, oo, oo, oo, oo,  1,  0}
				};
		GraphMatrix.dijkstra(matrix, 0);		
	}

	public static void main(String[] args) {
		GraphMatrix<String> graph = GraphMatrix.createFromConsole();
	}
}
