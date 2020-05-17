package com.hcs.datastructure.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3,9,-1,10,-2};

        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int)(Math.random()*8000000);
        }
        double start = System.currentTimeMillis();
        bubbledSort(arr2);
        double end = System.currentTimeMillis();
        System.out.println("排序后:"+ Arrays.toString(arr2)+"\n冒泡排序用时："+(end-start)/1000+"s");
    }

    public static void bubbledSort(int[] arr){
        int temp = 0;
        boolean flag = false;
        for (int i = 1; i < arr.length; i++) {

            for (int j = 0; j < arr.length-i; j++) {
                if(arr[j]>arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag){
                break;
            }else {
                flag = false;
            }
        }

    }
}
