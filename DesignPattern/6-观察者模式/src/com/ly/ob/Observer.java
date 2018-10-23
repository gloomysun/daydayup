package com.ly.ob;

public interface Observer {
    //被观察变化后调用此方法
    void update(Observable observable);
}
