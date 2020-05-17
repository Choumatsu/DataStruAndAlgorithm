package com.hcs.datastructure.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr2 = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr2[i] = (int) (Math.random() * 80000000);
        }
        double start = System.currentTimeMillis();

        shellSort2(arr2);
        double end = System.currentTimeMillis();
        //System.out.println("排序后:" + Arrays.toString(arr2));
        System.out.println("希尔排序用时：" + (end - start) / 1000 + "s");

    }

    //交换法
    public static void shellSort(int[] arr) {
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {

            for (int i = gap; i < arr.length; i++) {//分组遍历各组元素 步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的元素，则交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //优化版，移位法
    public static void shellSort2(int[] arr) {
        //增量gap，并逐渐减小
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从gap个元素开始，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];

                while (j - gap >= 0 && temp < arr[j - gap]) {
                    //移动
                    arr[j] = arr[j - gap];
                    j -= gap;

                }
                arr[j] = temp;

            }
        }
    }
}
