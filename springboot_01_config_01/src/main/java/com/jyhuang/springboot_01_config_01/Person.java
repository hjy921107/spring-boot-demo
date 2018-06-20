package com.jyhuang.springboot_01_config_01;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person {

    private String userName;
    private Boolean sex;
    private Integer age;
    private Date birthday;
    private Address address;

    private Map map;
    private List list;

}

