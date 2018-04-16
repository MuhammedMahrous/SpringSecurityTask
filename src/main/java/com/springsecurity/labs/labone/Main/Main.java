package com.springsecurity.labs.labone.Main;

import com.springsecurity.labs.labone.application.MyApplication;
import com.springsecurity.labs.labone.util.SpringContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("cfg/spring-cfg.xml");
        SpringContext.setApplicationContext(applicationContext);
        MyApplication.launchApp();
    }
}
