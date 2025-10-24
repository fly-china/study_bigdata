package com.lpf.bojun;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.*;

public class TaskScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10); // 线程池大小可以根据需求调整
    private final PriorityQueue<TaskCustom> taskQueue = new PriorityQueue<>(Comparator.comparingLong(TaskCustom::getStartTime));

    // 添加任务到调度器
    public void addTask(TaskCustom task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify(); // 唤醒线程，检查是否有任务需要调度
        }
    }

    // 启动调度器
    public void start() {
        Thread schedulerThread = new Thread(() -> {
            while (true) {
                try {
                    TaskCustom nextTask;
                    synchronized (taskQueue) {
                        while (taskQueue.isEmpty()) {
                            taskQueue.wait(); // 等待任务队列中有任务
                        }
                        nextTask = taskQueue.poll(); // 获取最早启动的任务
                    }

                    long now = System.currentTimeMillis();
                    long delay = nextTask.getStartTime() - now;

                    if (delay > 0) {
                        Thread.sleep(delay); // 等待到任务启动时间
                    }

                    // 提交任务到线程池执行
                    scheduler.schedule(nextTask, 0, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        schedulerThread.setDaemon(true); // 设置为守护线程
        schedulerThread.start();
    }

    // 关闭调度器
    public void shutdown() {
        scheduler.shutdown();
    }
}