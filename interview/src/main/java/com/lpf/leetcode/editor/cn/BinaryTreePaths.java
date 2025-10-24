//给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3,null,5]
//输出：["1->2->5","1->3"]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：["1"]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [1, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 深度优先搜索 字符串 回溯 二叉树 👍 1219 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [257]-二叉树的所有路径
 */
public class BinaryTreePaths {
    public static void main(String[] args) {
        Solution solution = new BinaryTreePaths().new Solution();
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode root  = new TreeNode(1, node2, node3);

        List<String> strings = solution.binaryTreePaths(root);
        System.out.println(strings);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private List<List<String>> resList = new ArrayList<>();
        private LinkedList<String> path = new LinkedList<>();

        public List<String> binaryTreePaths(TreeNode root) {
            recur(root);

            return resList.stream()
                    .map(e -> String.join("->", e))
                    .collect(Collectors.toList());
        }

        private void recur(TreeNode root) {
            if (root == null) return;

            path.add(root.val + "");

            if (root.left == null && root.right == null) {
                resList.add(new ArrayList<>(path));
                path.removeLast();
                return;
            }

            recur(root.left);
            recur(root.right);

            path.removeLast();

            return;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}