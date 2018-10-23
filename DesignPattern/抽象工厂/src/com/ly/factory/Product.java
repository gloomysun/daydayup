package com.ly.factory;

interface ProductA {

    void methodA();
}

interface ProductB {

    void methodB();
}

class ProductA1 implements ProductA{

    public void methodA() {
        System.out.println("产品A系列中1型号产品的方法");
    }

}

class ProductA2 implements ProductA{

    public void methodA() {
        System.out.println("产品A系列中2型号产品的方法");
    }

}

class ProductB1 implements ProductB{

    public void methodB() {
        System.out.println("产品B系列中1型号产品的方法");
    }

}

class ProductB2 implements ProductB{

    public void methodB() {
        System.out.println("产品B系列中2型号产品的方法");
    }
}