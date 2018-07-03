package com.ly.server;

import java.util.concurrent.atomic.AtomicInteger;

public class Count {
    public static int count = 0;

    static AtomicInteger atomic = new AtomicInteger(0);

    public synchronized static void inc() {
        count++;
    }

    public static void main(String[] args) {
        //同时启动1000个线程，去进行i++计算，看看实际结果
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    //Count.inc();
                    atomic.incrementAndGet();
                }
            }).start();
        }


        while (Thread.activeCount() > 2)
            Thread.yield();
        //这里每次运行的值都有可能不同,可能为1000
        //System.out.println("运行结果:Counter.count=" + Count.count);
        System.out.println("运行结果:Counter.count=" + atomic.get());
    }
}