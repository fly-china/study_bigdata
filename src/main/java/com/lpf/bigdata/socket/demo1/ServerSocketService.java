package com.lpf.bigdata.socket.demo1;

import java.io.*;
import java.net.Socket;

/**
 * 服务端报文处理层
 *
 * @author lipengfei
 * @create 2018-09-29 15:02
 **/
public class ServerSocketService implements Runnable{

    public Socket socket;

    public ServerSocketService(Socket socket){
        this.socket = socket;
    }


    @Override
    public void run() {

        try {
            // 从socket连接中获取到与client之间的网络通信输入输出流
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();


            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String param = br.readLine();
            System.out.println("server received --->" + param);

            PrintWriter printWriter = new PrintWriter(out);
            printWriter.println("i love \n\r you");
//            printWriter.println("love");
//            printWriter.println("you" + str);
            printWriter.flush();
            System.out.println("服务器响应成功。");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
