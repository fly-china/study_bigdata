package test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lipengfei
 * @create 2020-08-21 21:24
 **/
public class ChooseSubject {

    //    private static final String BASE_PATH = "/Users/ran/Desktop/ran/";
//    private static final String ORI_FILE_PATH = BASE_PATH + "muban.txt";
//    public static final String WRITE_FILE_TEMPLATE_PATH = BASE_PATH + "%s.txt";
//    private static final int targetFileNums = 30;
    private static Map<Integer, String> topicMap = new HashMap<>(); // 题目集合
    private static Map<Integer, List<String>> optionsMap = new HashMap<>(); // 选项集合
    private static Map<Integer, Integer> optionNumMap = new HashMap<>(); // 选项个数集合


    public static void main(String[] args) throws Exception {
        String BASE_PATH = args[0];
        String mubanFileName = args[1];
        String targetFileNumsStr = args[2];
        String ORI_FILE_PATH = BASE_PATH + "/" + mubanFileName;
        String WRITE_FILE_TEMPLATE_PATH = BASE_PATH + "/%s.txt";
        int targetFileNums = targetFileNumsStr != null ? Integer.parseInt(targetFileNumsStr) : 30;

        readAndAnalyzeFile(ORI_FILE_PATH);
        System.out.println("扫描完成,准备拆分文件.........");

        for (int i = 1; i <= targetFileNums; i++) {
            StringBuffer contentSb = new StringBuffer();
            int topicNums = topicMap.size();
            for (int j = 1; j <= topicNums; j++) {
                String topic = topicMap.get(j);
                contentSb.append(j).append("、").append(topic).append("\n");
                Integer optionNum = optionNumMap.get(j);
                List<String> optionList = optionsMap.get(j);
                for (int k = 1; k <= optionNum; k++) {
                    String option = optionList.remove(0);
                    contentSb.append(option).append("   ");
                }
                contentSb.append("\n");
            }

            writeFile(i, contentSb.toString(), WRITE_FILE_TEMPLATE_PATH);
            System.out.println("完成第" + i + "天题目分类");
        }
        System.out.println("文件拆分完成.......");

    }

    private static void writeFile(int fileNum, String content, String writeFilePath) throws Exception {
        String fileName = String.format(writeFilePath, fileNum);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }


    /**
     * 读取并解析文件
     */
    private static void readAndAnalyzeFile(String oriFilePath) {
        File file = new File(oriFilePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString = null;
            Integer topicNum = 1; // 行号
            while ((tempString = reader.readLine()) != null) {
//                System.out.println("本行内容为：" + tempString);
                if (tempString.startsWith("#")) {
                    // 解析题目
                    String optionNum = tempString.split("#")[1];
                    String topic = tempString.split("#")[2];
                    topicMap.put(topicNum, topic);
                    optionNumMap.put(topicNum, Integer.parseInt(optionNum));
                    topicNum++;
                } else {
                    // 解析答案
                    List<String> options = optionsMap.get(topicNum - 1);
                    if (options == null || options.size() < 1) {
                        options = new ArrayList<>();
                        optionsMap.put(topicNum - 1, options);
                    }
                    String[] split = tempString.split(" ");
                    List<String> list = Arrays.stream(split)
                            .filter(ite -> ite != null && ite.trim().length() > 0)
                            .collect(Collectors.toList());
                    options.addAll(list);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
