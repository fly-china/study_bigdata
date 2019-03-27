package com.lpf.leetcode.math;

import org.junit.Test;

/**
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * <p>
 * 如n=3, 3! = 3*2 =6,  尾数中没有零, result =0
 * 如n=5, 5! = 5*4*3*2 = 120,  尾数中有1个零, result = 1
 *
 * @author lipengfei
 * @create 2019-03-27 11:07
 **/
public class Solution_172 {

    /**
     * 解题思路：
     * 0的个数，即求10的个数，一个10是最终是由2*5产生。
     * 阶乘是指：从1 * 2 * 3 * ....* n，故存在5时，至少存在2，即2的个数一定是大于5
     * 该题转换后：即查找1至n中5的个数。
     * <p>
     * 比如：n=31时，存在5的数字有：5、10、15、20、25、30,这其中第五个包含5的数25=5*5包含两个五，所以最终5的个数为：6+1=7个
     * 每隔5个连续的数存在1个5， 然后在这些可被5整除的数中, 每间隔5个数又有一个可以被25整除, 故要再除一次5....
     * <p>
     * 当0 < n < 5时，f(n!) = 0;
     * 当n > 5时, f(n!) = k + f(k!), 其中k = n / 5;
     */
    @Test
    public void testMethod() {
        int n = 30;
        int res2 = trailingZeroes2(n);
        int res1 = trailingZeroes1(n);

        System.out.println(n + "的阶乘尾数中0的个数为：" + res1);
        System.out.println(n + "的阶乘尾数中0的个数为：" + res2);

    }


    // 非递归解法
    public int trailingZeroes1(int n) {
        int count = 0;
        while (n / 5 != 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }


    // 非递归解法
    public int trailingZeroes2(int n) {
        if (n < 5)
            return 0;
        int k = n / 5;
        return k + trailingZeroes2(k);
    }
}
