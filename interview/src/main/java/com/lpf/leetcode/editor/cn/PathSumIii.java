//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。 
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,1000] 
// 
// -10⁹ <= Node.val <= 10⁹ 
// -1000 <= targetSum <= 1000 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 1964 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [437]-路径总和 III
 */
public class PathSumIii {
    public static void main(String[] args) {
        Solution solution = new PathSumIii().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        /**
         * 前缀和的方式。
         * 1、每遍历一个节点 node，就计算 root 节点到该节点的前缀和，记为 curr，同时放入 map 中。即 root.val + p1.val + p2.val + ... + node.val
         * 2、
         */
        public int pathSum(TreeNode root, int targetSum) {
            if (root == null) return 0;

            Map<Long, Integer> prefixSumMap = new HashMap<>();
            prefixSumMap.put(0L, 1);

            return recur(root, targetSum, 0, prefixSumMap);
        }

        private int recur(TreeNode node, long targetSum, long curr, Map<Long, Integer> prefixSumMap) {
            if (node == null) return 0;

            curr += node.val;

            Integer res = prefixSumMap.getOrDefault((curr - targetSum), 0);
            prefixSumMap.merge(curr, 1, Integer::sum);

            int resLeft = recur(node.left, targetSum, curr, prefixSumMap);
            int resRight = recur(node.right, targetSum, curr, prefixSumMap);

            prefixSumMap.merge(curr, -1, Integer::sum);

            return res + resLeft + resRight;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}