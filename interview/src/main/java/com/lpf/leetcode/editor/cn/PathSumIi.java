//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
// 
// 
// 
// 
//
// 示例 1： 
// 
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
//
// Related Topics 树 深度优先搜索 回溯 二叉树 👍 1161 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * [113]-路径总和 II
 */
public class PathSumIi {
    public static void main(String[] args) {
        Solution solution = new PathSumIi().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private List<List<Integer>> res = new ArrayList<>();
        private LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

            backtracking(root, targetSum);

            return res;
        }

        private void backtracking(TreeNode node, int targetSum) {
            if (node == null) return;

            path.add(node.val);

            if (node.left == null && node.right == null) {
                if (targetSum == node.val) {
//                    path.add(node.val);
                    res.add(new ArrayList<>(path));
//                    path.removeLast();
                }
                return;
            }

//            path.add(node.val);
            if (node.left != null) {
                backtracking(node.left, targetSum - node.val);
                path.removeLast();
            }
            if (node.right != null) {
                backtracking(node.right, targetSum - node.val);
                path.removeLast();
            }
        }

        private void backtracking2(TreeNode node, int targetSum) {
            if (node == null) {
                if (0 == targetSum) {
                    // 回收结果
                    System.out.print("收结果了：");
                    path.forEach(System.out::print);
                    System.out.println(" ");
                    res.add(new ArrayList<>(path));
                }
                return; // 已经到椰子节点了，返回
            }

            path.add(node.val);
            System.out.println("node.val=" + node.val);
            backtracking2(node.left, targetSum - node.val);
            backtracking2(node.right, targetSum - node.val);
            Integer removed = path.removeLast();
            System.out.println("***remove val=" + removed);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}