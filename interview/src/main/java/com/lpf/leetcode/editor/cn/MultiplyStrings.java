//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
//
// 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。 
//
// 
//
// 示例 1: 
//
// 
//输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 
//输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 
//
// 提示： 
//
// 
// 1 <= num1.length, num2.length <= 200 
// num1 和 num2 只能由数字组成。 
// num1 和 num2 都不包含任何前导零，除了数字0本身。 
// 
//
// Related Topics 数学 字符串 模拟 👍 1388 👎 0


package com.lpf.leetcode.editor.cn;

import java.math.BigInteger;

/**
 * [43]-字符串相乘
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        Solution solution = new MultiplyStrings().new Solution();
//        System.out.println(solution.multiply("34", "83"));
        System.out.println("123".charAt(0));

        String num1 = "99";
        String num2 = "199"; // 6688+9998888=
        System.out.println("自定义 add 方法，res=" + solution.add(num1, num2));
        System.out.println("计算器 add 方法，res=" + (Long.parseLong(num1) + Long.parseLong(num2)));
        System.out.println("--------------");
        System.out.println("计算器 multiply 方法，res=" + solution.multiply(num1, num2));
        System.out.println("计算器 multiply 方法，res=" + new BigInteger(num1).multiply(new BigInteger(num2)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 时间复杂度：O(mn+n*n)
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }
            // 99 * 199
            String res = "0";
            for (int i = 0; i < num2.length(); i++) {
                int b = num2.charAt(num2.length() - i - 1) - '0';

                StringBuilder multiStr = new StringBuilder();
                int carry = 0;
                for (int j = 0; j < num1.length(); j++) {
                    int a = num1.charAt(num1.length() - j - 1) - '0';

                    int tmp = a * b + carry;
                    multiStr.append(tmp % 10);
                    carry = tmp / 10;
                }
                if (carry > 0) {
                    multiStr.append(carry);
                }

                multiStr.reverse();

                for (int j = 0; j < i; j++) {
                    multiStr.append("0"); // 补位 0
                }

                res = add(multiStr.toString(), res);
            }

            return res;
        }

        public String add(String a, String b) {

            String res = "";
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
                int ac = i >= a.length() ? 0 : a.charAt(a.length() - i - 1) - '0';
                int bc = i >= b.length() ? 0 : b.charAt(b.length() - i - 1) - '0';

                int sum = ac + bc + carry;
//                res = (sum % 10) + "" + res;
                sb.append(sum % 10);
                carry = sum / 10;
            }

            if (carry == 1) {
//                res = "1" + res;
                sb.append("1");
            }

            return sb.reverse().toString();
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}