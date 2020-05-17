package com.hcs.algorithm.kmp;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "asdasssddssdasdadaaassdasdasdddsasds";

        String str2 = "sasds";

        System.out.println(str1.length()-str2.length());
        System.out.println(KMPSearch(str1,str2,kmpNext(str2)));
    }

    /**
     * kmp算法
     *
     * @param str1 原字符串
     * @param str2 要匹配的字符串
     * @param next 要匹配的字符串的部分匹配值
     * @return 第一个匹配到的起始位置，没有返回-1
     */
    public static int KMPSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {

            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取一个字符串的部分匹配值
    public static int[] kmpNext(String dest) {
        //数组保存部分匹配值
        int[] next = new int[dest.length()];

        next[0] = 0;

        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }


            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }

            next[i] = j;
        }

        return next;
    }
}
