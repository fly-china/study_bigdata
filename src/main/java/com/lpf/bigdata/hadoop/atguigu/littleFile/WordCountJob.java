package com.lpf.bigdata.hadoop.atguigu.littleFile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 单词统计
 *
 * @author lipengfei
 * @create 2018-11-12 17:19
 **/
public class WordCountJob {


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        String inputPath1 = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/wordCount";
        String outputPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/wordCount/";
        outputPath = outputPath + System.currentTimeMillis() + "/";

        // 1、初始化job
        Configuration conf = new Configuration();
        // 1.1、开启 map 端输出压缩
        conf.setBoolean("mapreduce.map.output.compress", true);
        // 设置 map 端输出压缩方式
        conf.setClass("mapreduce.map.output.compress.codec", BZip2Codec.class, CompressionCodec.class);
//        conf.setClass("mapreduce.map.output.compress.codec", GzipCodec.class, CompressionCodec.class);
//        conf.setClass("mapreduce.map.output.compress.codec", DefaultCodec.class, CompressionCodec.class);

        Job job = Job.getInstance(conf);

        // 2-3、设置 jar 加载路径，指定MapperClass和ReducerClass的class文件
        job.setJarByClass(WordCountJob.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // ************* 小文件合并设置 **************
        job.setInputFormatClass(CombineTextInputFormat.class);
//        NLineInputFormat.setNumLinesPerSplit(job, 3);
//        job.setInputFormatClass(NLineInputFormat.class);
        CombineTextInputFormat.setMinInputSplitSize(job, 2 * 1024 * 1024);//分片最小：2m
        CombineTextInputFormat.setMaxInputSplitSize(job, 4 * 1024 * 1024);//分片最大：2m
        // ************* 小文件合并设置 **************

        // 4、设置输入和输出路径
        FileInputFormat.addInputPath(job, new Path(inputPath1));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        // 4.1、设置 reduce 端输出压缩开启
        FileOutputFormat.setCompressOutput(job, true);
        // 设置压缩的方式
//        FileOutputFormat.setOutputCompressorClass(job, BZip2Codec.class);
        FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
//        FileOutputFormat.setOutputCompressorClass(job, DefaultCodec.class);

        // 5、指定Map输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 6、指定Reduce输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 7、提交任务
        boolean flag = job.waitForCompletion(true);
        long end = System.currentTimeMillis();
        System.out.println("**************处理任务耗时：" + (end - start));
        System.exit(flag ? 0 : 1);

//        Integer.MAX_VALUE;
    }
}
