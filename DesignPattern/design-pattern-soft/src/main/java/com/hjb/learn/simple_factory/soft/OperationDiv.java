package com.hjb.learn.simple_factory.soft;

public class OperationDiv extends Operation {
    public void printResult() throws Exception {
        if(afterNumber == 0){
            throw new Exception("除数不能为0");
        }
        System.out.println(preNumber + " / " + afterNumber + " = " + (preNumber / afterNumber));
    }
}
