//
// 请你设计并实现一个满足 
// LRU (最近最少使用) 缓存 约束的数据结构。
// 
//
// 
// 实现 
// LRUCache 类：
// 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 
//key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。 
// 
// 
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put 
// 
//
// Related Topics 设计 哈希表 链表 双向链表 👍 3335 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * [146]-LRU 缓存
 */
public class LruCache {
    public static void main(String[] args) {
//        Solution solution = new LruCache().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class LRUCache {
        class Node {
            int key, val;
            Node prev, next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        Map<Integer, Node> objMap;
        Node dummyNode;
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.dummyNode = initDummyNode();
            objMap = new HashMap<>(capacity);
        }

        public int get(int key) {
            Node node = getNode(key);
            return node == null ? -1 : node.val;
        }

        public Node getNode(int key) {
            Node node = objMap.get(key);
            if (node == null) return null;

            // 从双向链表中，移除当前节点，并插入至头部。可以优化一下：如果已经是第一个节点则不操作
            if (dummyNode.next != node) {
            removeCurNode(node);
            insertToHead(node);
            }

            return node;
        }

        public void put(int key, int value) {
            Node node = this.getNode(key);
            if (node != null) {
                node.val = value; // 上述 getNode 已经对节点做了处理
                return;
            }

            // 容量超标时，移除掉最久未使用的节点
            if (objMap.size() >= this.capacity) {
                Node longestNode = dummyNode.prev;
                removeCurNode(longestNode);
                objMap.remove(longestNode.key);
            }
            node = new Node(key, value);
            insertToHead(node);
            objMap.put(key, node);
        }

        private void removeCurNode(Node curNode) {
            curNode.prev.next = curNode.next;
            curNode.next.prev = curNode.prev;
        }

        private void insertToHead(Node curNode) {
            curNode.prev = dummyNode;
            curNode.next = dummyNode.next;

            dummyNode.next.prev = curNode;
            dummyNode.next = curNode;
        }

        private Node initDummyNode() {
            Node dummy = new Node(-1, -1);
            dummy.next = dummy;
            dummy.prev = dummy;
            return dummy;
        }
    }

    /**
     * 暂时利用LinkedHashMap实现。即，重写LinkedHashMap的removeEldestEntry方法。
     * removeEldestEntry方法，只有在afterNodeInsertion插入新节点时调用
     * removeEldestEntry默认永远为false，即：不删除老节点。
     * 重写removeEldestEntry，当map中元素个数大于capacity了，就要删除老节点了。
     * <p>
     * accessOrder = true，节点会按照插入时排序
     * accessOrder = false，节点会按照访问时排序
     */
    class LRUCacheV2 {
        LinkedHashMap<Integer, Integer> map = null;
        int max;

        public LRUCacheV2(int capacity) {
            // accessOrder = true:代表按照访问排序； false：代表按照插入排序
            map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;  // 容量大于capacity 时就删除
                }
            };
            max = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            } else {
                return map.get(key);
            }
        }

        public void put(int key, int value) {
            map.put(key, value);// 添加到链尾
        }
    }


//leetcode submit region end(Prohibit modification and deletion)


}