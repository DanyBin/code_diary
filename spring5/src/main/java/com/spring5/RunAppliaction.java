package com.spring5;

import com.spring5.domain.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName com.spring5.RunAppliaction
 * @Author bin
 * @Date 2021/3/14 上午11:14
 * @Decr TODO
 * @Link TODO
 **/
@Slf4j
public class RunAppliaction {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        TestBean testBean = (TestBean)context.getBean("testBean");
        System.out.println(testBean.toString());
        log.debug("debug 信息");
        log.info("info 信息");
        log.warn("warn 信息");
        log.error("error 信息");
    }
}
