# 1 用一个 HelloWord 来阐述 SpringBoot 的简单与快速

## 1.1 使用 maven 创建 SpringBoot 项目

- 创建一个 maven 工程（packaging 为 jar）
- 导入相关的依赖

```xml
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.0.1.RELEASE</version>
	<relativePath/> <!-- lookup parent from repository -->
</parent>

<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
</dependencies>
```

- 编写一个主程序，用于启动 Spring Boot 应用

```java
@SpringBootApplication
public class Chapter01Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter01Application.class, args);
    }
}
```

- 编写相关的 Controller、Service

```java
@RestController
public class HelloWorldController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "Hello World!";
	}
}
```

## 1.2 使用 Spring Initializer 创建 SpringBoot 项目

1. 使用 Spring 提供的[在线工具](https://start.spring.io)自动生成 SpringBoot 工程代码，下载下来导入 IDE 即可直接使用。
2. 使用 IDEA 工具生成 SpringBoot 工程

	2.1 新建模块
![新建模块](https://ws3.sinaimg.cn/large/006tKfTcgy1frkfom56noj31cy0us75n.jpg)
	2.2 填写 Maven 工程信息
![填写 Maven 工程信息](https://ws4.sinaimg.cn/large/006tKfTcgy1frkfth2uspj31fy130wg4.jpg)
	2.3 选择需要的工具
![选择需要的工具](https://ws4.sinaimg.cn/large/006tKfTcgy1frkfuaqv45j31gy130jtf.jpg)
	2.4 填写模块名，Finish 即可！

	2.5 代码编写，测试运行

### 自动创建项目说明

使用 Spring Initializer 创建的项目，默认已经创建了相关的目录结构，在上述第三步时，根据选择的工具导入相关的 maven 依赖，最后的目录结构和 pom.xml 文件如下：

![目录结构](https://ws3.sinaimg.cn/large/006tKfTcgy1frkg817w6vj30is0k2wez.jpg)

- /src/main/java/**/Chapter13Application.class：主入口
+ /resources
	- statis：静态资源文件夹
	- templates：模版文件夹
	- application.properties：主配置文件
- mvnw,mvnw.cmd 可删除； .gitignore 如果不用 git 也可以删除
- pom.xml：maven 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.jyhuang</groupId>
    <artifactId>chapter13</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>chapter13</name>
    <description>Demo project for Spring Boot</description>

    <!-- 继承 spring boot 父工程，管理应用里面的所有依赖版本 -->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.2.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <!-- 自定义属性配置 -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

    <!-- spring boot 相关依赖 -->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <!-- spring boot 集成 maven 的打包插件 -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

## 1.3 运行 SpringBoot 项目

- 在 IDE 下，直接运行 Chapter01Application.class 的 main 方法
+ 基于 maven
    - 使用 `mvn package` 命令（依赖于 spring-boot-maven-plugin），将工程打成 jar/war 包，使用 `java -jar xxx.jar` 运行
    - 在工程的根目录下（即 pom.xml 文件所在目录），使用 `mvn spring-boot:run` 运行

运行成功后，使用浏览器访问 [http://localhost:8080/index](http://localhost:8080/index) 即可。