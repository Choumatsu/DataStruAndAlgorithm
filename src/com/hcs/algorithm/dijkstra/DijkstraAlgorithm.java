package com.hcs.algorithm.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertax = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int[][] matrix = new int[vertax.length][vertax.length];

        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertax, matrix);

        graph.showGraph();

        graph.dijkstra(2);

        graph.showDijkstra();
    }
}

class Graph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex visitedVertex;//已经访问的顶点的集合

    public void showDijkstra(){
        visitedVertex.show();
    }

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void dijkstra(int index) {
        visitedVertex = new VisitedVertex(vertex.length, index);
        update(index);
        for (int j = 1; j < vertex.length; j++) {
            index = visitedVertex.updateArr();
            update(index);
        }

    }

    //更新index小标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index) {
        int len = 0;
        for (int j = 0; j < matrix.length; j++) {
            //出发顶点到index距离+index顶点到j顶点距离
            len = visitedVertex.getDis(index) + matrix[index][j];
            //如果j没有被访问过，且len小于出发顶点到j的距离，就更新
            if (!visitedVertex.in(j)&&len<visitedVertex.getDis(j)){
                visitedVertex.updatePre(j,index);
                visitedVertex.updateDis(j,len);
            }
        }
    }
}

class VisitedVertex {
    public int[] alreadyArr;//记录顶点是否被访问过

    public int[] preVisited;//每个下标对应的值的前一个顶点的下标，会动态更新

    public int[] dis;//记录出发顶点到其他所有顶点的距离


    /**
     * @param length 顶点的个数
     * @param index  出发顶点的下标
     */
    public VisitedVertex(int length, int index) {
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];

        Arrays.fill(dis, 65535);
        this.alreadyArr[index] = 1;//设置出发顶点被访问过
        this.dis[index] = 0;//出发定点为0
    }

    /**
     * 判断index顶点是否被访问过
     *
     * @param index
     * @return 访问过返回true
     */
    public boolean in(int index) {
        return alreadyArr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre的前驱为index的顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        preVisited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     *
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }


    //继续选择并返回新的节点
    public int updateArr(){
        int min = 65535,index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if(alreadyArr[i]==0&&dis[i]<min){
                min = dis[i];
                index = i;
            }
        }

        alreadyArr[index] = 1;
        return index;
    }

    //显示结果
    public void show(){
        System.out.println("===================");

        for (int i:alreadyArr) {
            System.out.print(i +" ");
        }
        System.out.println();
        for (int i:preVisited){
            System.out.print(i+" ");
        }
        System.out.println();
        for (int i:dis){
            System.out.print(i+" ");
        }
    }
}