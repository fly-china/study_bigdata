//给定一个二叉树，返回它的 后序 遍历。 
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
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 397 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * [145]-二叉树的后序遍历
 */
public class BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreePostorderTraversal().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        // 前序：中，左，右 （先压右子树，在压左子树）
        // 转换：中，右，左 （先压左子树，在压右子树）
        // 顺序逆序后 ： 左，右，中  == 后序
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) return list;

            Stack<TreeNode> stack = new Stack<>();
            Stack<Integer> stack2 = new Stack<>();

            stack.add(root);
            while (!stack.isEmpty()) {
                TreeNode tempNode = stack.pop();
                stack2.add(tempNode.val);

                if (tempNode.left != null) {
                    stack.add(tempNode.left);
                }
                if (tempNode.right != null) {
                    stack.add(tempNode.right);
                }
            }

            while (!stack2.isEmpty()) {
                list.add(stack2.pop());
            }

            return list;
        }


    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution_Recur {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) return list;

            traversalRecur(root, list);
            return list;
        }

        private void traversalRecur(TreeNode root, List<Integer> list) {
            if (root == null) return;

            traversalRecur(root.left, list);
            traversalRecur(root.right, list);
            list.add(root.val);
        }


    }

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
