package com.springsecurity.labs.labone.util;

import org.springframework.context.ApplicationContext;

public class SpringContext {
    private static ApplicationContext applicationContext;

    public synchronized static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContext.applicationContext = applicationContext;
    }
}
