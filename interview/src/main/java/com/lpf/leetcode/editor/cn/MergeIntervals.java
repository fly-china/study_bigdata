//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
//
// Related Topics 数组 排序 👍 2484 👎 0


package com.lpf.leetcode.editor.cn;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * [56]-合并区间
 */
public class MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new MergeIntervals().new Solution();
        int[] num1 = {1, 3};
        int[] num2 = {2, 6};
        int[] num3 = {8, 10};
        int[] num4 = {15, 18};
        int[][] intervals = {num2, num1, num4, num3};
        int[][] merged = solution.merge(intervals);
        System.out.println(JSONObject.toJSON(merged));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // [1,3],[2,6],[8,10],[15,18] 这四个区间
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length < 1) return null;

            // 按照数组的 left 的值进行从大到小的排序
            Arrays.sort(intervals, Comparator.comparingInt(list -> list[0]));

            List<int[]> res = new ArrayList<>();
            for (int[] interval : intervals) {
                int resSize = res.size();

                if (resSize < 1) {
                    res.add(interval);
                    continue;
                }

                // 如果 interval 数组的  left 值大于 res 数组中最大的值，说明区间没有连续。
                if (interval[0] > res.get(resSize - 1)[1]) {
                    res.add(interval);
                } else {
                    res.get(resSize - 1)[1] = Math.max(interval[1], res.get(resSize - 1)[1]);
                }
            }

            return res.toArray(new int[res.size()][]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}