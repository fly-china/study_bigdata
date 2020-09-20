package com.lpf.随便写点;

import java.util.ArrayList;
import java.util.List;

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
//        int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println("Rows = " + arr.length);// 3行
        System.out.println("Columns = " + arr[0].length); // 4列

        int colLeft = 0, colRight = arr[0].length - 1;
        int rowsTop = 0, rowsBotom = arr.length - 1;
        List<Integer> outList = new ArrayList<>();

        int counter = 0;
        int nums = arr[0].length * arr.length;
        while (counter < nums) {
            int index = colLeft;
            while (counter < nums && index <= colRight) {
                outList.add(arr[rowsTop][index++]);
                counter++;
            }
            rowsTop++;

            index = rowsTop;
            while (counter < nums && index <= rowsBotom) {
                outList.add(arr[index++][colRight]);
                counter++;
            }
            colRight--;

            index = colRight;
            while (counter < nums && index >= colLeft) {
                outList.add(arr[rowsBotom][index--]);
                counter++;
            }
            rowsBotom--;

            index = rowsBotom;
            while (counter < nums && index >= rowsTop) {
                outList.add(arr[index--][colLeft]);
                counter++;
            }
            colLeft++;
        }

        outList.forEach(num -> {
            System.out.print(num + ",");
        });

    }

}
//                    // 第一段
//                    arr[rowsTop][colLeft];
//                    arr[rowsTop][colLeft + 1];
//                    arr[rowsTop][colLeft + 2];
//                    arr[rowsTop][colRight];
//                    rowsTop++;
//
//                    // 第二段
//                    arr[rowsTop][colRight];
//                    arr[rowsTop + 1][colRight];
//                    arr[rowsTop + 2][colRight];
//                    arr[rowsBotom][colRight];
//                    colRight--;
//
//                    // 第三段
//                    arr[rowsBotom][colRight];
//                    arr[rowsBotom][colRight - 1];
//                    arr[rowsBotom][colRight - 2];
//                    arr[rowsBotom][colLeft];
//                    rowsBotom--;
//
//                    // 第四段
//                    arr[rowsBotom][colLeft];
//                    arr[rowsBotom - 1][colLeft];
//                    arr[rowsBotom - 2][colLeft];
//                    arr[rowsTop][colLeft];
//                    colLeft++;
//
//                    // 重复第一段
//                    arr[rowsTop][colLeft];
//                    arr[rowsTop][colLeft + 1];
//                    arr[rowsTop][colLeft + 2;