package com.springsecurity.labs.labone.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @PreAuthorize("(hasRole('ROLE_ADMIN'))")
    public static String doSomething() {
        return "Success";
    }
}
