package com.hcs.algorithm.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品重量
        int[] val = {1500, 3000, 2000};//物品的价值

        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //记录放入商品情况
        int[][] path = new int[n+1][m+1];

        //创建二维数组 v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);

                    if(v[i - 1][j]<val[i - 1] + v[i - 1][j - w[i - 1]]){
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];

                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i - 1][j];
                    }

                }
            }
        }


        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //输出放入的商品
        int i = path.length-1;
        int j = path[0].length-1;
        while (i>0){
            if (path[i][j]==1){
                System.out.println("第"+i+"个商品放入背包");
                j -= w[i-1];
            }
        }
    }
}
