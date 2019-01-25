package com.lpf.bigdata.hadoop.atguigu.phoneData.sort;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 流量计算Bean序列化类
 * 作为mapper的key，实现WritableComparable类的compareTo接口
 *
 * @author lipengfei
 * @create 2018-11-03 16:26
 **/
public class FlowBeanComparable implements WritableComparable<FlowBeanComparable> {

    private long upFlow;// 上行流量
    private long downFlow;// 下行流量
    private long sumFlow;// 总流量

    // 必须要有空参构造，为了后续反射用
    public FlowBeanComparable() {
        super();
    }

    public FlowBeanComparable(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    public void set(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    // 序列化
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    // 反序列化
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.upFlow = dataInput.readLong();
        this.downFlow = dataInput.readLong();
        this.sumFlow = dataInput.readLong();
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    /**
     * 按照从大至小的倒序排列
     * @param otherObj
     * @return
     */
    @Override
    public int compareTo(FlowBeanComparable otherObj) {
        return this.sumFlow > otherObj.getSumFlow() ? -1 : 1;
    }
}
