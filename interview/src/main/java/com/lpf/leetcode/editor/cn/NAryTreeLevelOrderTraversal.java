//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索 
// 👍 109 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.List;

/**
 * [429]-N叉树的层序遍历
 */
public class NAryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new NAryTreeLevelOrderTraversal().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public List<List<Integer>> levelOrder(Node root) {

            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}