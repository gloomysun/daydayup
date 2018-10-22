package com.ly.factory;

import com.ly.bean.AuDi;
import com.ly.bean.BMW;
import com.ly.bean.Car;

public class CarFactory3 {
    private static CarFactory3 factory;
    static {
        factory = new CarFactory3();
    }

    public static CarFactory3 getFactory() {
        return factory;
    }

    public Car createCar(Class clazz){
        Car car = null;
        try {
            car = (Car) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return car;
    }

}
