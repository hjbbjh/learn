package com.hjb.learn.simple_factory.soft;

import lombok.Data;

@Data
public abstract class Operation {
    public double preNumber;
    public double afterNumber;
    public abstract void printResult() throws Exception;
}
