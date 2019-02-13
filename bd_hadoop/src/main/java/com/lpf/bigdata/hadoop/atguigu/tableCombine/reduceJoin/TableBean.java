package com.lpf.bigdata.hadoop.atguigu.tableCombine.reduceJoin;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author lipengfei
 * @create 2018-11-21 19:20
 **/
public class TableBean implements WritableComparable<TableBean> {
    private String order_id; // 订单 id
    private String p_id; // 产品 id
    private int amount; // 产品数量
    private String pname; // 产品名称
    private String flag;// 表的标记;0-订单表；1-品牌标

    public TableBean() {
        super();
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.order_id);
        dataOutput.writeUTF(this.p_id);
        dataOutput.writeInt(this.amount);
        dataOutput.writeUTF(this.pname);
        dataOutput.writeUTF(this.flag);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.order_id = dataInput.readUTF();
        this.p_id = dataInput.readUTF();
        this.amount = dataInput.readInt();
        this.pname = dataInput.readUTF();
        this.flag = dataInput.readUTF();
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return order_id + "\t" + pname + "\t" + amount + "\t";
    }

    @Override
    public int compareTo(TableBean other) {
        int anInt = Integer.parseInt(other.getOrder_id());
        int thisInt = Integer.parseInt(this.getOrder_id());


        return (anInt - thisInt);
    }
}
