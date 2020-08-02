package com.lpf.随便写点;

/**
 * 二维数组遍历
 *
 * @author lipengfei
 * @create 2020-07-31 15:05
 **/
/*
输入二维数组如下：
    int[][] arry
        [1, 2, 3, 4]
        [5, 6, 7, 8]
        [9,10,11,12]
按照顺时针转圈的顺序打印，打印结果如下：
        1,2,3,4, 8, 12,11,10,9, 5,6,7
 */
public class TwoDimenArrayTraverse {

    public static void main(String[] args) {
        int m = 4;
        int n = 3;
//        int[][] arr = new int[m][n];
        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

        System.out.println("Rows = " + arr.length);
        System.out.println("Columns = " + arr[0].length);


    }

}
