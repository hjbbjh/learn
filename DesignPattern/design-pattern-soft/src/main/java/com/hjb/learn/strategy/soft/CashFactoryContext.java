package com.hjb.learn.strategy.soft;

/**
 * ClassName: CashFactory
 * Description: 简单工厂+策略环境类
 * Created by haojingbin on 2021/8/4 8:59
 *
 * @author haojingbin
 */
public class CashFactoryContext {
    private CashSuper cashSuper;

    public CashFactoryContext(String type) {
        switch (type) {
            case "正常收费":
                cashSuper = new CashNormal();
                break;
            case "满300减100":
                cashSuper = new CashReturn(300.0, 100.0);
                break;
            case "打八折":
                cashSuper = new CashRebate(0.8);
                break;
            default:
                System.out.println("not support");
                break;
        }
    }

    public double getResult(double money) {
        return cashSuper.acceptCash(money);
    }
}
