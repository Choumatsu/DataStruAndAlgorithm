package com.hcs.datastructure.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000,1000,1000,1234};



        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(resIndexList);
    }

    /**
     * @param arr     数组
     * @param left    左
     * @param right   右
     * @param findVal 要找的值
     * @return 返回找到的
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }

    /**
     * 找出所有重复的
     *
     * @return
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            //向mid左边扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                //否则，放入集合
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);

            //向右扫描
            temp = mid + 1;
            while (true){
                if (temp > arr.length-1 || arr[temp] != findVal) {
                    break;
                }
                //否则，放入集合
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }
}
