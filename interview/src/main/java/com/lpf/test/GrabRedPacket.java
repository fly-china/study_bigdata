package com.lpf.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Random;

/**
 * @author lipengfei
 * @create 2025-03-07 11:05
 **/
public class GrabRedPacket {

    public static void main(String[] args) {
        int[] amount = getAmount(10, 500);
        int finalAmount = 0;
        for (int i = 0; i < amount.length; i++) {
            finalAmount += amount[i];
        }
        System.out.println(JSONObject.toJSON(amount));
        System.out.println("最终发出金额：" + finalAmount);
    }

    private static int[] getAmount(int nums, int totalAmount) {
        if (nums <= 0 || totalAmount <= 0) {
            return null;
        }
        int[] res = new int[nums];
        int avl = totalAmount / nums; // 单份金额

        Random random = new Random(System.currentTimeMillis());
        int balance = totalAmount;

        for (int i = 0; i < nums; i++) {
            double rdm = random.nextDouble(); // 0 - 1
            int v = (int) (((rdm * 100) - 50) * 2 / 5);

            int amount = (avl * (100 + v)) / 100;
            if (balance >= amount) {
                res[i] = amount;
                balance -= amount;
            } else {
                res[i] = balance;
            }

            if (i == nums - 1 && balance > 0) {
                res[i] = amount + balance;
                balance = 0;
            }
        }

        return res;
    }
}
