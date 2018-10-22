package com.ly.singleton;

/**
 * 懒汉式2-添加同步锁（不推荐）
 */
public class Singleton3 {
    private Singleton3() {
    }

    private static Singleton3 singleton;

    public static synchronized Singleton3 getInstance() {
        if (singleton == null) {
            singleton = new Singleton3();
        }
        return singleton;
    }
}
