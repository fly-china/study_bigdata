//nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。 
//
// 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。 
//
// 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 
//nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。 
//
// 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
//输出：[-1,3,-1]
//解释：nums1 中每个值的下一个更大元素如下所述：
//- 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
//- 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
//- 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。 
//
// 示例 2： 
//
// 
//输入：nums1 = [2,4], nums2 = [1,2,3,4].
//输出：[3,-1]
//解释：nums1 中每个值的下一个更大元素如下所述：
//- 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
//- 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums1.length <= nums2.length <= 1000 
// 0 <= nums1[i], nums2[i] <= 10⁴ 
// nums1和nums2中所有整数 互不相同 
// nums1 中的所有整数同样出现在 nums2 中 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？ 
//
// Related Topics 栈 数组 哈希表 单调栈 👍 1215 👎 0


package com.lpf.leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * [496]-下一个更大元素 I
 */
public class NextGreaterElementI {
    public static void main(String[] args) {
        Solution solution = new NextGreaterElementI().new Solution();
        int[] nums1 = {2, 4};
        int[] nums2 = {1, 2, 3, 4};
        int[] res = solution.nextGreaterElement(nums1, nums2);
        System.out.println(JSONObject.toJSON(res));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public int[] nextGreaterElement(int[] nums1, int[] nums2) {

            Deque<Integer> stack = new LinkedList<>();
            Map<Integer, Integer> resMap = new HashMap<>(); // <nums2的 val，下一个更大元素的 val>
            stack.push(0);
            // 时间复杂度：O(n2)
            for (int i = 1; i < nums2.length; i++) {
                while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                    Integer pop = stack.pop();
                    resMap.put(nums2[pop], nums2[i]);
                }
                stack.push(i);
            }

            int[] res = new int[nums1.length];
            // 时间复杂度：O(n1)
            for (int i = 0; i < nums1.length; i++) {
                res[i] = resMap.getOrDefault(nums1[i], -1);
            }
            return res;
        }

        // 时间复杂度过高
        public int[] nextGreaterElement2(int[] nums1, int[] nums2) {

            int[] res2 = new int[nums2.length];
            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            // 时间复杂度：O(n2)
            for (int i = 1; i < nums2.length; i++) {
                while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                    Integer pop = stack.pop();
                    res2[pop] = i;
                }
                stack.push(i);
            }

            int[] res = new int[nums1.length];
            // 时间复杂度：O(n2 * n1)
            for (int i = 0; i < nums1.length; i++) {
                for (int j = 0; j < nums2.length; j++) {
                    if (nums1[i] == nums2[j]) {
                        res[i] = res2[j] == 0 ? -1 : nums2[res2[j]];
                        break;
                    }
                }
            }


            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}