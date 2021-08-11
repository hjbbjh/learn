#需求描述
商场收银软件客户端，具备打折、满减等促销活动  

#共性代码
##父类
```java
public abstract class CashSuper {
    //收取一定的钱，然后根据某种优惠规则返回最终的收费
    public abstract double acceptCash(double money);
}
```  
##正常收费
```java
public class CashNormal extends CashSuper {

    //没任何优惠，给多少收多少
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
```  
##满减收费
```java
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
```  
##打折
```java
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
``` 

#简单工厂模式
属于创建型模式，为类创建型模式，为了根据某些参数条件动态创建对象
##工厂类
```java
public class CashFactory {
    public static CashSuper createCashAccept(String type) {
        CashSuper cashSuper = null;
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
        return cashSuper;
    }
}
``` 
##客户端
```java
public class CashMain {

    public static void main(String[] args) {
        //简单工厂模式
        double total = 0;
        CashSuper cashSuper = CashFactory.createCashAccept("打八折");
        double price = 2.0;
        int num = 5;
        double money = cashSuper.acceptCash(price * num);
        System.out.println("单价：" + price + " 数量：" + num + " 合计：" + money);
        total += money;
        System.out.println("总价：" + total);
    }
}
``` 
##缺点
1. 超市促销活动往往很频繁且有时效性，也就是说收银软件如果使用以上简单工厂模式，那么工厂类就要频繁的进行改动、编译、上线、下线

#策略模式
属于行为型模式，是算法的抽象，运行时动态改变类的行为或算法。如果系统中多个类使用环境相同，仅仅区别于具体类的行为，而系统需要在这些行为中动态的选择一种。  
策略模式有助于减少if...else...分支。
##环境类
```java
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
``` 
##客户端
```java
public class CashMain {

    public static void main(String[] args) {
        //策略模式
        double total = 0;
        CashContext cashContext = new CashContext(new CashRebate(0.8));
        double price = 2.0;
        int num = 5;
        double money = cashContext.getResult(price * num);
        System.out.println("单价：" + price + " 数量：" + num + " 合计：" + money);
        total += money;
        System.out.println("总价：" + total);
    }
}
``` 
##特性
1. 环境类持有具体策略类的对象引用，根据不同的条件实例化具体策略对象，并由环境类持有引用，由此实现不同算法行为的动态切换  
2. 抽象策略类以及具体策略的继承关系，可实现一些共性算法的继承  
3. 具体策略类较多的情况下，可改进为混合模式（简单工厂+策略模式）
#简单工厂+策略模式
##环境类
```java
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
```
##客户端
```java
public class CashMain {

    public static void main(String[] args) {
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
```  
#简单工厂和策略模式区别
1. 简单工厂为创建型模式，强调对象的动态创建；策略模式为行为型模式，强调算法的封装和动态选择替换，算法行为委派给不同的对象  
2. 两者可一定程度上实现相同的功能，如上各个具体策略类，但如果新增行为策略，对于简单工厂来说，就得修改工厂类，可能会导致错误比较繁琐。  
但对于策略模式来说，仅仅增加一个具体策略类，作为参数传递给环境类而已，但缺点是，客户端必须清楚了解每个具体策略类，而不像工厂模式一样，只需要知道不同场景下的参数type即可。  
3. 简单工厂+策略模式，具备更好的解耦性，客户端只需要知道CashFactoryContext一个类即可，具体的行为算法对象的创建逻辑也隐藏了，但同时策略增加时也不可避免的需要修改CashFactoryContext。
4. 综上所述，各有利弊，择优选择。

[github](https://github.com/hjbbjh/learn/tree/main/DesignPattern/design-pattern-soft/src/main/java/com/hjb/learn/strategy)