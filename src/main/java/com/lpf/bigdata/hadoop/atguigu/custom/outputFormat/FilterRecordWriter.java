package com.lpf.bigdata.hadoop.atguigu.custom.outputFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @author lipengfei
 * @create 2018-11-21 16:55
 **/
public class FilterRecordWriter extends RecordWriter<Text, NullWritable> {

    private static final String ranPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/cusOutput/file/ran.log";
    private static final String otherPath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/output/cusOutput/file/other.log";

    Configuration configuration;
    FSDataOutputStream ranFos;
    FSDataOutputStream otherFos;

    public FilterRecordWriter(TaskAttemptContext context) {

        configuration = context.getConfiguration();
        try {
            FileSystem fileSystem = FileSystem.get(configuration);
            ranFos = fileSystem.create(new Path(ranPath));
            otherFos = fileSystem.create(new Path(otherPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException {
        // 判断key是否包含ran，并决定输出到哪个文件
        if (text.toString().contains("ran")) {
            ranFos.write(text.getBytes());
        } else {
            otherFos.write(text.getBytes());
        }

    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        if (ranFos != null) {
            ranFos.close();
        }
        if (otherFos != null) {
            otherFos.close();
        }
    }
}
