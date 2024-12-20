//请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。 
//
// 实现 LFUCache 类： 
//
// 
// LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象 
// int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。 
// void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 
//capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。 
// 
//
// 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。 
//
// 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
//
// 
//
// 示例： 
//
// 
//输入：
//["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", 
//"get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
//输出：
//[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
//
//解释：
//// cnt(x) = 键 x 的使用计数
//// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
//LFUCache lfu = new LFUCache(2);
//lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
//lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
//lfu.get(1);      // 返回 1
//                 // cache=[1,2], cnt(2)=1, cnt(1)=2
//lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
//                 // cache=[3,1], cnt(3)=1, cnt(1)=2
//lfu.get(2);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,1], cnt(3)=2, cnt(1)=2
//lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
//                 // cache=[4,3], cnt(4)=1, cnt(3)=2
//lfu.get(1);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,4], cnt(4)=1, cnt(3)=3
//lfu.get(4);      // 返回 4
//                 // cache=[3,4], cnt(4)=2, cnt(3)=3 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 10⁴ 
// 0 <= key <= 10⁵ 
// 0 <= value <= 10⁹ 
// 最多调用 2 * 10⁵ 次 get 和 put 方法 
// 
//
// Related Topics 设计 哈希表 链表 双向链表 👍 854 👎 0


package com.lpf.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * [460]-LFU 缓存
 */
public class LfuCache {
    public static void main(String[] args) {
//        Solution solution = new LfuCache().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LFUCache {
        class Node {
            int key, val;
            Node prev, next;
            int count;

            public Node(int key, int val, int cnt) {
                this.key = key;
                this.val = val;
                this.count = cnt;
            }
        }

        Map<Integer, Node> keyToNodeMap;         // <key, objNode>
        Map<Integer, Node> counterToDummyMap;    // <counter, dummyNode>
        int minCounter = -1;
        int capacity;

        public LFUCache(int capacity) {
            this.keyToNodeMap = new HashMap<>(capacity);
            this.counterToDummyMap = new HashMap<>(capacity);
            this.capacity = capacity;
        }

        public int get(int key) {
            Node objNode = keyToNodeMap.get(key);
            if (objNode == null) {
                return -1;
            }

            // 1、在对应 counter 的链表中，移除掉本 node
            removeCurNode(objNode);

//            Node curCounterDummyNode = counterToDummyMap.get(objNode.count);
//            if (objNode.count == minCounter) {
//                // minCounter 有两种情况。A:仅有本 node 的 cnt 等于 min 值  B:还有其他节点的 cnt 值等于 min 值
//                // 2、判断 minCounter 的链表上是否还有其他节点
//                if (curCounterDummyNode.next == curCounterDummyNode) {
//                    minCounter++;  // 无其他节点，计数器自增
//                }
//            } else if (objNode.count > minCounter) {
//                // minCounter 保持不变。
//            } else {
//                // node.count < minCounter, 这种情况不可能存在。minCounter 要永远保持最小值
//            }
            // 注释的代码可以优化为下述代码⬇️，即：当前节点是最低访问频次，且最低频次的节点只有这一个时，则需要调整 minCounter
            Node curCounterDummyNode = counterToDummyMap.get(objNode.count);
            if (curCounterDummyNode.next == curCounterDummyNode && minCounter == objNode.count) {
                minCounter++;
            }

            // 3、在 counter+1 的链表中，重新连接上本节点.
            objNode.count += 1;
            Node newCounterDummyNode = counterToDummyMap.computeIfAbsent(objNode.count, k -> initDummyNode());
            insertToHead(objNode, newCounterDummyNode);

            return objNode.val;
        }

        public void put(int key, int value) {
            Node objNode = keyToNodeMap.get(key);
            if (objNode != null) {
                this.get(key);
                objNode.val = value;
                return;
            }

            if (keyToNodeMap.size() >= capacity) { // 超过了最大容量，从 minCounter 的 Node 中删除 LRU 节点
                Node lruNode = removeLRUFromCounterNode(minCounter);
                keyToNodeMap.remove(lruNode.key);
            }

            Node newNode = new Node(key, value, 1);
            minCounter = 1;
            Node minCounterDummyNode = counterToDummyMap.computeIfAbsent(minCounter, k -> initDummyNode());
            insertToHead(newNode, minCounterDummyNode);
            keyToNodeMap.put(key, newNode);
        }

        private Node initDummyNode() {
            Node dummyNode = new Node(-1, -1, 0);
            dummyNode.next = dummyNode;
            dummyNode.prev = dummyNode;
            return dummyNode;
        }

        private void insertToHead(Node curNode, Node dummyNode) {
            curNode.prev = dummyNode;
            curNode.next = dummyNode.next;

            dummyNode.next.prev = curNode;
            dummyNode.next = curNode;
        }

        private void removeCurNode(Node curNode) {
            curNode.prev.next = curNode.next;
            curNode.next.prev = curNode.prev;

            curNode.prev = null;
            curNode.next = null;
        }

        private Node removeLRUFromCounterNode(int counter) {
            Node counterDummyNode = this.counterToDummyMap.get(counter);
            if (counterDummyNode == null) return null;

            Node removeNode = counterDummyNode.prev;
            removeCurNode(removeNode);
            return removeNode;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}