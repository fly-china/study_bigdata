// 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
//
// 每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。 
//
// 返回到达终点需要的*最小*移动次数。
//
// 示例 1: 
//
// 
//输入: target = 3
//输出: 2
//解释:
//第一次移动，从 0 到 1 。
//第二次移动，从 1 到 3 。
// 
//
// 示例 2: 
//
// 
//输入: target = 2
//输出: 3
//解释:
//第一次移动，从 0 到 1 。
//第二次移动，从 1 到 -1 。
//第三次移动，从 -1 到 2 。
// 
//
// 注意: 
//
// 
// target是在[-10^9, 10^9]范围中的非零整数。 
// 
// Related Topics 数学

package com.lpf.leetcode.editor.cn;

public class ReachANumber {
    public static void main(String[] args) {
        Solution solution = new ReachANumber().new Solution();
        int number = solution.reachNumber(1);
        System.out.println(number);
    }

    /**
     * 解题思路：
     * 1. target虽然是非零整数，可正可负。不过若target取负数时，相比于target的绝对值，对移动方向取反，
     *      便可得到同样的路径。即最小移动次数相同。故可转化为 获取target绝对值的解法
     * 2. sum = 1 + 2 + 3 + ... + n
     *      a.若sum < target，说明n较小，继续向上累加n+1 、n+2 ... 计算sum和target的大小;
     *      b.若sum = target，这绝对是最短路径了；
     *      c.若sum > target，说明继续向上累加n+1 、n+2 ... 后的sum值，大过了target的值。
     *          便需计算，是否可能将先前的某一步取反方向，可使 1 + 2 + 3 ...-x + (x+1) + ...+ n = sum = target
     *          将第x步取反后的sum值定义为newSum，即target=newSum。 target = sum -2x
     *          1） 即sum - target 必须等于2x才满足条件，换一种说法：sum - target 必须是偶数。
     *          2） 如果sum - target是奇数，则不符合条件。需要继续向上累加。
     * 3. 为什么满足 target = sum -2x 的符合“最小移动次数”？
     *      一直正向移动，从1 -> n累加上去，直到target=sum，不走回头路肯定是最小移动次数
     *      满足target = sum -2x达到终点，是因为不走回头路完全达不到终点，争取到仅走一步回头路。故肯定是最小移动次数
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reachNumber(int target) {
            if (target == 0) {
                return 0;
            }
            target = Math.abs(target);

            int sum = 0;
            int result = 1;
//            while (true) {
//                sum += result;
//                if (sum == target || (sum > target && (sum - target) % 2 == 0)) {
//                    return result;
//                }
//                result++;
//            }

            while (target > sum || (sum - target) % 2 != 0) {
                sum += result;
                result++;
            }
            return result - 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}