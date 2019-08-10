package com.lpf.zhihu.download;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 下载图片
 *
 * @author lipengfei
 * @create 2019-05-31 11:46
 **/
public class DownLoadImg {

    private static final String BASE_PATH = "G:\\知乎图片\\0531\\";

    public static void main(String[] args) throws Exception {
        String filePath = BASE_PATH + "知乎imgUrl.txt";
        List<String> urlList = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {


            String url = null;
            while ((url = bufferedReader.readLine()) != null) {
                // 一次读入一行数据
                urlList.add(url);
            }
            System.out.println("地址个数：" + urlList.size());

        }

        System.out.println("开始下载");
        for (int i = 0; i < urlList.size(); i++) {
            String urlFordown = null;
            try {
                urlFordown = urlList.get(i);
                String imgPath = BASE_PATH + i + ".jpg";
                downloadPicture(urlFordown, imgPath);
                Thread.sleep(100);
                System.out.println(i + "下载成功...");
            } catch (InterruptedException e) {
                System.out.println(i + "下载失败，url:" + urlFordown);
                e.printStackTrace();
            }
        }
        System.out.println("下载成功");

    }


    //链接url下载图片
    private static void downloadPicture(String urlList, String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
