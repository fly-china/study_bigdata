package com.lpf.bigdata.hadoop.atguigu.custom.inputFormat;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author lipengfei
 * @create 2018-11-20 17:41
 **/
public class SequenceFileMapper extends Mapper<NullWritable, BytesWritable, Text, BytesWritable> {

    Text k = new Text();

    @Override
    protected void setup(Mapper<NullWritable, BytesWritable, Text, BytesWritable>.Context context) throws IOException, InterruptedException {
        // 1、获取切片信息
        FileSplit split = (FileSplit) context.getInputSplit();
        // 2、获取切片名称
        String name = split.getPath().toString();
        // 3、设置key输出
        k.set(name);
    }

    @Override
    protected void map(NullWritable key, BytesWritable value, Context context) throws IOException, InterruptedException {
        System.out.println("_____________________-");
        context.write(k,value);
    }
}
