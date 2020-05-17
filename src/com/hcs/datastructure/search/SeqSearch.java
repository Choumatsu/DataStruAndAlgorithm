package com.hcs.datastructure.search;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,3,6,5,11,-1,34,89};

    }

    /**
     * 找一个
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
