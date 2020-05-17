package com.hcs.datastructure.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr2 = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr2[i] = (int) (Math.random() * 80000000);
        }
        double start = System.currentTimeMillis();

        quickSort(arr2,0,arr2.length-1);
        double end = System.currentTimeMillis();
        //System.out.println("排序后:" + Arrays.toString(arr2));
        System.out.println("快速用时：" + (end - start) / 1000 + "s");
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        //中间值
        int pivot = arr[(left + right) / 2];
        int temp = 0;

        while (l < r) {
            //在左边找一个大于等于的值
            while (arr[l] < pivot) {
                l += 1;
            }
            //在右边找一个小于等于的值
            while (arr[r] > pivot) {
                r -= 1;
            }

            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换后，发现arr[l]==pivot值相等 --，前移
            if (arr[l] == pivot) {
                r--;
            }

            //如果交换后，发现arr[r]==pivot值相等 ++，后移
            if (arr[r] == pivot) {
                l++;
            }
        }

        if(l==r){//防止栈溢出
            l++;
            r--;
        }

        //向左递归
        if(left<r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right>l){
            quickSort(arr,l,right);
        }

    }
}
