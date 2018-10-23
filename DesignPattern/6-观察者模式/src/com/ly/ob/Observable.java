package com.ly.ob;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 */
public class Observable {
    private List<Observer> observerList = new ArrayList<>(); //观察者列表

    //通知观察者
    public void notifyObservers() {
        for (Observer ob : observerList) {
            ob.update(this);
        }
    }
    public void changed(){
        System.out.println("被观察者改变了，开始通知观察者");
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }
}
