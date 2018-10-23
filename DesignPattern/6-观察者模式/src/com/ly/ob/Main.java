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
