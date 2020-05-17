package com.hcs.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {

    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    //标记棋盘位置是否被访问过
    private static boolean[] visited;

    //是否所有位置都被访问过
    private static boolean finished;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;

        //穿件棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];

        long start = System.currentTimeMillis();
        traversalCheaaboard(chessboard,row-1,column-1,1);
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start)/1000.0+"s");

        for (int[] rows:chessboard){
            for (int cols:rows){
                System.out.print(cols+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 骑士周游问题算法
     *
     * @param chessboard 棋盘
     * @param row        当前行 0开始
     * @param column     当前列 0开始
     * @param step       是第几步 初始位置就是第一步
     */
    public static void traversalCheaaboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true;//二维转一维，标记该位置被访问
        //System.out.println(step);

        //获取可以走的下一个位置集合
        ArrayList<Point> ps = next(new Point(column, row));

        sort(ps);//根据当前这一步的所有下一步的选择位置。进行非递减排序

        //遍历集合
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            //判断该点是否访问过
            if (!visited[p.y * X + p.x]) {
                traversalCheaaboard(chessboard, p.y, p.x, step + 1);
            }
        }

        //判断是否完成
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前的位置，计算还能走那些位置，并放入集合中，最多有8个位置
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();

        Point p1 = new Point();
        //判断八个点是否能走
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //System.out.println(ps);
        return ps;

    }

    //根据当前这一步的所有下一步的选择位置。进行非递减排序
    public static void sort(ArrayList<Point> ps){
        ps.sort(Comparator.comparingInt(p -> next(p).size()));
    }
}
