package com.lpf.bigdata.hadoop.atguigu.order;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author lipengfei
 * @create 2018-11-15 20:03
 **/
public class OrderBean implements WritableComparable<OrderBean> {

    private String orderId;
    private double price;

    public OrderBean() {
        super();
    }

    public OrderBean( String	 	 orderId, double price) {
        this.orderId = orderId;
        this.price = price;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.orderId);
        dataOutput.writeDouble(this.price);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.orderId = dataInput.readUTF();
        this.price = dataInput.readDouble();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return orderId + "" + '\t' + price;
    }


    @Override
    public int compareTo(OrderBean o) {
        int result;
        if (Integer.valueOf(orderId.trim()) > Integer.valueOf(o.getOrderId().trim())) {
            result = 1;
        } else if (Integer.valueOf(orderId.trim()) < Integer.valueOf(o.getOrderId().trim())) {
            result = -1;
        } else {
            // 价格倒序排序
            result = price > o.getPrice() ? -1 : 1;
        }
        return result;
    }
}
