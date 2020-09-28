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

import java.util.*;

/**
 * [429]-N叉树的层序遍历
 */
public class NAryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new NAryTreeLevelOrderTraversal().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 递归遍历，深度遍历
     */
    class Solution {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> resList = new ArrayList<>();
            if (root == null) return resList;

            List<Integer> levelList = new ArrayList<>();
            resList.add(levelList);
            helperLevelOrder(root, resList, 1);

            return resList;
        }

        /**
         * 递归函数
         * @param root 根节点
         * @param resList 大结果集
         * @param level 节点所处的层级
         */
        private void helperLevelOrder(Node root, List<List<Integer>> resList, int level) {
            if (root == null) return;

            // 如果最终结果集的数量 < 节点所处层级，说明是第一次遍历到此层级的集合
            // 创建一个该level层级的空集合，先放入最终结果集List
            if (resList.size() < level) {
                List<Integer> levelList = new ArrayList<>();
                resList.add(levelList);
            }

            // 找到该level层级小集合，并将数字追加到List中
            resList.get(level - 1).add(root.val);

            // 递归遍历子节点
            List<Node> childNodes = root.children;
            if (childNodes != null && childNodes.size() > 0) {
                for (Node childNode : childNodes) {
                    helperLevelOrder(childNode, resList, level + 1);
                }
            }

        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 迭代遍历，宽度遍历
     *
     * 广度优先搜索。我们使用队列来进行广度优先搜索，队列具有先进先出的特性。
     * 在这里使用栈是错误的选择，栈应用于深度优先搜索。
     */
    class Solution_FOR {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> resList = new ArrayList<>();
            if (root == null) return resList;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()){
                int size = queue.size();
                List<Integer> levelList = new ArrayList<>(size);
                resList.add(levelList);

                for (int i = 0; i < size; i++) {
                    Node poll = queue.poll();
                    levelList.add(poll.val);

                    if(poll.children != null){
                        queue.addAll(poll.children);
                    }
                }

            }

            return resList;
        }
    }

    /**
     * 这个有点傻了
     * 迭代遍历，宽度遍历
     */
    class Solution_FOR_STUPID {
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> resList = new ArrayList<>();
            if (root == null) return resList;

            Stack<Node> stackA = new Stack<>();
            Stack<Node> stackB = new Stack<>();

            stackA.push(root);

            List<Integer> partList = null;
            while (!stackA.isEmpty()) {
                partList = new ArrayList<>();
                resList.add(partList);

                while (!stackA.isEmpty()) {
                    Node popA = stackA.pop();
                    partList.add(popA.val);
                    stackB.push(popA);
                }

                while (!stackB.isEmpty()) {
                    Node popB = stackB.pop();
                    List<Node> childs = popB.children;
                    if (childs != null && childs.size() > 0) {
                        for (int i = childs.size() - 1; i >= 0; i--) {
                            stackA.push(childs.get(i));
                        }
                    }
                }

            }

            return resList;
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