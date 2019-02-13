package com.lpf.bigdata.hadoop.atguigu.custom.outputFormat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author lipengfei
 * @create 2018-11-21 17:21
 **/
public class FilterMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    Text k = null;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        k = new Text(line);

        context.write(k, NullWritable.get());
    }
}
