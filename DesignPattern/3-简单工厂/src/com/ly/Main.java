package com.ly;

import com.ly.bean.AuDi;
import com.ly.bean.BMW;
import com.ly.bean.Car;
import com.ly.factory.CarFactory3;

public class Main {
    public static void main(String[] args) {
        /**
         * 方式1
         */
//        Car audi =  CarFactory.getFactory().createAuDi();
//        Car bmw = CarFactory.getFactory().createBMW();
//        audi.run();
//        bmw.run();
        /**
         * 方式2 升级版
         */
//        Car audi = CarFactory2.getFactory().createCar("audi");
//        Car bmw = CarFactory2.getFactory().createCar("bmw");
//        audi.run();
//        bmw.run();
        /**
         * 方式3 反射
         */
        Car audi = CarFactory3.getFactory().createCar(AuDi.class);
        Car bmw = CarFactory3.getFactory().createCar(BMW.class);
        audi.run();
        bmw.run();
    }
}
