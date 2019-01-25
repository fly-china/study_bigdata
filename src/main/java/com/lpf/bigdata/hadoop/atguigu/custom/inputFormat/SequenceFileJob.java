package com.lpf.bigdata.hadoop.atguigu.custom.inputFormat;

import com.lpf.bigdata.hadoop.atguigu.order.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

/**
 * @author lipengfei
 * @create 2018-11-20 18:08
 **/
public class SequenceFileJob {

    public static void main(String[] args) throws Exception {

        String inputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/cusInput/";
        String outputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/cusIutput/";
        outputPath = outputPath + System.currentTimeMillis() + "/";

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(SequenceFileJob.class);
        job.setMapperClass(SequenceFileMapper.class);
        job.setReducerClass(SequenceFileReducer.class);

        // 设置自定义的输入的inputFormat
        job.setInputFormatClass(WholeFileInputformat.class);
        // 设置输出的outputFormat
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);

        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);


    }
}
