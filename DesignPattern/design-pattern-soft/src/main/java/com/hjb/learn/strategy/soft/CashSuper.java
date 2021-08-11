package com.hjb.learn.strategy.soft;

/**
 * ClassName: CashSuper
 * Description: 父类/策略模式抽象策略类
 * Created by haojingbin on 2021/8/4 8:59
 *
 * @author haojingbin
 */
public abstract class CashSuper {
    //收取一定的钱，然后根据某种优惠规则返回最终的收费
    public abstract double acceptCash(double money);
}
