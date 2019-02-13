package com.lpf.bigdata.socket.demo1;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lipengfei
 * @create 2018-09-29 14:29
 **/
public class FirstSocketClientDemo1 {

    public static String LOCAL_HOST = "localhost";
    public static int LOCAL_PORT = 8888;

    public static void main(String[] args) {


        try {
            Socket socket = new Socket(LOCAL_HOST, LOCAL_PORT);

            System.out.println("client start --->");


            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println("heihei");
//            printWriter.println("报告完毕");
            printWriter.flush();
            System.out.println("请求服务器成功.....");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String tempStr = null;
//            StringBuffer respBody = new StringBuffer();
//            while((tempStr = bufferedReader.readLine()) != null && (tempStr = bufferedReader.readLine()).length() > 0){
//                respBody.append(tempStr);
//            }
//            bufferedReader.close();
            System.out.println("接受到服务器响应报文为：" + bufferedReader.readLine());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
