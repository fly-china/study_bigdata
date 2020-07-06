package com.lpf.jol;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author lipengfei
 * @create 2020-03-09 15:44
 **/
public class TestUnsafe {

    int i = 0;
    private static TestUnsafe t = new TestUnsafe();

    public static void main(String[] args) throws Exception {
        //Unsafe unsafe = Unsafe.getUnsafe();

        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        Field f = TestUnsafe.class.getDeclaredField("i");
        long offset = unsafe.objectFieldOffset(f);
        System.out.println(offset);

        // 参数一：目标对象   参数二：目标对象对应偏移量的field
        // 参数三：期望当前内存中的值   参数四：期望修改的目标值
        boolean success = unsafe.compareAndSwapInt(t, offset, 0, 1);
        System.out.println(success);
        System.out.println(t.i);
        //unsafe.compareAndSwapInt()
    }
}
