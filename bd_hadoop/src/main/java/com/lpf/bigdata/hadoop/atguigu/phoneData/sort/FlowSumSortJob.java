package com.lpf.bigdata.hadoop.atguigu.phoneData.sort;

import com.lpf.bigdata.hadoop.atguigu.phoneData.FlowBean;
import com.lpf.bigdata.hadoop.atguigu.phoneData.FlowSumMapper;
import com.lpf.bigdata.hadoop.atguigu.phoneData.FlowSumReducer;
import com.lpf.bigdata.hadoop.atguigu.phoneData.PhoneNumPartition;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 流量汇总调度类
 * 要求：将统计结果按照总流量倒序排序(全排序)
 *
 * @author lipengfei
 * @create 2018-11-03 16:48
 **/
public class FlowSumSortJob {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        String inputPath1 = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/flow/flow.txt";
//        String inputPath = "/Users/ran/Desktop/info.log";
        String outputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/flow/sort/";
        outputPath =  outputPath + System.currentTimeMillis() + "/";

        Configuration conf = new Configuration();
//        conf.set("fs.defaultFS", "hdfs://hadoop01:8020/");
//        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        Job job = Job.getInstance(conf);

        job.setJarByClass(FlowSumSortJob.class);
        job.setMapperClass(FlowSumSortMapper.class);
        job.setReducerClass(FlowSumSortReducer.class);

        FileInputFormat.addInputPath(job, new Path(inputPath1));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));


        job.setMapOutputKeyClass(FlowBeanComparable.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBeanComparable.class);

        // ************* 实战7.2.4 设置Partition分区 **************
        job.setPartitionerClass(PhoneNumSortPartition.class);
        job.setNumReduceTasks(5);
        // ************* 实战7.2.4 设置Partition分区 **************

        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);


    }
}
