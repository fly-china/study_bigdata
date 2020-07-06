package com.lpf.jdk8.endian;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * 大端模式和小端模式
 *
 * @author lipengfei
 * @create 2020-07-06 09:59
 **/
public class BigAndLittleEndian {
    public static void main(String[] args) {


        // 一个char占两字节
        // 创建12个字节的字节缓冲区
        ByteBuffer bb = ByteBuffer.wrap(new byte[2]);
        // 存入字符串
        bb.asCharBuffer().put("a");
        System.out.println(Arrays.toString(bb.array()));

        System.out.println("My OS默认ByteOrder为：" + ByteOrder.nativeOrder().toString());
        System.out.println("ByteBuffer默认ByteOrder为：" + bb.order());

        // 反转缓冲区
        bb.rewind();
        // 设置字节存储次序
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("a");
        System.out.println(Arrays.toString(bb.array()));

        // 反转缓冲区
        bb.rewind();
        // 设置字节存储次序
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put("a");
        System.out.println(Arrays.toString(bb.array()));

        System.out.println("-------------");
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putLong(1L);
        System.out.println(Arrays.toString(byteBuffer.array()));

    }
}
