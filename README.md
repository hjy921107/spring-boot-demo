[TOC]

# SpringBoot 2 笔记

##### 简介
- 简化 Spring 应用开发的一个框架；
- 整个 Spring 技术栈的一个大整合；
- J2EE 开发的一站式解决方案；

##### 个人环境说明

- SpringBoot 2
- JDK 8
- maven 3.5.3
- IntelliJ IDEA 2018
- MySQL 5.7
- Redis 4

## 1 基础

### 1.1 用一个 HelloWord 来阐述 SpringBoot 的简单与快速

#### 1.1.1 使用 maven 创建 SpringBoot 项目

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

#### 1.1.2 使用 Spring Initializer 创建 SpringBoot 项目

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

##### 自动创建项目说明

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

#### 1.1.3 运行 SpringBoot 项目

- 在 IDE 下，直接运行 Chapter01Application.class 的 main 方法
+ 基于 maven
    - 使用 `mvn package` 命令（依赖于 spring-boot-maven-plugin），将工程打成 jar/war 包，使用 `java -jar xxx.jar` 运行
    - 在工程的根目录下（即 pom.xml 文件所在目录），使用 `mvn spring-boot:run` 运行

运行成功后，使用浏览器访问 [http://localhost:8080/index](http://localhost:8080/index) 即可。

### 1.2 SpringBoot 与 JSP 间不可描述的秘密

SpringBoot 官方推荐的视图模版引擎是 Thymeleaf，对 jsp 的支持并不是特别理想，此处简单介绍使用 jsp 的一些注意事项。

##### 1.1.3.1 创建 web 项目

- 基于 Spring Initializer 创建 SpringBoot 工程（选择 war，工具 web）
- 配置 SpringBoot 支持 jsp

```xml
<!--
    Spring Boot 集成 jsp，使用内置容器时需要加入依赖
    此处的 scope 为 provided 可有可无
 -->
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
    <scope>provided</scope>
</dependency>
<!-- 如果需要，可以配置 Servlet, JSTL 的 maven 依赖 -->
```

- 添加 Controller

```java
@Controller
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
```

- 配置视图

通常情况下，一般 web 工程中的 jsp 文件都是存放在 /webapp/WEB-INF/jsp 下，默认，SpringBoot 是没有 /webapp 这个文件夹，我们需要自己创建，通常有两种方法：

- 完全手动创建对应目录
- 使用 idea 工具，对于 web 工程，idea 会自动将 webapp 目录映射到 / 目录，没有 webapp 会提示（图中红色字体），双击该行，会为我们自动创建。 ![自动创建 /webapp](https://ws1.sinaimg.cn/large/006tNc79gy1frl1dyjra0j31kw11eadx.jpg)

创建完上述目录后，配置 SpringMVC 的视图解析：

```xml
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
```

- 创建 jsp 文件

在 /webapp 下再手动创建 /WEB-INF 和 /jsp 目录，在 /jsp 目录下创建 index.jsp 文件，如下：

```html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
</head>
<body>
    <h3>This is index jsp page!</h3>
</body>
</html>
```

##### 1.1.3.2 运行 web 项目

SpringBoot 因为内置了 Servlet 容器，所以可以直接运行，而不依赖于外部 Servlet 容器；当然也可以使用外部 Servlet 容器，此处分别作简单说明：

###### 1.1.3.2.1 使用内置的 Servlet 容器

默认情况下，SpringBoot 内部集成了 Tomcat，无需其他配置，直接运行即可，运行方式见：[1.1.3 运行 SpringBoot 项目](#113-springboot)。但是此处直接运行 `Chapter01Application.class` 的 main 方法无法访问到 index.jsp，总是报 404 错误，只能使用 `mvn spring-boot:run` 的方式或者将工程打成 war 包（jar 包不行）才能正常访问。

###### 1.1.3.2.2 使用外置的 Servlet 容器

区别于使用内置的 Servlet 容器，使用外部的 Tomcat 需要作一些额外的配置：

- 将 SpringBoot 内置的 Tomcat 包指定为 provided，jsp/servlet 相关包无须引入，因为 外部 Tomcat 服务器中已提供，将内部 Tomcat 包指定为 provided 也是因为外部已提供，如果不使用 provided，可能会与外部的冲突。

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring‐boot‐starter‐tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```

- 必须编写一个 SpringBootServletInitializer 的子类，并重写 configure 方法

```java
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Chapter02Application.class);
    }

}
```

注意：此类在指定 maven 工程的 packaging 为 war 时，idea 已帮我们自动生成，此处 packaging 必须为 war，如果为 jar，则 idea 不会自动生成此类，需要手动添加。

### 1.3 SpringBoot使用SpringDataJPA完成CRUD

### 1.4 使用Druid作为SpringBoot项目数据源（添加监控）

### 1.5 配置使用FastJson返回Json视图

### 1.6 如何在SpringBoot项目中使用拦截器

### 1.12 SpringBoot使用LogBack作为日志组件

### 1.13 SpringBoot实战SpringDataJPA


## 2 核心

### 2.16 使用Redis作为SpringBoot项目数据缓存
