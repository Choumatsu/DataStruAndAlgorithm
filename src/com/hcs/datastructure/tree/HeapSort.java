package com.hcs.datastructure.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {4, 6, 8, 5, 9};
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr2 = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr2[i] = (int)(Math.random()*80000000);
        }
        double start = System.currentTimeMillis();
        heapSort(arr2);

        double end = System.currentTimeMillis();
        //System.out.println("排序后:"+ Arrays.toString(arr2));
        System.out.println("堆排序用时："+(end-start)/1000+"s");

    }

    public static void heapSort(int[] arr) {

        int temp = 0;

        //先将数组调整为大顶堆
        for (int i = arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        for (int j = arr.length-1;j>0;j--){
            temp = arr[j];//顶部元素（最大值）与末尾元素交换
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);//忽略最大的末尾元素，从顶部开始调整（除顶部外无变化）
        }
    }

    /**
     * 将数组调整成大顶堆
     *
     * @param arr    待调整数组
     * @param i      非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {

        int temp = arr[i];

        //k = k*2+1是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//左子节点小于右子节点
                k++;//指向右子节点
            }
            if (arr[k]>temp){//子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i=k;//i指向k，继续循环比较
            }else {
                break;
            }
        }
        //for循环结束后，已经将i为父节点的树的最大值，放在了最顶（局部）
        arr[i] = temp;

    }
}
