package com.ly.factory;

import com.ly.bean.AuDi;
import com.ly.bean.BMW;
import com.ly.bean.Car;

public class CarFactory2 {
    private static CarFactory2 factory;
    static {
        factory = new CarFactory2();
    }

    public static CarFactory2 getFactory() {
        return factory;
    }

    public Car createCar(String type){
        if("AuDi".equals(type)){
            return new AuDi();
        }else if("BMW".equals(type)){
            return new BMW();
        }else {
            System.out.println("无法生产");
            return null;
        }
    }
}
