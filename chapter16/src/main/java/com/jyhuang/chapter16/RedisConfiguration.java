package com.jyhuang.chapter16;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(o.getClass().getName()) //
                    .append(".") //
                    .append(method.getName());

            if (objects.length > 0) {
                stringBuilder.append("?");

                for (Object object : objects) {
                    stringBuilder.append(String.valueOf(object)) //
                            .append("&");
                }

                stringBuilder.substring(0, stringBuilder.length() - 1);
            }

            System.out.println("调用 redis 缓存，key：" + stringBuilder.toString());

            return stringBuilder.toString();
        };
    }
}
