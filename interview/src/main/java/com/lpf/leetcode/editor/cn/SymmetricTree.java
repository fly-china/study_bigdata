//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//       1
//     / \
//    2   2
//   / \ / \
//  3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//    1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 1038 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.Stack;

/**
 * [101]-对称二叉树
 */
public class SymmetricTree {
    public static void main(String[] args) {
        Solution solution = new SymmetricTree().new Solution();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.left = node2;


        System.out.println(solution.isSymmetric(node1));


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            // TODO：给定一个二叉树，检查它是否是镜像对称的。
            if (root == null) return true;

            boolean isSymmetric = true;
            // 左子树的栈（先左后右）
            Stack<TreeNode> stackLeft = new Stack<>();
            if (root.left != null) stackLeft.add(root.left);
            // 右子树的栈（先右后左）
            Stack<TreeNode> stackRight = new Stack<>();
            if (root.right != null) stackRight.add(root.right);

            while (!stackRight.isEmpty() || !stackLeft.isEmpty()) {
                if (stackLeft.size() != stackRight.size()) {
                    isSymmetric = false;
                    break;
                }

                TreeNode leftPop = stackLeft.pop();
                TreeNode rightPop = stackRight.pop();
                if (leftPop.val != rightPop.val) {
                    isSymmetric = false;
                    break;
                }

                if (leftPop.left == null && rightPop.left == null
                        && leftPop.right == null && rightPop.right == null) {
                    break;
                }
                if ((leftPop.left != null && rightPop.right != null)
                        || (leftPop.right != null && rightPop.left != null)) {
                    // 左子树的栈（先左后右）
                    if (leftPop.left != null) stackLeft.push(leftPop.left);
                    if (leftPop.right != null) stackLeft.push(leftPop.right);

                    // 左子树的栈（先右后左）
                    if (rightPop.right != null) stackRight.push(rightPop.right);
                    if (rightPop.right != null) stackRight.push(rightPop.left);
                } else {
                    isSymmetric = false;
                    break;
                }
            }


            return isSymmetric;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}