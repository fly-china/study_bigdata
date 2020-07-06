package com.lpf.utils;

import java.io.FileInputStream;

/**
 * 文件真实类型判断
 *
 * @author lipengfei
 * @create 2020-04-01 15:06
 **/
public class FIleRealTypeJudgeUtil {

    public static void main(String[] args) {
        String path = "/Users/ran/Desktop/u=1265415991,873691815&fm=26&gp=0.jpg";
        System.out.println(bytesToHexString(path));
    }


    //传入path文件路径

    public static String bytesToHexString(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream is;
        try {
            is = new FileInputStream(path);
            byte[] b = new byte[4];//大小不同，获取的文件头长度也不一样
            is.read(b, 0, b.length);
            if (b == null || b.length <= 0) {
                return null;
            }
            for (int i = 0; i < b.length; i++) {

                // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式
                int v = b[i] & 0xFF;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(hv);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //转换为大写输出
        return stringBuilder.toString().toUpperCase();
    }

//          常见的文件头格式
//        ("FFD8FF", "jpg");
//        ("89504E47", "png");
//        ("47494638", "gif");
//        ("49492A00", "tif");
//        ("424D", "bmp");
//        ("41433130", "dwg"); //CAD
//        ("38425053", "psd");
//        ("7B5C727466", "rtf"); //日记本
//        ("3C3F786D6C", "xml");
//        ("68746D6C3E", "html");
//        ("44656C69766572792D646174653A", "eml"); //邮件
//        ("D0CF11E0", "doc");
//        ("5374616E64617264204A", "mdb");
//        ("252150532D41646F6265", "ps");
//        ("255044462D312E", "pdf");
//        ("504B0304", "zip")
//        ("52617221", "rar")
//        ("57415645", "wav")
//        ("41564920", "avi")
//        ("2E524D46", "rm")
//        ("000001BA", "mpg")
//        ("000001B3", "mpg")
//        ("6D6F6F76", "mov")
//        ("3026B2758E66CF11", "asf")
//        ("4D546864", "mid")
//        ("1F8B08", "gz")


}
