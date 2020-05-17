package com.hcs.datastructure.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        //int[] arr = {3,9,-1,10,-2};

        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int)(Math.random()*8000000);
        }
        double start = System.currentTimeMillis();
        selectSort(arr2);
        double end = System.currentTimeMillis();
        System.out.println("排序后:"+ Arrays.toString(arr2));
        System.out.println("选择排序用时："+(end-start)/1000+"s");
    }

    public static void selectSort(int[] arr){
        int num = arr[0];
        int index = 0;
        for (int i = 0; i < arr.length-1; i++) {
            num = arr[i];
            index = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]<num){
                    num = arr[j];
                    index = j;
                }
            }
            if (i!=index){

                arr[index] = arr[i];
                arr[i] = num;
            }


        }
    }

}
