package com.hcs.datastructure.recursion;

public class Queen8 {
    int max = 8;//皇后数量

    int[] array = new int[max];

    static int count = 0;
    public static void main(String[] args) {

        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("解法个数："+count);

    }

    //放置第n个皇后
    private void check(int n){
        if(n == max){//所有皇后已经放好
            print();
            return;
        }
        //依次放入，判断是否冲突
        for (int i = 0; i < max; i++) {

            array[n] = i;
            //判断是否冲突
            if (judge(n)){//不冲突
                //接着放n+1
                check(n+1);
            }
        }
    }

    /**
     * 监测是否和前面的皇后冲突
     * @param n 第n个皇后
     * @return 是否冲突
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //Math.abs(n-i) == Math.abs(array[n]-array[i]判断是否在同一斜线
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    private void print(){
        count++;
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

}
