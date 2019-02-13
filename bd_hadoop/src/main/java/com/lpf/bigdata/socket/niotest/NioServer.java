package com.lpf.bigdata.socket.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Nio的服务端
 *
 * @author lipengfei
 * @create 2018-10-10 11:17
 **/
public class NioServer {

    private Selector selector;
    private ByteBuffer byteBuffer;

    public static void main(String[] args) {
        NioServer nioServer = new NioServer();
        nioServer.init();
        System.out.println("server start ---> 8383端口");
        nioServer.listen();
    }


    /**
     * 初始化服务端
     */
    private void init() {

        ServerSocketChannel serverSocketChannel;
        byteBuffer = ByteBuffer.allocate(1024);
        try {
            // 1、创建一个socket channel，channel是nio中的通道对象部分输入输出
            serverSocketChannel = ServerSocketChannel.open();
            // 2、设置通道为非阻塞方式
            serverSocketChannel.configureBlocking(false);
            // 3、将通道绑定在服务器的ip地址和某个端口上
            serverSocketChannel.socket().bind(new InetSocketAddress(8383));

            // 4、打开一个多路复用器，用来协调各个channel
            selector = Selector.open();

            /**
             * 5、将上面创建的socket channel注册到多路复用器selector
             *  作为服务器必须要注册一个OP_ACCEPT事件，用来接受客户端的连接
             */
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    /**
     * 服务端事件监听
     */
    private void listen() {

        while (true) {
            try {
                // 去询问一次selector选择器,改方法是一个阻塞方法，直到有channel准备完毕
                int n = selector.select();
                if (n == 0) continue;

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    // 遍历到一个事件KEY
                    SelectionKey key = iterator.next();

                    handleKey(key);
                    iterator.remove();//    确保事件不被重复处理
                }

            } catch (Throwable e) {
                e.printStackTrace();
            }


        }

    }

    private void handleKey(SelectionKey key) throws IOException {
        SocketChannel channel = null;
        try {
            if (key.isAcceptable()) {
                System.out.println("-------key.isAcceptable()");
                ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = serverChannel.accept();
                socketChannel.configureBlocking(false);
                // 监听读事件，准备接受客户端发送的数据
                System.out.println("" + key.isValid());
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (key.isConnectable()) {
                System.out.println("-------key.isConnectable()");

            } else if (key.isReadable()) {
                System.out.println("-------key.isReadable()");
                channel = (SocketChannel) key.channel();
                // 先清空byteBUffer
                byteBuffer.clear();

                /**
                 * 当客户端channel关闭后，服务端也回不断收到read事件，但是没有消息，即readCount=-1
                 * 所以，服务端也要及时关闭channel，避免无限无效的处理
                 */
                int readCount = channel.read(byteBuffer);

                if (readCount > 0) {
                    /**
                     * 一定要调用flip函数，否则读取数据错误
                     * 简单来说，flip就是让读写指针和limit指针复位到正确的位置；
                     */
                    byteBuffer.flip();

                    CharBuffer charBuffer = CharsetHelper.decode(byteBuffer);
                    String req = charBuffer.toString();
                    System.out.println("****接收到客户端请求报文为：" + req);

                    // 进行业务处理
                    String result = bizHandle(req);
                    channel.write(CharsetHelper.encode(CharBuffer.wrap(result)));
                    System.out.println("****响应到客户端请求报文为：" + result);
                } else {
                    channel.close();
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
            if (channel != null) {
                channel.close();
            }
        }
    }

    /**
     * @param req
     * @return
     */
    private String bizHandle(String req) {

        String result = "";

        switch (req) {
            case "who":
                result = "i am 佐罗";
                break;
            case "where":
                result = "我在那儿等着你来";
                break;
            case "what":
                result = "do what you like to do";
                break;
            case "how":
                result = "how are you";
                break;
            default:
                result = "输入有误，请输入who、where、what";

        }

        return result;
    }
}
