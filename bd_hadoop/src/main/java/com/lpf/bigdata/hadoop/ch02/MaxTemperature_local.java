package com.lpf.bigdata.hadoop.ch02;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 获取最大温度
 *
 * @author lipengfei
 * @create 2018-10-23 16:32
 **/
public class MaxTemperature_local {

    /**
     * 构建任务"七步"走
     * 1、构建Job（）对象
     * 2、设置InputPath路径（InputPath既可以是单个文件，也可是目录-目录下所有文件；同时也可设置多inputPath）
     * 3、设置outPath路径（outPut必须是"一个"不存在的"目录"）
     * 4、指定MapperClass和ReducerClass的class文件
     * [5]、若Map函数的输入类型和reducer的输入类型不一致，请指定MapOutputkeyClass和MapOutputvalueClass
     * 6、指定最终reduce输出类型OutputkeyClass和MapOutputvalueClass
     * 7、执行任务
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        String inputPath1 = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/ncdc/";
        String outputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/";
        outputPath = outputPath + System.currentTimeMillis();


        Job job = Job.getInstance();
        job.setJarByClass(MaxTemperature_local.class);
        job.setJobName("Max temperature");

        // 步骤2+步骤3
        FileInputFormat.addInputPath(job, new Path(inputPath1));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        // 步骤4
        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        // 步骤5可省略
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 步骤6
        job.setOutputKeyClass(Text.class);
        job.setOutputKeyClass(IntWritable.class);

        // 步骤7
        boolean flag = job.waitForCompletion(true);
        System.out.println("任务执行状态为：" + flag);

    }
}
