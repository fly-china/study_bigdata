package com.lpf.jdk8.lambda.SortDemo;




public class OrderVO {

    private OrderNum orderNum;

    public OrderVO(OrderNum orderNum) {
        this.orderNum = orderNum;
    }

    public OrderNum getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(OrderNum orderNum) {
        this.orderNum = orderNum;
    }


   public  int getOrderMin(){
        return this.orderNum.getMin();
   }
}
