//给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历） 
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其自底向上的层次遍历为： 
//
// [
//  [15,7],
//  [9,20],
//  [3]
//]
// 
// Related Topics 树 广度优先搜索 
// 👍 334 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeLevelOrderTraversalIi {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversalIi().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> resList = new ArrayList<>();
            Stack<List<Integer>> stackList = new Stack<>();
            if (root != null) {
                helperRecur(stackList, root, 0);

                while (!stackList.isEmpty()) {
                    resList.add(stackList.pop());
                }
            }


            return resList;
        }

        private void helperRecur(Stack<List<Integer>> stackList, TreeNode root, int level) {
            if (root != null) {
                if (stackList.size() <= level) {
                    List<Integer> levelList = new ArrayList<>();
                    stackList.push(levelList);
                }

                stackList.get(level).add(root.val);

                helperRecur(stackList, root.left, level + 1);
                helperRecur(stackList, root.right, level + 1);
            }


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}