//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
// 
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 10⁵] 内。 
// -10⁹ <= Node.val <= 10⁹ 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 2860 👎 0


package com.lpf.leetcode.editor.cn;

/**
 * [236]-二叉树的最近公共祖先
 */
public class LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        node1.left = node2;
        node1.right = node9;

        node2.left = node3;
        node2.right = node4;

        node3.left = node5;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        System.out.println("***final res=" + solution.printNode(solution.lowestCommonAncestor(node1, node6, node7)));

        // 根据打印结果更好理解
        // root=5, L=null, R=null...res=null
        // root=6...res=6
        // root=3, L=null, R=6...res=6
        // root=7...res=7
        // root=8, L=null, R=null...res=null
        // root=4, L=7, R=null...res=7
        // root=2, L=6, R=7...res=2
        // root=9, L=null, R=null...res=null
        // root=1, L=2, R=null...res=2
        //
        // ***final res=2
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            if (root == p || root == q) {
                System.out.println("root=" + root.val + "...res=" + root.val);
                return root; // 找到目标节点就返回它。
            }


            TreeNode leftRes = lowestCommonAncestor(root.left, p, q); // 后序遍历：左。
            TreeNode rightRes = lowestCommonAncestor(root.right, p, q); // 后序遍历：右

            // 后序遍历：中
            TreeNode res = null;
            if (leftRes != null && rightRes != null) {
                // 若找到两个节点。说明左子树中包含了p或 q，右子树包含了另一个节点。那么该根节点就是最近公共父节点
                res = root;
            } else if (rightRes != null) {
                // 若找到一个节点。左空右非空，说明在右子树中包含该节点，左子树什么都没找到
                res = rightRes;
            } else if (leftRes != null) {
                // 若找到一个节点
                res = leftRes;
            } else {
                res = null;
            }

            System.out.println("root=" + root.val + ", L=" + printNode(leftRes) + ", R=" + printNode(rightRes)
                    + "...res=" + printNode(res));
            return res;
        }

        private String printNode(TreeNode node) {
            return node == null ? "null" : node.val + "";
        }


    }
//leetcode submit region end(Prohibit modification and deletion)


}