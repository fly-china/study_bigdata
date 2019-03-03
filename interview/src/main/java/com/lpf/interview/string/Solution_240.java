package com.lpf.interview.string;

import org.junit.Test;

public class Solution_240 {

    @Test
    public void testMethod() {
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int[][] matrix2= new int[][]{
                {-1}
        };
        // 5
//        System.out.println(searchMatrix2(matrix, 35));
//        System.out.println(searchMatrix2(matrix, 17));
        System.out.println(searchMatrix2(matrix2, -1));
    }


    /**
     * 太慢了，老哥，快和遍历完数组的效果类似了
     * 执行用时: 34 ms,在Search a 2D Matrix II的Java提交中击败了8.89% 的用户
     * 内存消耗: 50.4 MB, 在Search a 2D Matrix II的Java提交中击败了2.65% 的用户
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }

        boolean isExist = false;
        int maxColumnIndex = matrix[0].length - 1;// target可能存在的最大列索引
        int currentRow = 0;

        while (!isExist && currentRow <= matrix.length - 1) {

            // 先按照行纬度查询，从小至大
            int currentColumn = 0;
            while (currentColumn <= maxColumnIndex
                    && target >= matrix[currentRow][currentColumn]) {
                if (target == matrix[currentRow][currentColumn]) {
                    isExist = true;
                    break;
                }
                currentColumn++;
            }
            if(isExist){
                break;
            }

            maxColumnIndex = currentColumn - 1;
            currentRow++;
        }

        return isExist;
    }


    /**
     * 不能以左上和右下角元素为衡量，一个最小一个最大， 不管怎么比较，都需要横纵纬度均展开比较；
     * 我们可以以左下角（或右上角）的元素展开比较，因为只需从单向展开比较即可
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }

        boolean isExist = false;

        // 以右上角元素为基准，开始展开比较
        int currentRow =0;
        int currentColumn = matrix[0].length - 1;
        while( currentRow < matrix.length && currentColumn >0){

            if(matrix[currentRow][currentColumn] == target){
                isExist = true;
                break;
            }else if(matrix[currentRow][currentColumn] > target){
                // 右上角元素 > 目标值，应在当前行的左侧进行搜索
                currentColumn--;
            }else{
                // 右上角元素 < 目标值，应在当前列的下侧进行搜索
                currentRow++;
            }
        }

        return isExist;
    }
}
