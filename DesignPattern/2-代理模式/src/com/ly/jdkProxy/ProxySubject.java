package com.ly.jdkProxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxySubject implements InvocationHandler {

    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin...");
        Object result = method.invoke(subject, args);
        System.out.println("end...");
        return result;
    }

    public static void main(String[] args) {
        RealSubject target = new RealSubject();
        Subject proxy = (Subject) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new ProxySubject(new RealSubject()));
        proxy.request();
    }
}
