package com.hcs.datastructure.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int)(Math.random()*8000000);
        }
        double start = System.currentTimeMillis();
        insertSort(arr2);

        double end = System.currentTimeMillis();
        System.out.println("排序后:"+ Arrays.toString(arr2));
        System.out.println("插入排序用时："+(end-start)/1000+"s");
    }

    public static void insertSort(int[] arr){
        int insertVal = arr[0];
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i-1;

            while(insertIndex>=0 && insertVal<arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            arr[insertIndex+1] = insertVal;
        }
    }
}
