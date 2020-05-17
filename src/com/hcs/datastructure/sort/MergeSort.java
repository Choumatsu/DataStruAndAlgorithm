package com.hcs.datastructure.sort;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr2 = new int[8000000];
        int[] temp = new int[arr2.length];
        for (int i = 0; i < 8000000; i++) {
            arr2[i] = (int) (Math.random() * 80000000);
        }
        double start = System.currentTimeMillis();

        mergrSort(arr2,0,arr2.length-1,temp);
        double end = System.currentTimeMillis();
        //System.out.println("排序后:" + Arrays.toString(arr2));
        System.out.println("归并排序用时：" + (end - start) / 1000 + "s");
    }

    public static void mergrSort(int[] arr,int left,int right,int[] temp){
        if (left<right){
            int mid = (left+right)/2;
            //向左递归分解
            mergrSort(arr,left,mid,temp);
            //向右递归分解
            mergrSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);

        }
    }



    /**
     * //合并方法
     * @param arr 原始数组
     * @param left 左索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 临时数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i=left;//左边有序序列初始索引
        int j = mid+1;//右边有序序列初始索引
        int t = 0;//temp数组索引

        while(i<=mid&&j<=right){
            if(arr[i]<=arr[j]){
                temp[t] = arr[i];
                i++;
                t++;
            }else {
                temp[t] = arr[j];
                j++;
                t++;
            }

        }

        //将剩余的填充岛数组
        while (i<=mid){
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j<=right){
            temp[t] = arr[j];
            t++;
            j++;
        }

        //将temp拷贝到arr，并不是每次拷贝所有
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
