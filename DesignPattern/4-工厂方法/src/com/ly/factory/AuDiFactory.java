package com.ly.factory;

import com.ly.bean.AuDi;
import com.ly.bean.Car;

public class AuDiFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new AuDi();
    }
}
