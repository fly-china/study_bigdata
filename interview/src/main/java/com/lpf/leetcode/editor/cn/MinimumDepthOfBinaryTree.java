//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最小深度 2. 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 368 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [111]-二叉树的最小深度
 */
public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new MinimumDepthOfBinaryTree().new Solution();
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node3.left = node9;
        node3.right = node20;
        node20.left = node15;
        node20.right = node7;

        System.out.println(solution.minDepth(node3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDepth(TreeNode root) {
            if (root == null) return 0;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int depth = 1;

            while (!queue.isEmpty()) {
                int size = queue.size();

                boolean breakFlag = false;
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    if (poll.right == null && poll.left == null) {
                        breakFlag = true;
                        break;
                    } else {
                        if (poll.left != null) queue.add(poll.left);
                        if (poll.right != null) queue.add(poll.right);
                    }
                }
                if (breakFlag) {
                    break;
                }
                depth++;
            }


            return depth;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    class Solution_RECUR {
        public int minDepth(TreeNode root) {
            if (root == null) return 0;

            // 当左子树或右子树为空时，路径需要忽略，而不能认为是0；
            if (root.left == null && root.right != null) {
                return minDepth(root.right) + 1;
            }
            if (root.right == null && root.left != null) {
                return minDepth(root.left) + 1;
            }

            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
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