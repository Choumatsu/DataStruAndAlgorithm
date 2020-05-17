package com.hcs.algorithm.kruskal;

import java.util.Arrays;

public class KruskalCase {

    private int edgeNum;
    private char[] vertex;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;


    public static void main(String[] args) {
        char[] vertax = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int[][] matrix = new int[][]{
                {  0,  12, INF, INF, INF,  16,  14},
                { 12,   0,  10, INF, INF,   7, INF},
                {INF,  10,   0,   3,   5,   6, INF},
                {INF, INF,   3,   0,   4, INF, INF},
                {INF, INF,   5,   4,   0,   2,   8},
                { 16,   7,   6, INF,   2,   0,   9},
                { 14, INF, INF, INF,   8,   9,   0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertax, matrix);

        kruskalCase.print();

        kruskalCase.kruskal();

        /*EData[] edges = kruskalCase.getEdges();

        System.out.println(Arrays.toString(edges));
        kruskalCase.sortEdge(edges);

        System.out.println(Arrays.toString(edges));*/

    }

    public KruskalCase(char[] vertex, int[][] matrix) {

        int vlen = vertex.length;

        this.vertex = new char[vlen];
        for (int i = 0; i < vlen; i++) this.vertex[i] = vertex[i];

        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //克鲁斯卡尔算法
    public void kruskal(){
        int index = 0;//最后结果数组的索引
        int[] ends = new int[edgeNum];//保存已有最小生成树中的每个的顶点在最小生成树中的终点

        //创建结果数组
        EData[] res = new EData[edgeNum];

        //获取图中所有边的集合
        EData[] edges = getEdges();

        //排序
        sortEdge(edges);

        //遍历edges
        for (int i = 0; i < edgeNum; i++) {
            //获取第i条边的第一个顶观点
            int p1 = getPosition(edges[i].start);//第一轮 p1=4
            //获取第i条边的第二个顶观点
            int p2 = getPosition(edges[i].end);//p2=5

            //获取p1在最小生成树的终点
            int m = getEnd(ends,p1);//m=4
            //获取p2在最小生成树的终点
            int n = getEnd(ends,p2);//n=5

            if(m!=n){//没有构成回路
                ends[m] = n;//设置m在最小生成树中的终点 <E,F>[0,0,0,0,5,0,0,0,0,0,0,0]
                res[index++] = edges[i];
            }
        }

        //统计并打印最小生成树，输出res
        System.out.println("最小生成树为："+Arrays.toString(res));
    }



    public void print() {
        System.out.println("邻接矩阵：");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%10d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序
    private void sortEdge(EData[] edges){
        for (int i = 0; i < edges.length-1; i++) {
            for (int j = 0; j < edges.length-1-i; j++) {
                if(edges[j].weight>edges[j+1].weight){
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     *
     * @param ch 顶点的值，‘A’，‘B’
     * @return 返回ch对应的下标，没有返回-1
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertex.length; i++) {
            if(vertex[i]==ch){
                return i;
            }


        }
        return -1;
    }

    /**
     * 获取如中的边，放到EData数组中，后面需要遍历数组
     * @return
     */
    private EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i+1; j <vertex.length ; j++) {
                if(matrix[i][j]!= INF){
                    edges[index++] = new EData(vertex[i],vertex[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点
     * @param ends 记录了各个顶点对应的终点是哪个，ends数组在遍历过程中逐步形成
     * @param i 传入顶点对应的下标
     * @return 小标为i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while(ends[i]!=0){
            i=ends[i];
        }
        return i;
    }
}

class EData {
    char start;//边的一个点
    char end;//边的另外一个点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                ", " + end +
                "> =" + weight +
                '}';
    }


}
