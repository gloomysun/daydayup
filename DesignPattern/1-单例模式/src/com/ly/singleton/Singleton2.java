package com.ly.singleton;

/**
 * 懒汉式1-多线程下不安全，只适用于单线程
 */
public class Singleton2 {
    private Singleton2() {
    }

    private static Singleton2 singleton;

    public static Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}
