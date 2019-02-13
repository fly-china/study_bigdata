package com.lpf.bigdata.hadoop.atguigu.phoneData;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 流量汇总调度类
 *
 * @author lipengfei
 * @create 2018-11-03 16:48
 **/
public class FlowSumJob {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        String inputPath1 = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/flow/flow.txt";
//        String inputPath = "/Users/ran/Desktop/info.log";
        String outputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/flow/partition/";
        outputPath =  outputPath + System.currentTimeMillis() + "/";

        Configuration conf = new Configuration();
//        conf.set("fs.defaultFS", "hdfs://hadoop01:8020/");
//        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        Job job = Job.getInstance(conf);

        job.setJarByClass(FlowSumJob.class);
        job.setMapperClass(FlowSumMapper.class);
        job.setReducerClass(FlowSumReducer.class);
//        job.setCombinerClass(FlowSumReducer.class); // combiner汇总

//        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileInputFormat.addInputPath(job, new Path(inputPath1));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);


        // ************* 实战7.2.2 设置Partition分区 **************
        job.setPartitionerClass(PhoneNumPartition.class);
        /**
         *  自定义Partitioner中共有5个分区
         *  1、NumReduceTasks默认为1，相当于不分区，只有一份输出文件
         *  2、设置NumReduceTasks为2，则有一部分数据无处安放，会报异常
         *  3、设置NumReduceTasks为5，生成分区对应的5份输出文件
         *  4、设置NumReduceTasks大于5，会多产生几个空的输出文件part-r-00006
         */
        job.setNumReduceTasks(5);
        // ************* 设置Partition分区 **************

        boolean flag = job.waitForCompletion(true);
        System.exit(flag ? 0 : 1);


    }
}
