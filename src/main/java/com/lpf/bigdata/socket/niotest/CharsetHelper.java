package com.lpf.bigdata.socket.niotest;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @author lipengfei
 * @create 2018-10-11 15:06
 **/
public class CharsetHelper {

    private static final String UTF_8 = "UTF-8";
    private static Charset encoder = Charset.forName(UTF_8);
    private static Charset decoder = Charset.forName(UTF_8);

    public static ByteBuffer encode (CharBuffer charBuffer) {
        return encoder.encode(charBuffer);
    }

    public static CharBuffer decode (ByteBuffer byteBuffer){
        return  decoder.decode(byteBuffer);
    }
}
