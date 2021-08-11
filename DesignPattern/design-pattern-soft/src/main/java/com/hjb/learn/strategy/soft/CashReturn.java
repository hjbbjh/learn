package com.hjb.learn.strategy.soft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CashRebate
 * Description: 返利收费/策略模式具体策略类
 * Created by haojingbin on 2021/8/4 8:59
 *
 * @author haojingbin
 */
public class CashReturn extends CashSuper {
    private final double moneyCondition;
    private final double moneyReturn;

    public CashReturn(double moneyCondition, double moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    @Override
    public double acceptCash(double money) {
        double result = money;
        if (money > moneyCondition) {
            result = money - (money / moneyCondition) * moneyReturn;
        }
        return result;
    }
}
