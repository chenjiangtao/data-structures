package chapter07;

import java.util.Scanner;

import chapter03.QueueArray;
import chapter03.LinkedStack;

public class GraphList<T> {
    public VertexList<T>[] vertexes;
    public int edgeCount;

    public GraphList() {

    }


    public GraphList(int vertexCount, int edgeCount) {
        this.edgeCount = edgeCount;
        this.vertexes = new VertexList[vertexCount];
    }

    public VertexList<T> getVetex(int i) {
        if (i < 0 || i < this.vertexes.length) {
            System.out.println("索引越界！");
        }
        return this.vertexes[i];
    }


    public GraphList<String> createFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入顶点数：");
        int vertexCount = scanner.nextInt();
        System.out.print("请输入边数：");
        int edgeCount = scanner.nextInt();

        GraphList<String> graph = new GraphList<String>(vertexCount, edgeCount);
        for (int i = 0; i < vertexCount; i++) {
            graph.vertexes[i] = new VertexList<String>("v" + i, null);
        }

        EdgeListNode currentEdge = null;
        for (int i = 0; i < vertexCount; i++) {
            System.out.print("请输入顶点" + i + "为起点的邻接边终点标号(以-1结束)：");
            int vertexIndex = scanner.nextInt();
            while (vertexIndex != -1) {
                System.out.print("请输入<" + i + "," + vertexIndex + ">边的权值：");
                double weight = scanner.nextDouble();
                currentEdge = new EdgeListNode(vertexIndex, weight, null);
                currentEdge.next = graph.vertexes[i].firstEdege;
                graph.vertexes[i].firstEdege = currentEdge;
                System.out.print("请输入顶点" + i + "为起点的邻接边终点标号(以-1结束)：");
                vertexIndex = scanner.nextInt();
            }
            ;
        }
        return graph;
    }

    public void deepFirstSearch() {
        int vertexCount = this.vertexes.length;
        boolean[] isVisited = new boolean[vertexCount];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }
        for (int i = 0; i < vertexCount; i++) {
            if (!isVisited[i]) {
                DFS(i, isVisited);
            }
        }
    }


    private void DFS(int i, boolean[] isVisited) {
        System.out.println(this.vertexes[i].data);
        isVisited[i] = true;
        for (EdgeListNode currentEdge = this.vertexes[i].firstEdege; currentEdge != null; currentEdge = currentEdge.next) {
            if (!isVisited[currentEdge.vertexIndex]) {
                DFS(currentEdge.vertexIndex, isVisited);
            }
        }
    }

    public void DFS() {
        boolean[] isVisited = new boolean[this.vertexes.length];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        System.out.println(this.vertexes[0].data);
        isVisited[0] = true;
        stack.push(0);
        EdgeListNode currentEdge = this.vertexes[0].firstEdege;
        while (currentEdge != null || (!stack.isEmpty())) {
            if (currentEdge != null) {
                if (!isVisited[currentEdge.vertexIndex]) {
                    System.out.println(this.vertexes[currentEdge.vertexIndex].data);
                    isVisited[currentEdge.vertexIndex] = true;
                    stack.push(currentEdge.vertexIndex);
                    currentEdge = currentEdge.next;
                }
            } else {
                currentEdge = this.vertexes[stack.pop()].firstEdege;
            }
        }
    }


    public void BreadthFirstSearch() {
        boolean[] isVisited = new boolean[this.vertexes.length];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }

        for (int i = 0; i < this.vertexes.length; i++) {
            if (!isVisited[i]) {
                BFS(i, isVisited);
            }
        }
    }


    private void BFS(int i, boolean[] isVisited) {

        System.out.println(this.vertexes[i].data);
        isVisited[i] = true;
        QueueArray<Integer> queue = new QueueArray<Integer>(1);
        queue.enQueue(i);

        EdgeListNode currentNode = null;
        while (!queue.isEmpty()) {
            currentNode = this.vertexes[queue.deQueue()].firstEdege;
            while (currentNode != null) {
                if (!isVisited[currentNode.vertexIndex]) {
                    System.out.println(this.vertexes[currentNode.vertexIndex].data);
                    isVisited[currentNode.vertexIndex] = true;
                    queue.enQueue(currentNode.vertexIndex);
                }
                currentNode = currentNode.next;
            }
        }
    }

    public void shortestPath(int startVertexIndex, int endVertexIndex) {
        boolean[] isVisited = new boolean[this.vertexes.length];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }
        for (int i = 0; i < this.vertexes.length; i++) {
            if (!isVisited[i]) {
                BFS(i, isVisited);
            }
        }

    }

    public void shortestBFS(int startVertexIndex, int endVertexIndex) {
        boolean[] isVisited = new boolean[this.vertexes.length];
        for (int i = 0; i < isVisited.length; i++) {
            isVisited[i] = false;
        }

        isVisited[startVertexIndex] = true;
        QueueArray<Integer> queue = new QueueArray<Integer>(1);
        QueueArray<Integer> shortestQueue = new QueueArray<Integer>(1);
        queue.enQueue(startVertexIndex);
        shortestQueue.enQueue(startVertexIndex);

        EdgeListNode currentNode = null;
        int currentVertexIndex = startVertexIndex;
        while (!queue.isEmpty()) {
            currentNode = this.vertexes[queue.deQueue()].firstEdege;
            double minWeight = Double.MAX_VALUE;
            int minWeightNodeIndex = -1;
            while (currentNode != null) {
                currentVertexIndex = currentNode.vertexIndex;
                if (currentVertexIndex == endVertexIndex) {
                    shortestQueue.enQueue(currentVertexIndex);
                    break;
                }
                if (!isVisited[currentVertexIndex]) {
                    isVisited[currentVertexIndex] = true;
                    queue.enQueue(currentVertexIndex);
                    if (currentNode.weight < minWeight) {
                        minWeight = currentNode.weight;
                        minWeightNodeIndex = currentVertexIndex;
                    }
                }

                currentNode = currentNode.next;
            }
            shortestQueue.enQueue(minWeightNodeIndex);
            if (currentVertexIndex == endVertexIndex) {
                break;
            }

        }
        while (!shortestQueue.isEmpty()) {
            System.out.println(shortestQueue.deQueue());
        }

    }

    public static void main(String[] args) {
        GraphList graph = new GraphList();

        return;
    }

}
