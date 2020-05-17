package com.hcs.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;//存储顶点
    private int[][] edges;//存储边
    private int numOfEdges;//边的个数

    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;

        String vertex[] = {"A", "B", "C", "D", "E"};

        Graph graph = new Graph(n);

        for (String value : vertex) {
            graph.insertVertex(value);
        }

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();

        System.out.println("深度");
        //graph.dfs();

        System.out.println();

        System.out.println("广度");
        graph.bfs();

    }

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];


    }

    //得到第一个领接节点的下标
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个领接节点的下标获取下一个领接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历
    private void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i) + " -> ");

        isVisited[i] = true;

        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs重载，遍历所有节点，并进行dfs
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //遍历所有节点，进行广度优先搜索
    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //对一个节点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u;//队列头结点对应的下标
        int w;
        //队列，节点访问的顺序
        LinkedList<Object> queue = new LinkedList<>();
        System.out.print(getValueByIndex(i) + " -> ");
        isVisited[i] = true;

        queue.addLast(i);

        while (!queue.isEmpty()) {
            u = (Integer) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " -> ");

                    isVisited[w] = true;

                    queue.addLast(w);
                }
                w = getNextNeighbor(u,w);
            }
        }
    }


    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }


    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * @param v1     第一个顶点对应的下标
     * @param v2     第二个顶点对应的下标
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
