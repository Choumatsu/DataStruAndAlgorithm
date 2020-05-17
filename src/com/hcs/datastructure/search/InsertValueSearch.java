package com.hcs.datastructure.search;

import java.util.Arrays;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        //System.out.println(Arrays.toString(arr));
        int index = inserValueSearch(arr, 0, arr.length-1, 15);

        System.out.println(index);

    }

    public static int inserValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("方法调用！");
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) {
            return inserValueSearch(arr, mid + 1, right, findVal);

        } else if (findVal < midVal) {
            return inserValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }
}
