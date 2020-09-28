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

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * [101]-对称二叉树
 */
public class SymmetricTree {
    public static void main(String[] args) {
        Solution solution = new SymmetricTree().new Solution();

        Queue<TreeNode> queue = new LinkedList();
        System.out.println(queue.poll());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) return true;


            Queue<TreeNode> queue = new LinkedList();
            queue.add(root.left);
            queue.add(root.right);

            while (!queue.isEmpty()) {
                TreeNode left = queue.poll();
                TreeNode right = queue.poll();

                if (left == null && right == null) continue;
                if (left == null || right == null) return false;
                if (left.val != right.val) return false;

                queue.add(left.right);
                queue.add(right.left);
                queue.add(left.left);
                queue.add(right.right);
            }

            return true;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution_RECUR {
        public boolean isSymmetric(TreeNode root) {
            if (root == null) return true;
            // 比较左右子树是否对称
            return isSymmetric(root.left, root.right);
        }

        public boolean isSymmetric(TreeNode left, TreeNode right) {
            if (left == null && right == null) return true;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;

            // 左子树的左节点VS右子树的右节点  && 左子树的右节点VS右子树的左节点  都是相同的，才算对称
            return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
        }
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}