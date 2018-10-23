package com.ly.ob;

public class ObserverB implements Observer {
    @Override
    public void update(Observable observable) {
        System.out.println("B观察到"+observable.getClass().getSimpleName()+"发生变化");
    }
}
