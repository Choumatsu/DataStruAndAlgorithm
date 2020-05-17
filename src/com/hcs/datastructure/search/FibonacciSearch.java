package com.hcs.datastructure.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int MAX_SIZE = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        System.out.println(fibSearch(arr, 1234));
    }

    //非递归方法得到斐波那契数列
    public static int[] fib() {
        int[] f = new int[MAX_SIZE];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < MAX_SIZE; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * @param a   数组
     * @param key 需要查找的值
     * @return 返回对应下标，没有返回-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//斐波那契分割数值的下标
        int mid = 0;
        int[] f = fib();
        //获取斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]可能大于a的长度，因此我们需要使用Arrays类构造一个新数组，并指向a
        //不足的部分用0填充
        int[] temp = Arrays.copyOf(a, f[k]);

        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                k--;//f[k] = f[k-1] + f[k-2],此为前半部分,即f[k-1]
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;//此为f[k-2]
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;

    }
}
