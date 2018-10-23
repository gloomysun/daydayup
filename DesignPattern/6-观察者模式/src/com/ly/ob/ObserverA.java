package com.ly.ob;

public class ObserverA implements Observer {
    @Override
    public void update(Observable observable) {
        System.out.println("A观察到"+observable.getClass().getSimpleName()+"发生变化");
    }
}
