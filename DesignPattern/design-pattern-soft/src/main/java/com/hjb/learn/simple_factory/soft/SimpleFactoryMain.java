package com.hjb.learn.simple_factory.soft;

public class SimpleFactoryMain {


    public static void main(String[] args) throws Exception {
        Operation operation = OperationFactory.createOperate("+");
        operation.preNumber = 1;
        operation.afterNumber = 2;
        operation.printResult();
    }
}
