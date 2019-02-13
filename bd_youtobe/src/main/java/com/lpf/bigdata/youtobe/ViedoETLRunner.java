package com.lpf.bigdata.youtobe;

import com.lpf.bigdata.youtobe.etl.VideoETLMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * ETL之runner
 *
 * @author lipengfei
 * @create 2019-01-28 11:28
 **/
public class ViedoETLRunner implements Tool {

    private Configuration conf = null;

    @Override
    public int run(String[] args) throws Exception {

        conf = this.getConf();
        conf.set("inpath", args[0]);
        conf.set("outpath", args[1]);


        Job job = Job.getInstance(conf, "youtube-video-etl");
        job.setJarByClass(ViedoETLRunner.class);

        job.setMapperClass(VideoETLMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);

        job.setNumReduceTasks(0);

        this.initJobInputPath(job);
        this.initJobOutputPath(job);


        return job.waitForCompletion(true) ? 0 : 1;
    }

    private void initJobOutputPath(Job job) throws IOException {
        Configuration conf = job.getConfiguration();
        String outPathString = conf.get("outpath");
        FileSystem fs = FileSystem.get(conf);
        Path outPath = new Path(outPathString);
        if (fs.exists(outPath)) {
            fs.delete(outPath, true);
        }
        FileOutputFormat.setOutputPath(job, outPath);
    }

    private void initJobInputPath(Job job) throws IOException {
        Configuration conf = job.getConfiguration();
        String inPathString = conf.get("inpath");
        FileSystem fs = FileSystem.get(conf);
        Path inPath = new Path(inPathString);
        if (fs.exists(inPath)) {
            FileInputFormat.addInputPath(job, inPath);
        } else {
            throw new RuntimeException("HDFS 中该文件目录不存在:" + inPathString);
        }
    }

    @Override
    public void setConf(Configuration configuration) {
        this.conf = configuration;
    }

    @Override
    public Configuration getConf() {
        return this.conf;
    }


    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        ViedoETLRunner viedoETLRunner = new ViedoETLRunner();

        int runStatus = ToolRunner.run(viedoETLRunner, args);

        System.exit(runStatus);
    }


}
