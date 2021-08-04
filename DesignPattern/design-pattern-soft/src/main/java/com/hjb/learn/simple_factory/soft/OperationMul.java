package com.hjb.learn.simple_factory.soft;

public class OperationMul extends Operation {

    public void printResult() {
        System.out.println(preNumber + " * " + afterNumber + " = " + (preNumber * afterNumber));
    }
}
