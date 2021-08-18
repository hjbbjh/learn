mvn help:effective-pom -Doutput=xxx.xml  
获取实际生效的完整pom配置：pom为约定大于配置，也就是说即使你pom文件中没有使用parent标签显式继承父pom，也会隐式集成父pom（所有的pom都继承自一个父pom），可以对父pom中配置进行覆盖重写  



