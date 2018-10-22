package com.ly.singleton;

/**
 * 饿汉式
 */
public class Singleton {
    private Singleton(){}  //防止被其他类对该类进行实例化，避免被调用
    private static Singleton singleton = new Singleton();
    public static Singleton getInstance(){
        return singleton;
    }
}
