package com.lpf.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * Java Object LayOut  java对象布局
 *
 * @author lipengfei
 * @create 2020-03-08 14:00
 **/
public class HelloJOL {

    private volatile boolean flag1 = true;
    private Boolean flag2 = true;
//    private boolean flag3 = true;
//    private boolean flag4 = true;
//    private boolean flag5 = true;

    public static void main(String[] args) {
        // 669745413
        JolDto o = new JolDto();
        String layout = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(layout);

    }
}
