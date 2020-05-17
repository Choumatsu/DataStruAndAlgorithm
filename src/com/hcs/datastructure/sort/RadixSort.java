package com.hcs.datastructure.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int) (Math.random() * 80000000);
        }
        double start = System.currentTimeMillis();

        radixSortt(arr2);
        double end = System.currentTimeMillis();
        //System.out.println("排序后:" + Arrays.toString(arr2));
        System.out.println("基数排序用时：" + (end - start) / 1000 + "s");

    }

    public static void radixSortt(int[] arr){

        int max = arr[0];
        for(int i = 1;i<arr.length;i++){
            if(arr[i]>max){
                max = arr[i];
            }
        }

        int maxLength = (max+"").length();

        //定义桶
        int[][] bucket = new int[10][arr.length];

        //记录桶中有多少个数据
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {

                int digitOfElement = arr[j] / n % 10;
                //放入对应的桶
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            //桶中数据放入原数组
            for(int k = 0;k < bucketElementCounts.length;k++){
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];

                }
                bucketElementCounts[k] = 0;
            }
        }



    }
}
