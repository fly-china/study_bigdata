package com.lpf.bigdata.hadoop.atguigu.phoneData.sort;

import com.lpf.bigdata.hadoop.atguigu.phoneData.FlowBean;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 流量计算mapper(流量信息为KEY，手机号码为value)
 *
 * @author lipengfei
 * @create 2018-11-03 16:32
 **/
public class FlowSumSortMapper extends Mapper<LongWritable, Text, FlowBeanComparable,Text> {

    FlowBeanComparable k = null;
    Text v = null;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // 1363157993055 	13560436666	C4-17-FE-BA-DE-D9:CMCC	120.196.100.99		18	15	1116		954		200
        // 获取该行内容
        String line = value.toString();

        if (StringUtils.isNotBlank(line)) {
            // 按制表符切割
            String[] temp = line.split("\t");
            String phoneNum = temp[1];
            Long upFlow = Long.parseLong(temp[temp.length - 3]);// 上行流量
            Long downFlow = Long.parseLong(temp[temp.length - 2]);// 下行流量

            k = new FlowBeanComparable(upFlow, downFlow);
            v = new Text(phoneNum);

            context.write(k, v);
        }
    }

}
