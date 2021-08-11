package com.hjb.learn.strategy.soft;

/**
 * ClassName: CashFactory
 * Description: 策略模式环境类
 * Created by haojingbin on 2021/8/4 8:59
 *
 * @author haojingbin
 */
public class CashContext {
    //环境类持有具体策略类的一个对象引用
    private final CashSuper cashSuper;

    public CashContext(CashSuper cashSuper) {
        this.cashSuper = cashSuper;
    }

    public double getResult(double money) {
        return cashSuper.acceptCash(money);
    }
}
