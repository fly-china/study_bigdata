//给定一个二叉树，返回它的 前序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 368 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * [144]-二叉树的前序遍历
 */
public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreePreorderTraversal().new Solution();
        Solution_Recur solutionA = new BinaryTreePreorderTraversal().new Solution_Recur();
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node2;
        root.right = node6;
        node2.left = node3;
        node2.right = node4;
        node3.left = node5;
        node6.right = node7;

        List<Integer> result = solution.preorderTraversal(root);
        result.forEach(System.out::println);

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        // 中，左，右
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode tempNode = stack.pop();
                list.add(tempNode.val);

                if (tempNode.right != null) {
                    stack.push(tempNode.right);
                }
                if (tempNode.left != null) {
                    stack.push(tempNode.left);
                }
            }

            return list;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution_LOOP_B {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }

            Stack<TreeNode> stack = new Stack<>();
            while (stack.size() > 0 || root != null) {

                if (root != null) {
                    stack.push(root);
                    list.add(root.val);
                    root = root.left;
                } else {
                    TreeNode popNode = stack.pop();
                    root = popNode.right;
                }
            }

            return list;
        }
    }

    class Solution_Recur {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }

            traversalRecur(root, list);

            return list;
        }

        private void traversalRecur(TreeNode root, List<Integer> list) {
            if (root == null) return;
            list.add(root.val);
            traversalRecur(root.left, list);
            traversalRecur(root.right, list);
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