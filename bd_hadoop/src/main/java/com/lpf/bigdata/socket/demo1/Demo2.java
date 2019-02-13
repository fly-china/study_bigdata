package com.lpf.bigdata.socket.demo1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;

/**
 * @author lipengfei
 * @create 2018-09-29 15:21
 **/
public class Demo2 {

    public static void main(String[] args) throws Exception {

        String filePath = "/Users/ran/Desktop/heihei";

//        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        String tempStr = null;
        StringBuffer respBody = new StringBuffer();
        int line = 1;
        while ((tempStr = bufferedReader.readLine()) != null) {
            System.out.println("第" + line + "行------" + tempStr);
            line ++;
        }
        bufferedReader.close();


    }
}
