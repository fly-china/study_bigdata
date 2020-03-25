package com.lpf.jol;

/**
 * 测试Synchronized汇编优化
 * -server -XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading  -XX:+PrintAssembly -XX:+LogCompilation -XX:LogFile=TestSynchronizedAssembly.log
 * 生成的TestSynchronizedAssembly.log，可以使用jitWatcher进行查看
 *
 * @author lipengfei
 * @create 2020-03-23 17:31
 **/
public class TestSynchronizedAssembly {

    static volatile int i = 0;
    static volatile int j = 0;

    public static void n() {
        i++;
    }

    public static synchronized void m() {
        j++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 100_0000; j++) {
            m();
            n();
        }
        System.out.println(i);
        System.out.println(j);
    }
}
