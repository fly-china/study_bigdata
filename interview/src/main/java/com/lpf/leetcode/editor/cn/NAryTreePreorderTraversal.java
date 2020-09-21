//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 102 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NAryTreePreorderTraversal {
    public static void main(String[] args) {
        Solution solution = new NAryTreePreorderTraversal().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
;
*/

    class Solution {
        public List<Integer> preorder(Node root) {
            List<Integer> resList = new ArrayList<>();
            if (root == null) return resList;

            Stack<Node> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                Node popNode = stack.pop();
                resList.add(popNode.val);

                List<Node> children = popNode.children;
                if (children == null || children.size() <= 0)
                    continue;

                for (int i = children.size() - 1; i >= 0; i--) {
                    stack.push(children.get(i));
                }
            }

            return resList;
        }


    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution_Recur {
        public List<Integer> preorder(Node root) {
            List<Integer> resList = new ArrayList<>();
            if (root == null) return resList;

            helpPreOrderRecur(root, resList);
            return resList;
        }

        private void helpPreOrderRecur(Node root, List<Integer> resList) {
            if (root == null) return;
            resList.add(root.val);

            if (root.children == null || root.children.size() <= 0) return;

            for (Node child : root.children) {
                helpPreOrderRecur(child, resList);
            }

        }


    }

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
}