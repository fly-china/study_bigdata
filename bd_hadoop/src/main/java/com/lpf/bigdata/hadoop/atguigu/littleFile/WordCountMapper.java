package com.lpf.bigdata.hadoop.atguigu.littleFile;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 单词计算mapper类
 *
 * @author lipengfei
 * @create 2018-11-12 17:10
 **/
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    Text k = null;
    IntWritable v = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 1、获取该行内容
        String line = value.toString();
        // 2、按照空格切割字符串
        String[] words = line.split(" ");

        for (String word : words) {
            k = new Text(word);
            // 3、输出
            context.write(k, v);
        }


    }
}
