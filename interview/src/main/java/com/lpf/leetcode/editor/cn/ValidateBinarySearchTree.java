//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//      根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 
// 👍 776 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.Stack;

public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new ValidateBinarySearchTree().new Solution();
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        node5.left = node1;
        node5.right = node7;
        node7.left = node3;
        node7.right = node8;

        boolean validBST = solution.isValidBST(node5);
        System.out.println(validBST);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        long pre = Long.MIN_VALUE;

        /**
         * 左 < 中 < 右
         * 中序遍历：左 中 右
         * 按照中序遍历，如果是从小到大，则是一个合法的有序二叉树
         */
        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;

            Stack<TreeNode> stack = new Stack<>();

            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    TreeNode popNode = stack.pop();
                    if (pre >= popNode.val) {
                        return false;
                    } else {
                        pre = popNode.val;
                    }
                    root = popNode.right;
                }
            }
            return true;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    class Solution_Recur {

        long pre = Long.MIN_VALUE;

        /**
         * 左 < 中 < 右
         * 中序遍历：左 中 右
         * 按照中序遍历，如果是从小到大，则是一个合法的有序二叉树
         */
        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;

            // 访问并校验左子树
            if (!isValidBST(root.left)) {
                return false;
            }

            // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            System.out.println(pre);

            // 访问并校验右子树
            return isValidBST(root.right);
        }
    }

    /**
     * 挺傻挺笨的方法
     */
    class Solution_STUPID {
        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;

            if (root.left == null && root.right == null) return true;

            // 右节点的值大于左节点的值时，则不是二叉搜索树
            if (root.left != null && root.right != null && root.left.val > root.right.val) {
                return false;
            }

            // 寻找左节点的最大值
            Long leftMax = findTreeMax(root.left);
            // 寻找右节点的最小值
            Long rightMin = findTreeMin(root.right);

            if (leftMax != null && leftMax >= root.val) return false;
            if (rightMin != null && rightMin <= root.val) return false;

            return isValidBST(root.left) && isValidBST(root.right);
        }

        private Long findTreeMin(TreeNode root) {
            if (root == null) return Long.MAX_VALUE;
            Long min = Long.MAX_VALUE;
            if (root.val < min) {
                min = (long) root.val;
            }
            Long minLeft = findTreeMin(root.left);
            Long minRight = findTreeMin(root.right);

            return Math.min(min, Math.min(minLeft, minRight));
        }

        private Long findTreeMax(TreeNode root) {
            if (root == null) return Long.MIN_VALUE;
            Long min = Long.MIN_VALUE;
            if (root.val > min) {
                min = (long) root.val;
            }
            Long maxLeft = findTreeMax(root.left);
            Long maxRight = findTreeMax(root.right);

            return Math.max(min, Math.max(maxLeft, maxRight));
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}