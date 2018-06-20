package com.jyhuang.chapter12;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter12ApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void contextLoads() {
    }

    @Test
    public void test_log() {
        logger.trace("trace ==> 这是 trace 信息 ...");
        logger.debug("debug ==> 这是 debug 信息 ...");
        logger.info("info ==> 这是 info 信息 ...");
        logger.warn("warn ==> 这是 warn 信息 ...");
        logger.error("error ==> 这是 error 信息 ...");
    }

}
