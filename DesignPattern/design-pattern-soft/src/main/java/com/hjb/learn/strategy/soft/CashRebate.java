package com.hjb.learn.strategy.soft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CashRebate
 * Description: 打折收费/策略模式具体策略类
 * Created by haojingbin on 2021/8/4 8:59
 *
 * @author haojingbin
 */
public class CashRebate extends CashSuper {
    private final double moneyRebate;

    public CashRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}
