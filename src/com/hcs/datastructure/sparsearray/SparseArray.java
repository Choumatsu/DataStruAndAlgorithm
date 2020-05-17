package com.hcs.datastructure.sparsearray;

import java.util.stream.IntStream;

public class SparseArray {
    public static void main(String[] args) {
        //创建二维数组11*11
        //0：没有棋子 1：黑 2：蓝
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        for (int[] row: chessArr1 ) {
            for (int data : row){
                System.out.print(data+" ");
            }
            System.out.println();
        }

        //二维数组转稀疏数组
        //1.得到非0个数
        int sum = 0;
        for (int[] value : chessArr1) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (value[j] != 0) {
                    sum++;
                }
            }
        }
        //2.创建稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        //赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        //遍历数组，存放非0值
        int count = 1;
        for (int i =0;i<chessArr1.length;i++){
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0){
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                    count++;

                }
            }
        }

        System.out.println();
        System.out.println("稀疏数组为：");
        for (int[] ints : sparseArr) {
            System.out.printf("%d %d %d\n", ints[0], ints[1], ints[2]);

        }

        //恢复原数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
//        for (int i = 0; i < chessArr2.length; i++) {
//            for (int j = 0; j < chessArr2[0].length; j++) {
//                chessArr2[i][j] = 0;
//            }
//        }

        IntStream.range(1, sparseArr.length).forEach(i -> chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2]);

        System.out.println("恢复原数组:");
        for (int[] row: chessArr2 ) {
            for (int data : row){
                System.out.print(data+" ");
            }
            System.out.println();
        }


    }
}
