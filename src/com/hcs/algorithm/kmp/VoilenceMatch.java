package com.hcs.algorithm.kmp;

public class VoilenceMatch {
    public static void main(String[] args) {
        String str1 = "asdasssddssdasdadaaas";

        String str2 = "ssda";

        System.out.println(violenceMatch(str1,str2));

    }

    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = str1.length();
        int s2Len = str2.length();

        int i = 0;
        int j = 0;

        while (i < s1Len && j < s2Len) {
            if (s1[i]==s2[j]){
                i++;
                j++;
            }else {
                i = i-(j-1);
                j = 0;
            }
        }

        if(j==s2Len){
            return i-j;
        }else {
            return -1;
        }


    }
}
