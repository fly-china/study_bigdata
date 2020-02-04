package com.lpf.zhihu.download;

import com.google.common.collect.Lists;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下载图片
 *
 * @author lipengfei
 * @create 2019-05-31 11:46
 **/
public class DownLoadImg0620 {

    private static final String BASE_PATH = "G:\\知乎图片\\";

    public static void main(String[] args) throws Exception {
        String filePath = BASE_PATH + "全量.txt";
        String imgBasePath = BASE_PATH + "%s\\";

        Map<String, List<ImgVO>> mapList = new HashMap<>();
        Map<String, Integer> imgNumMap = new HashMap<>();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {


            String recStr = null;
            while ((recStr = bufferedReader.readLine()) != null) {
                // 一次读入一行数据
                // 数据格式：id, question_id, question_title, answer_id, answer_author_name, link, answer_update_time
                String[] fields = recStr.split("\t");
                String questionId = fields[1];
                questionId = questionId.substring(1, questionId.length() - 1);
                String question_title = fields[2];
                String answer_id = fields[3];
                String answer_author_name = fields[4];
                String urlStr = fields[5];
                ArrayList<String> linklist = Lists.newArrayList(urlStr.substring(1, urlStr.length() - 1).split(";"));
                int urlNums = linklist.size();

                ImgVO imgVO = new ImgVO();
                imgVO.setQuestionTitle(question_title.substring(1, question_title.length() - 1));
                imgVO.setAnswerId(answer_id.substring(1, answer_id.length() - 1));
                imgVO.setAnswerAuthorName(answer_author_name.substring(1, answer_author_name.length() - 1));
                imgVO.setLinkList(linklist);

                List<ImgVO> imgVOS = mapList.get(questionId);
                if (imgVOS == null) {
                    imgVOS = new ArrayList<>();
                    mapList.put(questionId, imgVOS);
                }
                imgVOS.add(imgVO);

                // 计算图片数量
                Integer oldNum = imgNumMap.getOrDefault(questionId, 0);
                imgNumMap.put(questionId, oldNum + urlNums);
            }
            System.out.println("问题个数：" + mapList.size());
            System.out.println("各问题对应图片数量：" + imgNumMap.toString());
        }

        System.out.println("开始下载");
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        mapList.remove("56378769");// 已完成
        mapList.remove("297715922");// 已完成
        mapList.remove("63727821");// 已完成
        mapList.remove("266695575");// 已完成
        mapList.remove("328457531");// 已完成
        mapList.remove("285321190");// 已完成
        mapList.remove("321151616");// 已完成
//        mapList.remove("26037846");// 只完成了一半
//        mapList.remove("278552153");// 进行中
        for (String questionId : mapList.keySet()) {
            String imgPath = String.format(imgBasePath, questionId);
            File file = new File(imgPath);
            if(!file.exists()){
                file.mkdirs();
            }

            List<ImgVO> imgVOS = mapList.get(questionId);

            for (ImgVO imgVO : imgVOS) {
                List<String> linkList = imgVO.getLinkList();
                String answerId = imgVO.getAnswerId();

                for (int i = 0; i < linkList.size(); i++) {
                    String urlLink = linkList.get(i);
                    String imgFilePath = imgPath + answerId + "_" + i + ".jpg";
                    threadPool.submit(() -> {
                        downloadPicture(urlLink, imgFilePath);
//                        try {
//                            // 休眠1-20毫秒
//                            int num = (int) (Math.random() * 20 + 1);
//                            Thread.sleep(num);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    });
                }
            }
        }

        Thread.sleep(6000_000);

//        for (int i = 0; i < urlList.size(); i++) {
//            String urlFordown = null;
//            try {
//                urlFordown = urlList.get(i);
//                String imgPath = imgPathPath + i + ".jpg";
//                downloadPicture(urlFordown, imgPath);
//                Thread.sleep(100);
//                System.out.println(i + "下载成功...");
//            } catch (InterruptedException e) {
//                System.out.println(i + "下载失败，url:" + urlFordown);
//                e.printStackTrace();
//            }
//        }
//        System.out.println("下载成功");

    }


    //链接url下载图片
    private static void downloadPicture(String urlLink, String path) {
        URL url = null;
        try {
            url = new URL(urlLink);
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
        } catch (Exception e) {
            System.out.println(urlLink + "下载失败");
            e.printStackTrace();
        }
    }
}
