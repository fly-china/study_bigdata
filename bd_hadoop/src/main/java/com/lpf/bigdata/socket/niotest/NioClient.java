package com.lpf.bigdata.socket.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Nio的客户端
 *
 * @author lipengfei
 * @create 2018-10-11 15:43
 **/
public class NioClient implements Runnable {

    private ArrayBlockingQueue<String> words;
    private Random random;


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            NioClient client = new NioClient();
            client.init();


            new Thread(client).start();
        }


    }


    @Override
    public void run() {
        SocketChannel socketChannel = null;
        Selector selector = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            // 主动请求连接
            socketChannel.connect(new InetSocketAddress("localhost", 8383));

            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            boolean isOver = false;

            while (!isOver) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isConnectable()) {
                        if (socketChannel.isConnectionPending()) {// 判断通道上是否有初始化完成的连接
                            if (socketChannel.finishConnect()) {
                                //if, and only if, this channel's socket is now connected

                                // 将通道监听事件注册为可读，准备发送消息后，可读取服务端返回
                                socketChannel.register(selector, SelectionKey.OP_READ);

                                socketChannel.write(CharsetHelper.encode(CharBuffer.wrap(getWord())));

                            } else {
                                key.cancel();
                            }
                        }
                    } else if (key.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                        socketChannel.read(byteBuffer);
                        byteBuffer.flip();

                        CharBuffer charBuffer = CharsetHelper.decode(byteBuffer);
                        String resp = charBuffer.toString();
                        System.out.println(Thread.currentThread().getId() + "-------" + resp);

                        String word = getWord();
                        if (word != null) {
                            socketChannel.write(CharsetHelper.encode(CharBuffer.wrap(word)));
                        } else {
                            isOver = true;
                        }

                    }

                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null)
                    socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    private void init() throws InterruptedException {
        words = new ArrayBlockingQueue(50);
        words.put("who");
        words.put("what");
        words.put("where");
        words.put("how");
        words.put("xixi");

    }

    private String getWord() throws InterruptedException {

        return words.poll();
    }
}
