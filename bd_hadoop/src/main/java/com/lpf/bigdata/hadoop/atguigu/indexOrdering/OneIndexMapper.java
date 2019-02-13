package com.lpf.bigdata.hadoop.atguigu.indexOrdering;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author lipengfei
 * @create 2018-11-22 17:16
 **/
public class OneIndexMapper extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        if (StringUtils.isNotBlank(line)) {

            FileSplit fileSplit = (FileSplit) context.getInputSplit();
            String fileName = fileSplit.getPath().getName();

            String[] fields = line.split("\t");

//            atguigu	ss
//            atguigu	pingping
            for (String field : fields) {
                context.write(new Text(field), new Text(fileName + "---" + 1));
            }
        }


    }
}
