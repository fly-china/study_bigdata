package com.lpf.interview.jvm;

public class FinalizeDemoGC {

    public static  FinalizeDemoGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes, i am still alive :) ");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeDemoGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {

        // 对象第一次拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因为finalize方法的优先级很低，所以休眠1秒，以确保finalize方法执行
        Thread.sleep(30000);
        if(SAVE_HOOK == null){
            System.out.println("no, i am dead :(");
        }else{
            SAVE_HOOK.isAlive();
        }


        // 下面代码与上面完全相同，但是这一次却拯救失败
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(1000);
        if(SAVE_HOOK == null){
            System.out.println("no, i am dead :(");
        }else{
            SAVE_HOOK.isAlive();
        }
    }
}
