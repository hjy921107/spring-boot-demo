package com.jyhuang.springboot_01_config_01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01Config01ApplicationTests {

    @Autowired
    private Person person;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getPerson() {
        System.out.println(person);
    }

}
