# 2 SpringBoot 与 JSP 间不可描述的秘密

SpringBoot 官方推荐的视图模版引擎是 Thymeleaf，对 jsp 的支持并不是特别理想，此处简单介绍使用 jsp 的一些注意事项。

## 2.1 创建 web 项目

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

## 2.2 运行 web 项目

SpringBoot 因为内置了 Servlet 容器，所以可以直接运行，而不依赖于外部 Servlet 容器；当然也可以使用外部 Servlet 容器，此处分别作简单说明：

### 2.2.1 使用内置的 Servlet 容器

默认情况下，SpringBoot 内部集成了 Tomcat，无需其他配置，直接运行即可，运行方式见：[1.1.3 运行 SpringBoot 项目](#113-springboot)。但是此处直接运行 `Chapter01Application.class` 的 main 方法无法访问到 index.jsp，总是报 404 错误，只能使用 `mvn spring-boot:run` 的方式或者将工程打成 war 包（jar 包不行）才能正常访问。

### 2.2.2 使用外置的 Servlet 容器

区别于使用内置的 Servlet 容器，使用外部的 Tomcat 需要作一些额外的配置：

- 将 SpringBoot 内置的 Tomcat 包指定为 provided，jsp/servlet 相关包无须引入，因为外部 Tomcat 服务器中已提供，将内部 Tomcat 包指定为 provided 也是因为外部已提供，如果不使用 provided，可能会与外部的冲突。

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
