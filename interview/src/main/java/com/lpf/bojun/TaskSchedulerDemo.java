package com.lpf.bojun;

public class TaskSchedulerDemo {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        // 创建几个任务
        long now = System.currentTimeMillis();
        TaskCustom task1 = new ExampleTask(now + 5000, "Task1"); // 5秒后启动
        TaskCustom task2 = new ExampleTask(now + 3000, "Task2"); // 3秒后启动
        TaskCustom task3 = new ExampleTask(now + 8000, "Task3"); // 8秒后启动

        // 添加任务到调度器
        scheduler.addTask(task1);
        scheduler.addTask(task2);
        scheduler.addTask(task3);

        // 启动调度器
        scheduler.start();

        // 程序运行一段时间后关闭调度器
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutdown();
    }
}
 class ExampleTask extends TaskCustom {
    private String name;

    public ExampleTask(long startTime, String name) {
        super(startTime);
        this.name = name;
    }

    @Override
    protected void execute() {
        System.out.println("Task " + name + " is running at " + System.currentTimeMillis());
    }
}