package com.hjb.learn.strategy.soft;

/**
 * ClassName: CashNormal
 * Description: 正常收费/策略模式具体策略类
 * Created by haojingbin on 2021/8/4 8:59
 *
 * @author haojingbin
 */
public class CashNormal extends CashSuper {

    //没任何优惠，给多少收多少
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
