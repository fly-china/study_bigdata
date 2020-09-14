//给定两个二叉树，编写一个函数来检验它们是否相同。 
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
//
// 示例 1: 
//
// 输入:       1         1
//          / \       / \
//         2   3     2   3
//
//        [1,2,3],   [1,2,3]
//
//输出: true 
//
// 示例 2: 
//
// 输入:      1          1
//          /           \
//         2             2
//
//        [1,2],     [1,null,2]
//
//输出: false
// 
//
// 示例 3: 
//
// 输入:       1         1
//          / \       / \
//         2   1     1   2
//
//        [1,2,1],   [1,1,2]
//
//输出: false
// 
// Related Topics 树 深度优先搜索 
// 👍 469 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [100]-相同的树
 */
public class SameTree {
    public static void main(String[] args) {
        Solution solution = new SameTree().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            // 第一次想到的方法
            /*if (p == null && q == null) return true;
            if (p == null || q == null) return false;

            if (p.val != q.val) return false;

            if (!isSameTree(p.left, q.left)) return false;

            return isSameTree(p.right, q.right);*/

            // 看完国际站后，优化成两行代码
            if (p == null || q == null) return (p==q);
            return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}