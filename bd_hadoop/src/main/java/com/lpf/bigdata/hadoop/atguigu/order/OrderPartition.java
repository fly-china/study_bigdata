package com.lpf.bigdata.hadoop.atguigu.order;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import javax.validation.constraints.Null;

/**
 * @author lipengfei
 * @create 2018-11-16 15:09
 **/
public class OrderPartition extends Partitioner<OrderBean, NullWritable> {

    @Override
    public int getPartition(OrderBean orderBean, NullWritable nullWritable, int numReduceTasks) {
        return  (Integer.valueOf(orderBean.getOrderId().trim()) & 2147483647) % numReduceTasks;
    }
}
