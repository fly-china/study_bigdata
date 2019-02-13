package com.lpf.bigdata.hadoop.atguigu.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 压缩与解压缩
 *
 * @author lipengfei
 * @create 2018-11-24 17:40
 **/
public class CompressDemo {

    private static final String BZIP2_TYPE = "org.apache.hadoop.io.compress.BZip2Codec";
    private static final String GZIP_TYPE = "org.apache.hadoop.io.compress.GzipCodec";
    private static final String DEFAULT_TYPE = "org.apache.hadoop.io.compress.DefaultCodec";

    public static void main(String[] args) throws Exception {
        String originalFilePath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/compress/" +
                "original.txt";
        String decompressFilePath = "/Users/ran/fei/project/lpf_project/bigdata/src/main/resources/input/compress/" +
                "original.txt.deflate";

        compress(originalFilePath, DEFAULT_TYPE);
        decompress(decompressFilePath);

    }

    /**
     * 压缩
     *
     * @param origFilePath
     * @param compreeClassName
     */
    private static void compress(String origFilePath, String compreeClassName) {

        FileInputStream fis;
        FileOutputStream fos;
        CompressionOutputStream cos;
        try {


            Class aClass = Class.forName(compreeClassName);
            CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(aClass, new Configuration());

            fis = new FileInputStream(origFilePath);
            fos = new FileOutputStream(origFilePath + codec.getDefaultExtension());

            cos = codec.createOutputStream(fos);

            IOUtils.copyBytes(fis, cos, 1024 * 1024 * 5, false);


            cos.close();
            fos.close();
            fis.close();

            System.out.println(codec.getDefaultExtension() + "----------------压缩成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 解压缩
     *
     * @param compressFilePath
     */
    private static void decompress(String compressFilePath) {


        FileInputStream fis;
        CompressionInputStream cis;
        FileOutputStream fos;
        try {
            CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());
            CompressionCodec codec = factory.getCodec(new Path(compressFilePath));

            if (codec == null) {
                System.out.println("cannot find codec for file " + compressFilePath);
                return;
            }

            fis = new FileInputStream(compressFilePath);
            cis = codec.createInputStream(fis);

            fos = new FileOutputStream(compressFilePath + ".decoded");

            IOUtils.copyBytes(cis, fos, 1024 * 1024 * 5, false);

            fos.close();
            cis.close();
            fis.close();

            System.out.println(codec.getDefaultExtension() + "----------------解压成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
