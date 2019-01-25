package com.lpf.bigdata.hadoop.atguigu.order;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author lipengfei
 * @create 2018-11-16 14:44
 **/
public class OrderMapper  extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

    OrderBean k = null;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        if(StringUtils.isNotBlank(line)){
            String[] split = line.split("\t");
            String orderId = split[0];
            String price = split[2];
            k = new OrderBean(orderId.trim(), Double.valueOf(price));

            context.write(k , NullWritable.get());
        }
    }

}
