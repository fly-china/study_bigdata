package com.lpf.bojun;

public abstract class TaskCustom implements Runnable {
    private long startTime; // 任务启动时间（时间戳，单位：毫秒）

    public TaskCustom(long startTime) {
        this.startTime = startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public void run() {
        // 具体任务逻辑
        execute();
    }

    // 抽象方法，由子类实现具体任务逻辑
    protected abstract void execute();
}