package com.lpf.bigdata.hadoop.ch02;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 查找最高气温mapper类
 * LongWritable：输入的Key      行号
 * Text：输入的value            一行内容
 * Text：输出的key              年份
 * IntWritable：输出的value     温度
 *
 * @author lipengfei
 * @create 2018-10-23 15:43
 **/
public class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private static final int MISSING = 9999;

    @Override
    public void map(LongWritable longWritable, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String year = line.substring(15, 19);
        int airTemperature;
        // 87-92为带正负号的温度
        airTemperature = Integer.parseInt(line.substring(87, 92));

        String quality = line.substring(92, 93);
        if (airTemperature != MISSING && quality.matches("[01459]")) {
            context.write(new Text(year), new IntWritable(airTemperature));
        }
//        System.out.println("************************* mapper执行完成");
    }


    public static void main(String[] args) {
        String tmp = "0029029070999991901010106004+64333+023450FM-12+000599999V0202701N015919999999N0000001N9-00781+99999102001ADDGF108991999999999999999999";

        String quality = tmp.substring(87, 92);
        System.out.println(quality);
        int airTemperature = Integer.parseInt(tmp.substring(88, 92));
        System.out.println(airTemperature);

        System.out.println(tmp.substring(92, 93).matches("[01459]"));

    }


}
