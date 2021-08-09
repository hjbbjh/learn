#定义
[工厂类](https://github.com/hjbbjh/learn/blob/main/DesignPattern/design-pattern-soft/src/main/java/com/hjb/learn/simple_factory/soft/SimpleFactoryMain.java) ，根据不同的参数决定实例化哪种类型对象，被创建的实例通常有共同的父类

#特性
1. 工厂类创建实例的方法为静态方法，因此简单工厂也被称为静态方法工厂  
2. 根据工厂方法参数的不同，走不同类型的实例创建分支，使用者无需知道具体的实例创建逻辑以及类名等，全权交由工厂创建  
3. 一定程度上可以解耦使用者（客户端）和工厂类逻辑，因为具体的实例创建任务由工厂类进行或者使用某些手段（例如工厂方法配置化）进行不改动客户端的情况下动态创建实例
4. 适用于类型较少的实例创建，这样工厂方法逻辑不至于过于复杂臃肿

#缺点
1. 所有的实例创建逻辑耦合在工厂方法中，逻辑可能越发负责，若工厂方法有问题则可能影响所有类型实例的创建  
2. 新增类型实例的创建逻辑就要改动工厂方法，违背开闭原则

#示例
[github](https://github.com/hjbbjh/learn/tree/main/DesignPattern/design-pattern-soft/src/main/java/com/hjb/learn/simple_factory)