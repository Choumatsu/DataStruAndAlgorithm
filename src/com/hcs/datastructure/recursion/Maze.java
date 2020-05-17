package com.hcs.datastructure.recursion;

public class Maze {
    public static void main(String[] args) {
        //创建一个二维数组
        int[][] map = new int[8][7];
        //1表示墙,上下全置为一
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-------");

        setWay(map,1,1);
        //走过并标识的路
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

    }

    //使用递归寻路 -->map[6][5]
    //1表示墙 2表示可以走 3表示已经走过但不通
    //行走策略 下>右>上>左
    /**
     *
     * @param map 地图
     * @param i 开始位置
     * @param j 开始位置
     * @return 找到返回true
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5]==2){//通路已找到
            return true;
        }else{
            if(map[i][j]==0){//如果该点没有走过
                map[i][j] = 2;//假设该点可以走通
                if(setWay(map,i+1,j)){//down
                    return true;
                }else if(setWay(map,i,j+1)){//right
                    return true;
                }else if (setWay(map,i-1,j)){//up
                    return true;
                }else if (setWay(map,i,j-1)){//left
                    return true;
                }else {
                    //不通
                    map[i][j] = 3;
                    return false;
                }
            }else{
                return false;
            }
        }

    }

}
