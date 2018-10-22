package com.ly.singleton;

/**
 * 静态内部类实现
 * 一个类的静态属性只会在第一次加载类时初始化
 * 在多线程环境下，jvm对一个类的初始化会做限制，同一时间只会允许一个线程去初始化一个类
 */
public class Singleton5 {
    private static class InnerClass {
        public static Singleton5 singleton = new Singleton5();
    }

    public Singleton5 getInstance() {
        return InnerClass.singleton;
    }

}
