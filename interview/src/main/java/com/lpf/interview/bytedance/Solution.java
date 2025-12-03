package com.lpf.interview.bytedance;

import java.util.*;

public class Solution {
    
    public static String findMaxNumberLessThanN(int n, int[] A) {
        // 对A进行排序，方便查找
        Arrays.sort(A);
        
        String nStr = String.valueOf(n);
        List<Integer> result = new ArrayList<>();
        
        // 尝试构建数字
        if (backtrack(nStr, A, result, 0, false)) {
            // 构建成功，将结果转换为字符串
            StringBuilder sb = new StringBuilder();
            for (int digit : result) {
                sb.append(digit);
            }
            return sb.toString();
        } else {
            // 如果构建失败（例如n=1000，A={2,3,4}），返回len-1位的最大数
            return buildMaxNumber(nStr.length() - 1, A);
        }
    }
    
    private static boolean backtrack(String nStr, int[] A, List<Integer> result, int index, boolean alreadyLess) {
        if (index == nStr.length()) {
            return true; // 成功构建完整数字
        }
        
        int targetDigit = nStr.charAt(index) - '0';
        
        if (alreadyLess) {
            // 如果前面已经有位小于原数，当前位取最大值
            result.add(A[A.length - 1]);
            return backtrack(nStr, A, result, index + 1, true);
        } else {
            // 查找小于等于targetDigit的最大数字
            int idx = binarySearchLessOrEqual(A, targetDigit);
            
            // 尝试从大到小选择合适的数字
            for (int i = idx; i >= 0; i--) {
                int digit = A[i];
                result.add(digit);
                
                if (digit < targetDigit) {
                    // 当前位小于目标位，后面所有位都可以取最大值
                    if (backtrack(nStr, A, result, index + 1, true)) {
                        return true;
                    }
                } else if (digit == targetDigit) {
                    // 当前位等于目标位，继续处理下一位
                    if (backtrack(nStr, A, result, index + 1, false)) {
                        return true;
                    }
                }
                
                // 回溯，移除当前位
                result.remove(result.size() - 1);
            }
            
            return false; // 没有找到合适的数字
        }
    }
    
    private static int binarySearchLessOrEqual(int[] A, int target) {
        int left = 0, right = A.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] <= target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    private static String buildMaxNumber(int length, int[] A) {
        if (length <= 0) return ""; // 特殊情况处理
        
        StringBuilder sb = new StringBuilder();
        int maxDigit = A[A.length - 1];
        for (int i = 0; i < length; i++) {
            sb.append(maxDigit);
        }
        return sb.toString();
    }
    
    // 更简洁的迭代版本
    public static String findMaxNumberLessThanN2(int n, int[] A) {
        Arrays.sort(A);
        String nStr = String.valueOf(n);
        StringBuilder result = new StringBuilder();
        boolean alreadyLess = false;
        
        for (int i = 0; i < nStr.length(); i++) {
            int targetDigit = nStr.charAt(i) - '0';
            
            if (alreadyLess) {
                // 前面已经有位小于原数，当前位取最大值
                result.append(A[A.length - 1]);
                continue;
            }
            
            // 查找小于等于targetDigit的最大数字
            int digit = findMaxDigitNotExceed(A, targetDigit);
            
            if (digit == -1) {
                // 当前位没有合适的数字，需要回溯
                if (result.length() == 0) {
                    // 如果是第一位，直接构建len-1位的最大数
                    return buildMaxNumber(nStr.length() - 1, A);
                } else {
                    // 回溯：找到可以减小的前一位
                    int j = result.length() - 1;
                    while (j >= 0) {
                        char currentChar = result.charAt(j);
                        int currentDigit = currentChar - '0';
                        int smallerDigit = findMaxDigitLessThan(A, currentDigit);
                        
                        if (smallerDigit != -1) {
                            // 找到可以减小的位
                            result.setCharAt(j, (char) (smallerDigit + '0'));
                            // 后面的位都填充最大值
                            for (int k = j + 1; k < result.length(); k++) {
                                result.setCharAt(k, (char) (A[A.length - 1] + '0'));
                            }
                            // 如果还有剩余的位，继续填充最大值
                            while (result.length() < nStr.length()) {
                                result.append(A[A.length - 1]);
                            }
                            return result.toString();
                        } else {
                            // 当前位不能减小，继续向前回溯
                            j--;
                        }
                    }
                    
                    // 所有位都不能减小，构建len-1位的最大数
                    return buildMaxNumber(nStr.length() - 1, A);
                }
            }
            
            result.append(digit);
            if (digit < targetDigit) {
                alreadyLess = true;
            }
        }
        
        // 检查构建的数字是否等于n
        if (result.toString().equals(nStr)) {
            // 需要找到一个更小的数
            return findSmallerNumber(result.toString(), A);
        }
        
        return result.toString();
    }
    
    private static int findMaxDigitNotExceed(int[] A, int target) {
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] <= target) {
                return A[i];
            }
        }
        return -1;
    }
    
    private static int findMaxDigitLessThan(int[] A, int target) {
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] < target) {
                return A[i];
            }
        }
        return -1;
    }
    
    private static String findSmallerNumber(String numStr, int[] A) {
        // 从最后一位开始，找到可以减小的位
        StringBuilder result = new StringBuilder(numStr);
        for (int i = numStr.length() - 1; i >= 0; i--) {
            int currentDigit = numStr.charAt(i) - '0';
            int smallerDigit = findMaxDigitLessThan(A, currentDigit);
            
            if (smallerDigit != -1) {
                // 找到可以减小的位
                result.setCharAt(i, (char) (smallerDigit + '0'));
                // 后面的位都填充最大值
                for (int j = i + 1; j < result.length(); j++) {
                    result.setCharAt(j, (char) (A[A.length - 1] + '0'));
                }
                return result.toString();
            }
        }
        
        // 所有位都不能减小，构建len-1位的最大数
        return buildMaxNumber(numStr.length() - 1, A);
    }
    
    public static void main(String[] args) {
        // 测试用例
        int n = 23121;
        int[] A = {1,2,3,0};
        
//        System.out.println("回溯版本: " + findMaxNumberLessThanN(n, A)); // 应该输出 22999
        System.out.println("迭代版本: " + findMaxNumberLessThanN2(n, A)); // 应该输出 22999
        

    }
}