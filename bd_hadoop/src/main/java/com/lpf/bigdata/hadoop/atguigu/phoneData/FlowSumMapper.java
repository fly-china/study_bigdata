package com.lpf.bigdata.hadoop.atguigu.phoneData;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 流量计算mapper
 *
 * @author lipengfei
 * @create 2018-11-03 16:32
 **/
public class FlowSumMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    Text k = null;
    FlowBean v = null;

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


            k = new Text(phoneNum);
            v = new FlowBean(upFlow, downFlow);

            context.write(k, v);
        }
    }
}
