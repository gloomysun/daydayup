package com.ly.test;

import com.ly.singleton.Singleton2;
import com.ly.singleton.Singleton4;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

@RunWith(JUnit4.class)
public class SingletonTest {

    @Test
    public void testSingleton2() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1000);
        Set<Singleton2> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton2 singleton = Singleton2.getInstance();
                    set.add(singleton);
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        System.out.println(set.size());
        for (Singleton2 singleton : set) {
            System.out.println(singleton);
        }
        /**
         * 输出结果
          2
         com.ly.singleton.Singleton2@1da0f40b
         com.ly.singleton.Singleton2@7e541f7f
         */
    }

    @Test
    public void testSingleton3() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10000);
        Set<Singleton4> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton4 singleton = Singleton4.getInstance();
                    set.add(singleton);
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        System.out.println(set.size());
        for (Singleton4 singleton : set) {
            System.out.println(singleton);
        }
    }
}
