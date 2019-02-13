package com.lpf.bigdata.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * 第一个Hadoop程序
 *
 * @author lipengfei
 * @create 2018-10-22 19:56
 **/
public class FirstHadoopDemo {

    private static final String HDFS_URI = "hdfs://hadoop01:8020/";
    private static final String HDFS_HOME_PATH = "/user/root/";

    public static void main(String[] args) {
        FileSystem fileSystem = null;
        try {
            Configuration conf = new Configuration();
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

//        Path path = new Path("hdfs://hadoop01");
            fileSystem = FileSystem.get(URI.create(HDFS_URI), conf, "root");

            FileStatus[] fileStatuses = fileSystem.listStatus(new Path(HDFS_HOME_PATH));
            System.out.println("************* 列出该目录下所有文件 ***************");
            for (FileStatus fileStatus : fileStatuses) {
                System.out.println(fileStatus);
            }
            System.out.println("************* 列出该目录下所有文件 ***************");


            System.out.println("************* 使用Hadoop自带IOUtils组件读取文件 ***************");
            Path filePath = new Path(HDFS_HOME_PATH + "input/test.xml");
            try (FSDataInputStream fsInput = fileSystem.open(filePath);) {
                IOUtils.copyBytes(fsInput, System.out, 4096, false);
                fsInput.seek(3);
                System.out.println("使用seek（）跳转至文件中任意位置");
                IOUtils.copyBytes(fsInput, System.out, 4096, false);
            }
            System.out.println("************* 使用Hadoop自带IOUtils组件读取文件 ***************");

            System.out.println("************* 使用Hadoop自带IOUtils组件上传文件 ***************");
            Path uploadPath = new Path(HDFS_HOME_PATH + "input/ioutils_upload.txt");
            String localFilePath = "/Users/ran/Desktop/heihei";
            try (FSDataOutputStream fsOut = fileSystem.create(uploadPath);
                 FileInputStream loacalIn = new FileInputStream("localFilePath");) {
                IOUtils.copyBytes(loacalIn, fsOut, conf);
            }
            System.out.println("************* 使用Hadoop自带IOUtils组件上传文件 ***************");


            byte[] buff = "this is helloworld from java api!\n".getBytes();
            Path writePath = new Path(HDFS_HOME_PATH + "input/helloword");
            try (FSDataOutputStream os = fileSystem.create(writePath)) {
                os.write(buff, 0, buff.length);
            }
            System.out.println(">>>>>>>写入完成 ============================");

            System.out.println(">>>>>>>READING ============================");
            FSDataInputStream is = fileSystem.open(writePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // 示例仅读取一行
            String content = br.readLine();
            System.out.println(content);
            br.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileSystem != null) {
                try {
                    fileSystem.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
