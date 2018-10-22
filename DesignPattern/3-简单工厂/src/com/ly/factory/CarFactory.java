package com.ly.factory;

import com.ly.bean.AuDi;
import com.ly.bean.BMW;
import com.ly.bean.Car;

public class CarFactory {

    private static CarFactory factory;
    static {
        factory = new CarFactory();
    }

    public static CarFactory getFactory() {
        return factory;
    }

    public static Car createAuDi(){
        return new AuDi();
    }
    public static Car createBMW(){
        return new BMW();
    }
}
