package com.lpf.interview.bytedance;

import java.util.Arrays;

/**
 * 给定一个数 n，如 23121；给定一组数字 A 如 {2,4,9}，求由 A 中元素组成的、小于 n 的最大数，如小于 23121 的最大数为 22999
 * 
 * 解题思路：
 * 1. 从高位到低位贪心选择，每一位尽量选择不超过目标数字对应位的最大值
 * 2. 如果某一位无法匹配，需要回退到前一位选择更小的数字
 * 3. 一旦某一位选择了更小的数字，后续位都可以填充数组中的最大值
 * 4. 如果最终结果等于目标数，需要回退让它变小
 */
public class LargestNumberLessThanN {

    public static void main(String[] args) {
        int[] digits = {1, 2, 3};
        int target = 23121;
        int result = findLargestNumber(digits, target);
        System.out.println(result);
    }

    /**
     * 查找由给定数字组成的、小于目标数的最大数
     * 
     * @param digits 可用的数字集合
     * @param target 目标数字
     * @return 小于目标数的最大数，如果不存在则返回-1
     */
    private static int findLargestNumber(int[] digits, int target) {
        Arrays.sort(digits);
        String targetStr = String.valueOf(target);
        char[] result = new char[targetStr.length()];
        char maxDigitChar = (char) (digits[digits.length - 1] + '0');
        
        boolean isDefinitelyLess = buildGreedyResult(digits, targetStr, result, maxDigitChar);
        
        // 如果构建失败，尝试使用更短的数字
        if (!isDefinitelyLess && new String(result).isEmpty()) {
            return buildShorterResult(digits, targetStr, maxDigitChar);
        }
        
        // 如果结果等于目标数，需要调整使其变小
        if (new String(result).equals(targetStr)) {
            makeResultSmaller(digits, result, maxDigitChar);
        }
        
        return Integer.parseInt(new String(result));
    }

    /**
     * 贪心构建结果：从高位到低位尽量选择最大的合法数字
     * 
     * @return true 表示已确保结果小于目标数，false 表示可能等于目标数
     */
    private static boolean buildGreedyResult(int[] digits, String targetStr, 
                                             char[] result, char maxDigitChar) {
        boolean isDefinitelyLess = false;
        
        for (int i = 0; i < targetStr.length(); i++) {
            // 如果已经确定结果更小，后续位直接填最大值
            if (isDefinitelyLess) {
                result[i] = maxDigitChar;
                continue;
            }
            
            int targetDigit = targetStr.charAt(i) - '0';
            int chosenDigit = findMaxLessOrEqual(digits, targetDigit);
            
            if (chosenDigit == -1) {
                // 当前位无法匹配，需要回退
                boolean backtrackSuccess = backtrack(digits, result, i, maxDigitChar);
                if (!backtrackSuccess) {
                    return false; // 回退失败，需要构建更短的结果
                }
                isDefinitelyLess = true;
            } else {
                result[i] = (char) (chosenDigit + '0');
                if (chosenDigit < targetDigit) {
                    isDefinitelyLess = true; // 选择了更小的数字，后续可以随意填充
                }
            }
        }
        
        return isDefinitelyLess;
    }

    /**
     * 回退逻辑：从当前位置往前找到第一个可以减小的位置
     * 
     * @param position 当前失败的位置
     * @return true 表示回退成功，false 表示回退失败
     */
    private static boolean backtrack(int[] digits, char[] result, 
                                     int position, char maxDigitChar) {
        for (int i = position - 1; i >= 0; i--) {
            int currentDigit = result[i] - '0';
            int smallerDigit = findMaxLessThan(digits, currentDigit);
            
            if (smallerDigit != -1) {
                // 找到可以减小的位置，更新当前位并填充后续位
                result[i] = (char) (smallerDigit + '0');
                fillWithMax(result, i + 1, maxDigitChar);
                return true;
            }
        }
        return false; // 无法回退
    }

    /**
     * 构建比目标数短一位的最大结果
     */
    private static int buildShorterResult(int[] digits, String targetStr, char maxDigitChar) {
        if (targetStr.length() == 1) {
            return -1; // 无法构建更短的数字
        }
        char[] shorterResult = new char[targetStr.length() - 1];
        Arrays.fill(shorterResult, maxDigitChar);
        return Integer.parseInt(new String(shorterResult));
    }

    /**
     * 让结果变小：从后往前找到第一个可以减小的位置
     */
    private static void makeResultSmaller(int[] digits, char[] result, char maxDigitChar) {
        for (int i = result.length - 1; i >= 0; i--) {
            int currentDigit = result[i] - '0';
            int smallerDigit = findMaxLessThan(digits, currentDigit);
            
            if (smallerDigit != -1) {
                result[i] = (char) (smallerDigit + '0');
                fillWithMax(result, i + 1, maxDigitChar);
                break;
            }
        }
    }

    /**
     * 从后续位置开始填充最大值
     */
    private static void fillWithMax(char[] result, int fromIndex, char maxDigitChar) {
        for (int i = fromIndex; i < result.length; i++) {
            result[i] = maxDigitChar;
        }
    }

    /**
     * 查找数组中小于等于目标值的最大数字
     * 
     * @return 找到的数字，如果不存在则返回-1
     */
    private static int findMaxLessOrEqual(int[] digits, int target) {
        int result = -1;
        for (int digit : digits) {
            if (digit <= target) {
                result = Math.max(result, digit);
            }
        }
        return result;
    }

    /**
     * 查找数组中严格小于目标值的最大数字
     * 
     * @return 找到的数字，如果不存在则返回-1
     */
    private static int findMaxLessThan(int[] digits, int target) {
        int result = -1;
        for (int digit : digits) {
            if (digit < target) {
                result = Math.max(result, digit);
            }
        }
        return result;
    }
}
