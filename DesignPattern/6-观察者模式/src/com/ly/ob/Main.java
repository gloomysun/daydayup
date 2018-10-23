package com.ly.ob;

public class Main {
    public static void main(String[] args) {
        Observable observable = new Observable();
        ObserverA observerA = new ObserverA();
        ObserverB observerB = new ObserverB();

        observable.addObserver(observerA);
        observable.addObserver(observerB);

        observable.changed();
    }
}
/**
 被观察者改变了，开始通知观察者
 A观察到Observable发生变化
 B观察到Observable发生变化
 */
