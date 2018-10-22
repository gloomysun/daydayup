package com.ly.jdkProxy;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("realSubject request");
    }
}
