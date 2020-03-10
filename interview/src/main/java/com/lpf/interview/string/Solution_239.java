package com.lpf.interview.string;

import org.junit.Test;

/**
 * 滑动窗口的最大值
 *
 * @author lipengfei
 * @create 2019-03-08 23:29
 **/
public class Solution_239 {


    @Test
    public void testMethod(){
        int[] nums = {1, -1};
        int k = 1;

        int[] windowMax = maxSlidingWindow(nums, k);

        for (int i = 0; i < windowMax.length; i++) {
            int max = windowMax[i];
            System.out.print(max);
            System.out.print(", ");
        }


    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < 1 || k<1)
            return new int[0];

        int[] maxArr = new int[nums.length - k + 1];

        int maxTemp = nums[0];
        for(int i = 1; i < k; i++){
            if(maxTemp < nums[i])
                maxTemp = nums[i];
        }
        maxArr[0] = maxTemp;


        for(int i = k; i < nums.length; i++){
            if(nums[i] >= maxTemp){
                // 新滑进串口的肯定是最大值
                maxTemp = nums[i];
            }else{
                if(maxTemp > nums[i-k]){
                    // 说明新滑出的值并非最大值，最大值还在窗口内
                }else if(maxTemp < nums[i-k]){
                    // 不可能存在
                }else{
                    int maxTemp2 = nums[i];
                    for(int j = 1; j < k; j++){
                        if(maxTemp2 < nums[i - j])
                            maxTemp2 = nums[i - j];
                    }
                    maxTemp = maxTemp2;
                }

            }


            maxArr[i - k + 1] = maxTemp;
        }

        return maxArr;
    }
}
