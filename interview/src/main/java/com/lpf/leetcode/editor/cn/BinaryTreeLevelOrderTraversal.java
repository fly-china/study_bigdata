//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索 
// 👍 645 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> resList = new ArrayList<>();
            if (root == null) return resList;
            LinkedList<TreeNode> linkedList = new LinkedList<>();
            linkedList.addLast(root);

            while (!linkedList.isEmpty()){
                int size = linkedList.size();

                List<Integer> levelList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = linkedList.removeFirst();
                    levelList.add(treeNode.val);
                    if (treeNode.left != null) linkedList.addLast(treeNode.left);
                    if (treeNode.right != null) linkedList.addLast(treeNode.right);
                }
                resList.add(levelList);
            }

            return resList;
        }

        public List<List<Integer>> levelOrder2024(TreeNode root) {
            List<List<Integer>> resList = new ArrayList<>();
            if (root == null) return resList;

            helperLevelOrder20241224(root, resList, 0);
            return resList;
        }

        private void helperLevelOrder20241224(TreeNode root, List<List<Integer>> resList, int levelOrder) {
            if (root == null) return;

            if (resList.size() <= levelOrder) {
                resList.add(new ArrayList<>());
            }
            resList.get(levelOrder).add(root.val);

            helperLevelOrder20241224(root.left, resList, levelOrder + 1);
            helperLevelOrder20241224(root.right, resList, levelOrder + 1);
        }


        private void helperLevelOrder(TreeNode root, List<List<Integer>> resList, int level) {
            if (root == null) return;
            if (resList.size() <= level) {
                List<Integer> levelList = new ArrayList<>();
                resList.add(levelList);
            }

            resList.get(level).add(root.val);

            helperLevelOrder(root.left, resList, level + 1);
            helperLevelOrder(root.right, resList, level + 1);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    class Solution_FOR {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> resList = new ArrayList<>();
            if (root == null) return resList;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> levelList = new ArrayList<>(size);
                resList.add(levelList);

                for (int i = 0; i < size; i++) {
                    TreeNode pollNode = queue.poll();
                    levelList.add(pollNode.val);

                    if (pollNode.left != null) queue.add(pollNode.left);
                    if (pollNode.right != null) queue.add(pollNode.right);
                }
            }

            return resList;
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