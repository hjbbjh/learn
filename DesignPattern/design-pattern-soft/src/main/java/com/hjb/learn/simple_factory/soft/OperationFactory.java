package com.hjb.learn.simple_factory.soft;

public class OperationFactory {

    public static Operation createOperate(String operate) {
        Operation result = null;
        switch (operate) {
            case "+":
                result = new OperationAdd();
                break;
            case "-":
                result = new OperationSub();
                break;

            case "*":
                result = new OperationMul();
                break;

            case "/":
                result = new OperationDiv();
                break;

            default:
                System.out.println("not support this operate");
                break;

        }
        return result;
    }
}
