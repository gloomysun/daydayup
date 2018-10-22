package com.ly.factory;

import com.ly.bean.BMW;
import com.ly.bean.Car;

public class BMWFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new BMW();
    }
}
