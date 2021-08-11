package com.hjb.learn.strategy.soft;

public class CashMain {

    public static void main(String[] args) {
        /* //简单工厂模式
        double total = 0;
        CashSuper cashSuper = CashFactory.createCashAccept("打八折");
        double price = 2.0;
        int num = 5;
        double money = cashSuper.acceptCash(price * num);
        System.out.println("单价：" + price + " 数量：" + num + " 合计：" + money);
        total += money;
        System.out.println("总价：" + total);*/
        /* //策略模式
        double total = 0;
        CashContext cashContext = new CashContext(new CashRebate(0.8));
        double price = 2.0;
        int num = 5;
        double money = cashContext.getResult(price * num);
        System.out.println("单价：" + price + " 数量：" + num + " 合计：" + money);
        total += money;
        System.out.println("总价：" + total);*/
        //简单工厂+策略模式
        double total = 0;
        CashFactoryContext cashFactoryContext = new CashFactoryContext("打八折");
        double price = 2.0;
        int num = 5;
        double money = cashFactoryContext.getResult(price * num);
        System.out.println("单价：" + price + " 数量：" + num + " 合计：" + money);
        total += money;
        System.out.println("总价：" + total);
    }
}
