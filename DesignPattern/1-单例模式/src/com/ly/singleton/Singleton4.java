package com.ly.singleton;

/**
 * 懒汉式3-双重判断
 */
public class Singleton4 {
    private Singleton4() {
    }

    private static volatile Singleton4 singleton;

    public static synchronized Singleton4 getInstance() {
        if (singleton == null) {
            synchronized (Singleton4.class){
                //获得锁之后继续再一次判断，这样就安全了
                if(singleton==null){
                    singleton = new Singleton4();
                }
            }
        }
        return singleton;
    }
}
