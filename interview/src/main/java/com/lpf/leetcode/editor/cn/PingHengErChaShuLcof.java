//输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。 
//
// 
//
// 示例 1: 
//
// 给定二叉树 [3,9,20,null,null,15,7] 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回 true 。 
// 
//示例 2: 
//
// 给定二叉树 [1,2,2,3,3,null,null,4,4] 
//
//        1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4
// 
//
// 返回 false 。 
//
// 
//
// 限制： 
//
// 
// 1 <= 树的结点个数 <= 10000 
// 
//
// 注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/ 
//
// 
// Related Topics 树 深度优先搜索 
// 👍 49 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [剑指 Offer 55 - II]-平衡二叉树
 */
public class PingHengErChaShuLcof {
    public static void main(String[] args) {
        Solution solution = new PingHengErChaShuLcof().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 自顶向下递归（暴力法）--时间复杂度：O(nlog⁡n)
     * 空间复杂度：O(n)。如果树完全倾斜，递归栈可能包含所有节点。
     */
    class Solution {
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }

            if (!isBalanced(root.left) || !isBalanced(root.right)) {
                // 两个子树中存在不平衡，整棵树即不平衡
                return false;
            } else {
                // 两个子树中均平衡，根据两子树的深度差判断整棵树是否平衡
                int leftDepth = getTreeMaxDepth(root.left);
                int rightDepth = getTreeMaxDepth(root.right);
                return Math.abs(leftDepth - rightDepth) <= 1;
            }

//            if(Math.abs( getTreeMaxDepth(root.left) - getTreeMaxDepth(root.right)) > 1){
//                return false;
//            }else {
//                return isBalanced(root.left) && isBalanced(root.right);
//            }
        }

        // 获取以root为根节点的树的最大深度
        public int getTreeMaxDepth(TreeNode root) {
            if (root == null)
                return 0;
            if (root.left == null && root.right == null) {
                // 左右子树均为空
                return 1;
            } else {
                // 左右子树，存在不为空。计算左右子树的最大深度，+1后即为root树的最大深度
                int leftDepth = getTreeMaxDepth(root.left);
                int rightDepth = getTreeMaxDepth(root.right);
                return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
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