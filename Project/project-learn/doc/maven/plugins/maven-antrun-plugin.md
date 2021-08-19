#作用
总结来说，就是把一些task绑定到maven特定的生命周期上，执行maven声明周期命令时，会根据此插件定义的task执行一系列的ant指令  
#实用场景
1. 工程包的copy转义等，例如在package或install的时候，需要把包进行聚合到某目录下  
2. 自动化部署，工程包或配置文件等可scp到不同的部署机器目录上，然后ssh过去执行一些部署命令
3. 结合构建配置文件（profiles标签），可针对不同环境灵活控制ant命令执行的场景
#示例
实现功能：特定profile下（比如只想在研发环境下进行，研发时需要频繁的进行服务的部署验证，  
而且不希望影响到正式测试及上线打包部署，通常来说，测试和线上也不允许这种操作，一般都会有专门的工具进行），  
进行服务的自动化部署（这里只是列举了scp jar包以及执行了一个ls命令，可根据需要执行自己的部署脚本）
##父pom
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.hjb.learn</groupId>
    <artifactId>project-learn</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>


    <modules>
        <module>project-web</module>
        <module>project-management</module>
    </modules>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>

        <java.version>1.8</java.version>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <!-- https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-antrun-plugin -->
        <maven.antrun.plugin.version>3.0.0</maven.antrun.plugin.version>
        <!-- https://mvnrepository.com/artifact/com.jcraft/jsch -->
        <jsch.version>0.1.55</jsch.version>
    </properties>

    <profiles>
        <profile>
            <id>deploy</id>
            <build>
                <pluginManagement>
                    <plugins>
                        <plugin>
                            <groupId>org.apache.maven.plugins</groupId>
                            <artifactId>maven-antrun-plugin</artifactId>
                            <version>${maven.antrun.plugin.version}</version>
                            <dependencies>
                                <dependency>
                                    <groupId>com.jcraft</groupId>
                                    <artifactId>jsch</artifactId>
                                    <version>${jsch.version}</version>
                                </dependency>
                            </dependencies>
                            <executions>
                                <execution>
                                    <id>scp</id>
                                    <phase>install</phase>
                                    <goals>
                                        <goal>run</goal>
                                    </goals>
                                </execution>
                            </executions>
                        </plugin>
                    </plugins>
                </pluginManagement>
            </build>
        </profile>
    </profiles>

</project>
```
##project-web子pom
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>project-learn</artifactId>
        <groupId>com.hjb.learn</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>project-web</artifactId>

    <profiles>
        <profile>
            <id>deploy</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-antrun-plugin</artifactId>
                        <executions>
                            <execution>
                                <id>scp</id>
                                <configuration>
                                    <target>
                                        <path id="jsch.path">
                                            <fileset dir="${env.ANT_HOME}\lib">
                                                <include name="**/*.jar"/>
                                            </fileset>
                                        </path>
                                        <taskdef name="scp" classname="org.apache.tools.ant.taskdefs.optional.ssh.Scp"
                                                 classpathref="jsch.path"/>
                                        <taskdef name="sshexec" classname="org.apache.tools.ant.taskdefs.optional.ssh.SSHExec"
                                                 classpathref="jsch.path"/>
                                        <scp file="${project.build.directory}\project-web-1.0-SNAPSHOT.jar"
                                             todir="username:password@ip:/xxx" trust="true"/>
                                        <!-- <sshexec host="ip" username="xxx" password="xxx" command="sh xxx.sh"
                                                                                 trust="true"/>-->
                                    </target>
                                </configuration>
                            </execution>

                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>

</project>
```
##project-management子pom
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>project-learn</artifactId>
        <groupId>com.hjb.learn</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>project-management</artifactId>

    <profiles>
        <profile>
            <id>deploy</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-antrun-plugin</artifactId>
                        <executions>
                            <execution>
                                <id>scp</id>
                                <configuration>
                                    <target>
                                        <path id="jsch.path">
                                            <fileset dir="${env.ANT_HOME}\lib">
                                                <include name="**/*.jar"/>
                                            </fileset>
                                        </path>
                                        <taskdef name="scp" classname="org.apache.tools.ant.taskdefs.optional.ssh.Scp"
                                                 classpathref="jsch.path"/>
                                        <taskdef name="sshexec" classname="org.apache.tools.ant.taskdefs.optional.ssh.SSHExec"
                                                 classpathref="jsch.path"/>
                                        <scp file="${project.build.directory}\project-management-1.0-SNAPSHOT.jar"
                                             todir="username:password@ip:/xxx" trust="true"/>
                                        <!-- <sshexec host="ip" username="xxx" password="xxx" command="sh xxx.sh"
                                                                                 trust="true"/>-->
                                    </target>
                                </configuration>
                            </execution>

                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>

</project>
```
  
    
  
[github](https://github.com/hjbbjh/learn/tree/main/Project/project-learn)