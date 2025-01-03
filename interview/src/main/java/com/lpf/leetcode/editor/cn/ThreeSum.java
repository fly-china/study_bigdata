//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针 
// 👍 2439 👎 0


package com.lpf.leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * [15]-三数之和
 */
@Slf4j
public class ThreeSum {
    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
//        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
//        int[] nums = {-2, 0, 0, 2, 2};
//        int[] nums = {-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
//        int[] nums = {2, -3, 0, -2, -5, -5, -4, 1, 2, -2, 2, 0, 2, -4};
//        int[] nums = {1, 1, -2};
        List<List<Integer>> lists = solution.threeSum(nums);
        System.out.println(lists.size());
        System.out.println(JSONObject.toJSON(lists));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();

            Arrays.sort(nums);

            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int targetSum = -nums[i];
                if ((targetSum < 0 && nums[i + 1] > 0)
                        || (targetSum > 0 && nums[nums.length - 1] < 0)) {
                    break;
                }
                int low = i + 1;
                int high = nums.length - 1;
                while (low < high) {
                    int sum = nums[low] + nums[high];
                    if (sum == targetSum) {
                        res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        do low++;
                        while (low < high && nums[low] == nums[low - 1]);
                        do high--;
                        while (low < high && nums[high] == nums[high + 1]);
                    } else if (sum < targetSum) {
                        do low++;
                        while (low < high && nums[low] == nums[low - 1]);
                    } else {
                        do high--;
                        while (low < high && nums[high] == nums[high + 1]);
                    }
                }
            }

            return res;
        }


        /**
         * [[-5,1,4],[-4,0,4],[-4,1,3],[-2,-2,4]] ....... [-2,1,1],[0,0,0]
         * 双指针 + 双重循环 [-5,-5,-4,-4,-4,-2,-2,- 2, 0, 0, 0,  1, 1, 3, 4, 4]
         * 5         8        11     13    15
         */
        public List<List<Integer>> threeSum2020(int[] nums) {
            List<List<Integer>> numlist = new ArrayList<>();
            if (nums == null || nums.length < 3) return numlist;
            Arrays.sort(nums);
            if (nums[0] > 0 || nums[nums.length - 1] < 0) return numlist;

            for (int i = 0; i < nums.length - 2; i++) {
                // 最小值为正数或最大值为负数，则不可能出现a+b+c=0
                if (nums[i] > 0) break;

                int a = nums[i];
                // 若数组中当前元素 等于 前一个元素，代表会有重复出现，跳过
                if (i != 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // 把i之后的数组，起始和结尾位置设置双指针
                for (int low = i + 1, high = nums.length - 1; low < high; ) {
                    if ((nums[low] + nums[high] + a) < 0) {
                        low++;
                        while (low < high && nums[low] == nums[low - 1]) low++;
                    } else if ((nums[low] + nums[high] + a) > 0) {
                        high--;
                        while (low < high && nums[high] == nums[high + 1]) high--;
                    } else {
                        numlist.add(Arrays.asList(a, nums[low], nums[high]));
                        low++;
                        high--;
                        while (low < high && nums[low] == nums[low - 1]) low++;
                        while (low < high && nums[high] == nums[high + 1]) high--;
                    }

                }
            }

            return numlist;
        }

        // 暴力解法-三重for循环 O(n^3) -- 超时跑不出结果
        public List<List<Integer>> threeSum_A(int[] nums) {
            if (nums == null || nums.length < 3) return new ArrayList<>();
            List<List<Integer>> numlist = new ArrayList<>();
            Set<String> set = new HashSet<>();

            for (int i = 0; i < nums.length; i++) {
                int a = nums[i];
                for (int j = i + 1; j < nums.length; j++) {
                    int b = nums[j];
                    for (int k = j + 1; k < nums.length; k++) {
                        int c = nums[k];
                        if (a + b + c == 0) {
                            List<Integer> list = Arrays.asList(a, b, c);
                            Collections.sort(list);
                            int min = list.get(0);
                            int second = list.get(1);
                            if (!set.contains(min + "_" + second)) {
                                // 没有重复
                                numlist.add(list);
                                set.add(min + "_" + second);
                            }
                        }
                    }
                }

            }

            return numlist;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}