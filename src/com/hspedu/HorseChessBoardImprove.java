package com.hspedu;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @ author  tanruikai
 * @ date  2022/4/4 14:01
 * @ version 1.0
 *  《马踏棋盘》 -- 贪心算法优化
 */
@SuppressWarnings("all")

public class HorseChessBoardImprove {

    //棋盘大小
    private static int X = 6; // col
    private static int Y = 6; // row
    private static int[][] chessBoard = new int[Y][X];
    private static boolean[] visited = new boolean[X * Y]; //记录某个位置是否走过
    private static boolean finished = false; //记录马儿是否遍历完棋盘

    public static void main(String[] args) {

        int row = 2;
        int col = 2;

        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard, row - 1, col - 1, 1);
        long end = System.currentTimeMillis();

        System.out.println("遍历耗时：" + (end - start));

        //输出棋盘情况
        for (int[] rows : chessBoard) {
            for (int step : rows) {  //step 表示 该位置是马应该走的第几步
                System.out.print(step + "\t");
            }
            System.out.println(" ");
        }
    }

    // 编写核心算法，遍历棋盘，如果遍历成功，就把finished 设置为 true
    // 并且将马儿的每一步step 记录到 chessBoard
    public static void traversalChessBoard(int[][] chessBoard, int row, int col, int step) {

        //先把step 记录到 chessBoard
        chessBoard[row][col] = step;
        //把这个位置设置为已经访问
        visited[row * X + col] = true;
        //获取当前位置可以走的下一个位置有哪些
        ArrayList<Point> ps = next(new Point(col, row)); //col - X , row - Y\
        sort(ps);
        //遍历
        while (!ps.isEmpty()) {
            // 取出一个位置
            Point p = ps.remove(0);
            // 判断这个位置是否走过，如果没有走过， 就递归遍历
            if (!visited[p.y * X + p.x]) {
                //递归遍历
                traversalChessBoard(chessBoard, p.y, p.x, step + 1);
            }
        }
        // 当退出 while 时， 判断是否遍历成功，若没有，就重置相应的值， 然后进行回溯
        if (step < X * Y && !finished) {
            chessBoard[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true;
        }
    }

    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }


    // 编写方法获取当前位置可以走的下一步的所有位置 (point 表示 x, y)
    public static ArrayList<Point> next(Point curPoint) {

        //创建ArrayList
        ArrayList<Point> ps = new ArrayList<>();

        //创建Point对象(点/对象)
        Point p1 = new Point();

        //判断在curPoint是否可以走如下位置，如果可以走就将该点 放入ps

        //判断是否可以走5 位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) { //判断点5 是否在棋盘内从而判断是否可以走这个点
            ps.add(new Point(p1));
        }
        //判断是否可以走6 位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) { //判断点6 是否在棋盘内从而判断是否可以走这个点
            ps.add(new Point(p1));
        }
        //判断是否可以走7 位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) { //判断点7 是否在棋盘内从而判断是否可以走这个点
            ps.add(new Point(p1));
        }
        //判断是否可以走0 位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) { //判断点0 是否在棋盘内从而判断是否可以走这个点
            ps.add(new Point(p1));
        }
        //判断是否可以走1 位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) { //判断点1 是否在棋盘内从而判断是否可以走这个点
            ps.add(new Point(p1));
        }
        //判断是否可以走2 位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) { //判断点2 是否在棋盘内从而判断是否可以走这个点
            ps.add(new Point(p1));
        }
        //判断是否可以走3 位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) { //判断点3 是否在棋盘内从而判断是否可以走这个点
            ps.add(new Point(p1));
        }
        //判断是否可以走4 位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) { //判断点4 是否在棋盘内从而判断是否可以走这个点
            ps.add(new Point(p1));
        }

        return ps;

    }


}
