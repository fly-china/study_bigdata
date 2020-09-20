//给定一个二叉树，返回它的中序 遍历。 
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
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * [94]-二叉树的中序遍历
 */
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeInorderTraversal().new Solution();
        Solution_A solutionA = new BinaryTreeInorderTraversal().new Solution_A();
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

//        List<Integer> integers = solutionA.inorderTraversal(root);
//        integers.forEach(System.out :: println);

        List<Integer> result = solution.inorderTraversal(root);
        result.forEach(System.out::println);

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    // 迭代方式
    class Solution {
        Stack<TreeNode> stack = new Stack<>();

        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            while (stack.size() > 0 || root != null) {
                //不断往左子树方向走，每走一次就将当前节点保存到栈中
                //这是模拟递归的调用
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                    //当前节点为空，说明左边走到头了，从栈中弹出节点并保存
                    //然后转向右边节点，继续上面整个过程
                } else {
                    TreeNode tmp = stack.pop();
                    res.add(tmp.val);
                    root = tmp.right;
                }
            }
            return res;
        }
    }

//             1
//        2        6
//     3     4         7
//  5

    //leetcode submit region end(Prohibit modification and deletion)


    // 递归方式
    class Solution_A {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            helpTraversal(list, root);
            return list;
        }


        private void helpTraversal(List<Integer> res, TreeNode root) {
            if (root != null) {
                helpTraversal(res, root.left);
                res.add(root.val);
                helpTraversal(res, root.right);
            }
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