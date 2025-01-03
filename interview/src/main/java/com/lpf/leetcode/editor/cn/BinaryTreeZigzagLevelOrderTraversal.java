//给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[20,9],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 939 👎 0


package com.lpf.leetcode.editor.cn;

import com.sun.tools.hat.internal.model.Root;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * [103]-二叉树的锯齿形层序遍历
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new BinaryTreeZigzagLevelOrderTraversal().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root != null) {
                traverseIter(root);
            }
            return res;
        }

        private void traverseIter(TreeNode root) {
            LinkedList<TreeNode> list = new LinkedList<>();
            list.add(root);

            int levelOrder = 0;
            while (!list.isEmpty()) {
                int queueSize = list.size();
                List<Integer> levelPath = new ArrayList<>();
                for (int i = 0; i < queueSize; i++) {
                    TreeNode treeNode = list.remove();
                    levelPath.add(treeNode.val);

                    if (treeNode.left != null)
                        list.add(treeNode.left);
                    if (treeNode.right != null)
                        list.add(treeNode.right);
                }

                if (levelOrder % 2 != 0) { // 奇数层，反转
                    Collections.reverse(levelPath);
                }
                res.add(levelPath);
                levelOrder++;
            }
        }

        private void traverseRecur(TreeNode root, int levelOrder) {
            if (root == null) return;
            if (res.size() <= levelOrder)
                res.add(new LinkedList<>());

            List<Integer> path = res.get(levelOrder);
            if (levelOrder % 2 == 0) {
                path.add(root.val);
            } else {
                path.add(0, root.val);
            }

            traverseRecur(root.left, levelOrder + 1);
            traverseRecur(root.right, levelOrder + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}