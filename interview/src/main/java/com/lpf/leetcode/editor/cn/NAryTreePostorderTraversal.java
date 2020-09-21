//给定一个 N 叉树，返回其节点值的后序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其后序遍历: [5,6,3,2,4,1]. 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 100 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * [590]-N叉树的后序遍历
 */
public class NAryTreePostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new NAryTreePostorderTraversal().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)


    class Solution {
        // 先序遍历：中，左，右
        // 后序遍历：左、右、中
        // 辅助栈：中、右、左
        public List<Integer> postorder(Node root) {
            List<Integer> resList = new ArrayList<>();
            if (root == null) return resList;

            Stack<Integer> helperStack = new Stack<>();
            Stack<Node> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                Node popNode = stack.pop();
                helperStack.push(popNode.val);

                List<Node> childNodes = popNode.children;
                if (childNodes != null && childNodes.size() > 0) {
                    for (int i = 0; i < childNodes.size(); i++) {
                        stack.push(childNodes.get(i));
                    }
                }
            }

            while (!helperStack.isEmpty()){
                resList.add(helperStack.pop());
            }

            return resList;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution_RECUR {
        public List<Integer> postorder(Node root) {
            List<Integer> resList = new ArrayList<>();
            if (root == null) return resList;

            helpPostOrderRecur(root, resList);
            return resList;
        }

        private void helpPostOrderRecur(Node root, List<Integer> resList) {
            if (root == null) return;

            List<Node> childNodes = root.children;
            if (childNodes != null && childNodes.size() > 0) {
                for (Node childNode : childNodes) {
                    helpPostOrderRecur(childNode, resList);
                }
            }

            resList.add(root.val);
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

    ;
}