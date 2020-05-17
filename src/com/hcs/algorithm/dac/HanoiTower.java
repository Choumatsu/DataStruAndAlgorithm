package com.hcs.algorithm.dac;

public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');

    }

    //汉诺塔 分值算法
    public static void hanoiTower(int num,char a,char b, char c){
        if(num == 1){
            System.out.println("第1个盘从"+a+" -> "+c);
        }else {
            //n>=2,看成上面所有盘，下面一个盘
            //1.上面所有盘a->b
            hanoiTower(num-1,a,c,b);
            //2.下边的盘a->c
            System.out.println("第"+num+"个盘从"+a+" -> "+c);
            //3.把b塔所有盘b->c
            hanoiTower(num-1,b,a,c);
        }
    }

}
