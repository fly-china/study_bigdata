package com.lpf.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author lipengfeiR
 * @create 2020-03-08 14:06
 **/
public class JolDto {
    private boolean flag = true;   // 1字节
    private Boolean flag2 = true;   // 包装类型4字节
    private int int1 = 0;       // 4字节
    private Integer int2 = 0;       // 包装类型4字节
    private long long1 = 1;           // 8字节
    private Long long2 = 1L;           // 包装类型4字节
    private Object o = new Object();   //  4字节
    private static Long longStatic = 12L; // 静态变量在方法区存储，不占用实例数据空间

    public static void main(String[] args) {
        /*
         * 通过观察打印出的Java layout可发现：
         * 对象在内存的存储布局为： 8字节对象头（markword） + 4字节对象头（class pointer） + 实例数据大小 + 对齐数据大小
         * 其中对齐数据大小，保证对象的内存大小必须为8（位）的整数倍
         * 空对象Object o = new Object(), 默认占用内存大小为：16字节
         * 静态变量，不属于实例数据大小
         */
//        JolDto o = new JolDto();
        Object o = new Object();
        synchronized (o) {
            // -XX:BiasedLockingStartupDelay=0 偏向锁延时
            String layout = ClassLayout.parseInstance(o).toPrintable();
            System.out.println(layout);
        }

        System.out.println("************** 加锁前后数据对比 ***************");
        String layout2 = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(layout2);
    }


    private String getBool() {
        return flag2.toString();
    }


    // jdk8u: markOop.hpp
    // Bit-format of an object header (most significant first, big endian layout below):
//
//  32 bits:
//  --------
//             hash:25 ------------>| age:4    biased_lock:1 lock:2 (normal object)
//             JavaThread*:23 epoch:2 age:4    biased_lock:1 lock:2 (biased object)
//             size:32 ------------------------------------------>| (CMS free block)
//             PromotedObject*:29 ---------->| promo_bits:3 ----->| (CMS promoted object)
//
//  64 bits:
//  --------
//  unused:25 hash:31 -->| unused:1   age:4    biased_lock:1 lock:2 (normal object)
//  JavaThread*:54 epoch:2 unused:1   age:4    biased_lock:1 lock:2 (biased object)
//  PromotedObject*:61 --------------------->| promo_bits:3 ----->| (CMS promoted object)
//  size:64 ----------------------------------------------------->| (CMS free block)
//
//  unused:25 hash:31 -->| cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && normal object)
//  JavaThread*:54 epoch:2 cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)
//  narrowOop:32 unused:24 cms_free:1 unused:4 promo_bits:3 ----->| (COOPs && CMS promoted object)
//  unused:21 size:35 -->| cms_free:1 unused:7 ------------------>| (COOPs && CMS free block)
}
