//输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。 
//
// 例如： 
//
// 给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 10000 
// 
//
// 注意：本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-tre
//e/ 
// Related Topics 树 深度优先搜索 
// 👍 34 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

/**
 * [剑指 Offer 55 - I]-二叉树的深度
 */
public class ErChaShuDeShenDuLcof {
    public static void main(String[] args) {
        Solution solution = new ErChaShuDeShenDuLcof().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int maxDepth(TreeNode root) {
            return root == null ? 0 :
                    Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }


        // 广度优先搜索（BFS）
        public int maxDepthBFS(TreeNode root) {
            if (root == null) return 0;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int result = 0;

            while (!queue.isEmpty()) {
                int len = queue.size(); //必须提前获取size。offer()后，size会变化
                for (int i = 0; i < len; i++) {
                    TreeNode poll = queue.poll();
                    if (poll.left != null) queue.offer(poll.left);
                    if (poll.right != null) queue.offer(poll.right);
                }
                ++result;
            }
            return result;
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