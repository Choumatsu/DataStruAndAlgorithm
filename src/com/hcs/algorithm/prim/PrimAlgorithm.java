package com.hcs.algorithm.prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int vertex = data.length;

        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };

        MGraph mGraph = new MGraph(vertex);

        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,vertex,data,weight);

        minTree.showGraph(mGraph);

        minTree.prim(mGraph,0);

    }

}

//创建最小生成树
class MinTree {
    //创建图的邻接矩阵

    /**
     * @param graph  传入的图
     * @param vertex 图的顶点
     * @param data   顶点的数据
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int vertex, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //prim算法，得到最小生成树

    /**
     *
     * @param graph 图
     * @param v 从哪一个顶点开始生成
     */
    public void prim(MGraph graph,int v){
        int[] visited = new int[graph.vertex];//标记节点是否被访问过

        visited[v] = 1;
        //记录两个顶点下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;

        //确定每一次生成的子图和哪一个节点最近
        for (int k = 1; k < graph.vertex; k++) {
            for (int i = 0; i < graph.vertex; i++) {//i被访问过的节点
                for (int j = 0; j < graph.vertex; j++) {//j未被访问过的节点
                    if(visited[i]==1&&visited[j]==0&&graph.weight[i][j]<minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("<"+graph.data[h1]+","+graph.data[h2]+">,权值："+minWeight);

            visited[h2] = 1;

            minWeight = 10000;
        }


    }
}

class MGraph {
    int vertex;//表示图的节点个数
    char[] data;//c存放节点数据
    int[][] weight;//存放边,就是邻接矩阵

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}
