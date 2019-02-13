package com.lpf.bigdata.socket.demo1;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lipengfei
 * @create 2018-09-29 14:29
 **/
public class FirstSocketServerDemo1 {

    public static String LOCAL_HOST = "localhost";
    public static int LOCAL_PORT = 8888;

    public static void main(String[] args) {


        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(LOCAL_HOST, LOCAL_PORT));

            System.out.println("server start --->");

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerSocketService(socket)).start();



//                InputStream inputStream = accept.getInputStream();
//                OutputStream outputStream = accept.getOutputStream();
//
//                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//                String temp = null;
//                String str = br.readLine();
//                while ((temp = br.readLine()) != null) {
//                    str = str + temp;
//                }
//
//                PrintWriter printWriter = new PrintWriter(outputStream);
//                printWriter.println("i");
//                printWriter.println("love");
//                printWriter.println("you" + str);
//                printWriter.flush();
//                System.out.println("服务器响应成功。");

            }


        } catch (Throwable e) {
            e.printStackTrace();
        }finally {

        }

    }

}
