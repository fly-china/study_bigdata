package com.lpf.bigdata.hadoop.ch03;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.net.URI;

/**
 * @author lipengfei
 * @create 2018-10-25 14:20
 **/
public class FileTest {

    private static final String NAME_NODE_URL = "hdfs://hadoop01:8020/";
    private static final String HDFS_HOME_PATH = "/user/root/";
    Configuration conf = null;

    @Before
    public void initZk() {

        conf = new Configuration();
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("fs.defaultFS", NAME_NODE_URL);
    }

    @Test
    public void testListStatus() throws Exception {

        FileSystem fs = FileSystem.newInstance(conf);
        Path[] paths = {new Path("/user/root/input"), new Path("/user/root/output")};
        FileStatus[] fileStatuses = fs.listStatus(paths);

        // 测试listStatus
        System.out.println("-----------------------------");
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(fileStatus);
        }
        System.out.println("-----------------------------");

        // 测试stat2Paths
        System.out.println("*****************************");
        Path[] stat2Paths = FileUtil.stat2Paths(fileStatuses);
        for (Path stat2Path : stat2Paths) {
            System.out.println(stat2Path);
        }
        System.out.println("*****************************");

        // 测试stat2Paths
        System.out.println("=============================");
        PathFilter pf = new RegexExcludePathFilter(".*txt");
        FileStatus[] fileStatuses1 = fs.globStatus(new Path("/user/root/*put/*"), pf);
//        FileStatus[] fileStatuses1 = fs.globStatus(new Path("/user/root/*put/*"));
        for (FileStatus fileStatus : fileStatuses1) {
            System.out.println(fileStatus);
        }

        System.out.println("=============================");
    }

    @Test
    public void testIOUtils() throws Exception {
        FileSystem fileSystem = FileSystem.get(conf);

        System.out.println("************* 使用Hadoop自带IOUtils组件上传文件 ***************");
        Path uploadPath = new Path(HDFS_HOME_PATH + "input/ioutils_upload.txt");
        String localFilePath = "/Users/ran/Desktop/heihei";
        try (FSDataOutputStream fsOut = fileSystem.create(uploadPath);
             FileInputStream loacalIn = new FileInputStream(localFilePath);) {
            IOUtils.copyBytes(loacalIn, fsOut, conf);
        }
        System.out.println("************* 使用Hadoop自带IOUtils组件上传文件 ***************");

        System.out.println("************* 使用Hadoop自带IOUtils组件读取文件 ***************");
        Path filePath = new Path(HDFS_HOME_PATH + "input/ioutils_upload.txt");
        try (FSDataInputStream fsInput = fileSystem.open(filePath);) {
            IOUtils.copyBytes(fsInput, System.out, 4096, false);
            fsInput.seek(3);
            System.out.println("使用seek（）跳转至文件中任意位置");
            IOUtils.copyBytes(fsInput, System.out, 4096, false);
        }
        System.out.println("************* 使用Hadoop自带IOUtils组件读取文件 ***************");
    }

    @Test
    public void testLevel() throws Exception {
        // 设置副本数优先级：（1）客户端代码中设置的值 >（2）classpath下的用户自定义配置文件 >（3）然后是服务器的默认配置
        conf.set("dfs.replication" , "3");
        FileSystem fileSystem = FileSystem.get(conf);

        // 上传文件
        fileSystem.copyFromLocalFile(new Path("/Users/ran/fei/hello.txt"),
                new Path("/user/root/hello_node6.txt"));

        fileSystem.close();
    }
}
