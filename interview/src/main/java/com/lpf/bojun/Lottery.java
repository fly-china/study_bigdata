package com.lpf.bojun;

import java.util.ArrayList;
import java.util.List; 
import java.util.Random; 
 
// 奖品类，用于表示奖品的相关属性
class Prize {
    private int quantity; // 奖品的数量
    private double probability; // 奖品的中奖概率
 
    // 奖品类的构造函数，用于初始化奖品的数量和中奖概率
    public Prize(int quantity, double probability) {
        this.quantity = quantity; 
        this.probability = probability; 
    }
 
    // 获取奖品数量的方法
    public int getQuantity() {
        return quantity; 
    }
 
    // 设置奖品数量的方法
    public void setQuantity(int quantity) {
        this.quantity = quantity; 
    }
 
    // 获取奖品中奖概率的方法
    public double getProbability() {
        return probability; 
    }
 
    // 设置奖品中奖概率的方法
    public void setProbability(double probability) {
        this.probability = probability; 
    }
}
 
// 抽奖类，包含抽奖相关的逻辑和操作
public class Lottery {
    public static void main(String[] args) {
        // 初始化奖品列表，用来存放所有的奖品信息
        List<Prize> prizes = new ArrayList<>(); 
        // 向奖品列表中添加奖品，每个奖品包含数量和中奖概率
        prizes.add(new Prize(5, 0.1)); 
        prizes.add(new Prize(20, 5.1)); 
        prizes.add(new Prize(30, 8.4)); 
        prizes.add(new Prize(40, 15.4)); 
        prizes.add(new Prize(100, 21.5)); 
        prizes.add(new Prize(200, 49.5)); 
 
        // 模拟抽奖过程，这里进行10次抽奖模拟
        for (int i = 0; i < 10; i++) {
            // 调用抽奖方法，根据奖品的概率抽取奖品，返回抽中的奖品索引
            int prizeIndex = drawPrize(prizes); 
            if (prizeIndex!= -1) {
                // 如果抽中奖品，打印出抽中的是第几种奖品
                System.out.println("恭喜抽中第 " + (prizeIndex + 1) + " 种奖品"); 
                // 减少抽中奖品的数量，因为已经被抽中一次
                prizes.get(prizeIndex).setQuantity(prizes.get(prizeIndex).getQuantity() - 1); 
                if (prizes.get(prizeIndex).getQuantity() == 0) {
                    // 如果奖品数量变为0，从奖品列表中移除该奖品
                    prizes.remove(prizeIndex);
                }
            } else {
                // 如果没有抽中奖品（返回 -1时），打印提示信息
                System.out.println("没有可抽的奖品了"); 
            }
        }
    }
 
    // 抽奖方法，根据奖品的概率进行抽奖，返回抽中的奖品索引，-1表示没有可抽的奖品
    public static int drawPrize(List<Prize> prizes) {
        if (prizes.isEmpty()) {
            // 如果奖品列表为空，直接返回 -1，表示没有可抽的奖品
            return -1; 
        }
        Random random = new Random(); 
        double totalProbability = 0; 
        // 计算所有奖品的中奖概率总和
        for (Prize prize : prizes) {
            totalProbability += prize.getProbability(); 
        }
        // 生成一个0到总概率之间的随机数，用于确定抽中的奖品
        double randomValue = random.nextDouble() * totalProbability; 
        double cumulativeProbability = 0; 
        // 遍历奖品列表，根据随机数所在的概率区间确定抽中的奖品
        for (int i = 0; i < prizes.size(); i++) {
            cumulativeProbability += prizes.get(i).getProbability(); 
            if (randomValue < cumulativeProbability) {
                return i; 
            }
        }
        return -1; 
    }
 
    // 重新计算剩余奖品中奖概率的方法
    public static void recalculateProbabilities(List<Prize> prizes) {
        double totalQuantity = 0; 
        // 计算剩余奖品的总数量
        for (Prize prize : prizes) {
            totalQuantity += prize.getQuantity(); 
        }
        // 重新计算每个剩余奖品的中奖概率，根据其数量占总数量的比例
        for (Prize prize : prizes) {
            prize.setProbability((double) prize.getQuantity() / totalQuantity * 100); 
        }
    }
}