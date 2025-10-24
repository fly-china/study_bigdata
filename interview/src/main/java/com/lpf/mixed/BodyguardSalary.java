package com.lpf.mixed;

import java.util.Scanner;

public class BodyguardSalary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象用于读取输入
        int testCount = scanner.nextInt(); // 读取测试数据的组数，不超过1000组

        // 循环处理每一组测试数据
        for (int i = 0; i < testCount; i++) {
            int days = scanner.nextInt(); // 读取每组数据的工作天数，范围1到1000
            System.out.println(calculateTotalSalary(days)); // 调用方法计算并输出报酬总额
        }

        scanner.close(); // 关闭Scanner对象
    }

    // 方法：计算保镖在给定天数内能够得到的总报酬
    private static int calculateTotalSalary(int days) {
        int totalSalary = 0; // 初始化总报酬为0
        int currentSalary = 1; // 初始化当前报酬为1元
        int period = 1; // 初始化周期计数器，用于跟踪当前报酬的天数

        // 循环遍历每一天，直到达到给定的工作天数
        for (int day = 1; day <= days; day++) {
            totalSalary += currentSalary; // 将当前报酬加到总报酬中
            period++; // 周期计数器递增

            // 如果周期计数器超过当前报酬，意味着需要增加报酬并重置周期计数器
            if (period > currentSalary) {
                currentSalary++; // 报酬增加
                period = 1; // 周期计数器重置为1
            }
        }

        return totalSalary; // 返回计算出的总报酬
    }
}