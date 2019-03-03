package com.lpf.leetcode.topN;

import org.junit.Test;

/**
 * 计算一个正整数中有多少个1
 */
public class Calcul1Nums {

    @Test
    public void testDemo1() {
        int num = 175;

        System.out.println(num + "中1的个数为：" + solution1(num));
        System.out.println(num + "中1的个数为：" + solution2(num));
    }

    // 判断一个数是否为2的N次方
    @Test
    public void test2N() {
        int num = 8;
        int  result = num & (num-1);

        if(result == 0){
            System.out.println(num + "是2的N次方");
        }else{
            System.out.println(num + "不是2的N次方");
        }

    }

    // 测试十进制转二进制
    @Test
    public void testShiToEr(){
        Integer n = 175;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(shiToEr(n));
    }

    /**
     * 思想：n和n-1的“&”与操作，会消除n中最后一位的1。比如：
     * n = 1011 0000 = 176
     * n-1 = 1010 1111 = 175
     * n & n-1 = 1010 0000
     *
     * @param num
     * @return
     */
    public int solution1(int num) {
        int count = 0;

        while (num != 0) {
            count++;
            num = num & (num - 1);
        }

        return count;
    }

    /**
     * 思想：n的二进制数与1作"&"运算，
     * 若结果等于1，这证明n的二进制数的最末尾为1；否则为0
     * 将n不断右移，计算出结果
     *
     * @param num
     * @return
     */
    public int solution2(int num) {
        int count = 0;

        int i =0 ;
        do{
            if((num & 1) == 1){
                count++;
            }
            num = num >> 1;
            i++;
        }while (i < 32);

        return count;
    }


    /**
     * 十进制转二进制
     *
     * @param num
     * @return
     */
    public String shiToEr(int num) {
        String byteVaule = "";

        int sum;
        for (int i = num; i >= 1; i = i / 2) {
            if (i % 2 == 0) {
                sum = 0;
            } else {
                sum = 1;
            }
            byteVaule = sum + byteVaule;
        }

        return byteVaule;
    }
}
