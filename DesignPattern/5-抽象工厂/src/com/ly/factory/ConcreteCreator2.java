package com.ly.factory;
/**
 * 生产II型号产品
 */
public class ConcreteCreator2 implements Creator{

    public ProductA createProductA() {
        return new ProductA2();
    }
    public ProductB createProductB() {
        return new ProductB2();
    }

}