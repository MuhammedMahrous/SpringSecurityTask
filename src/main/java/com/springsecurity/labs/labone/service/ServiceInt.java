package com.springsecurity.labs.labone.service;

import org.springframework.security.access.prepost.PreAuthorize;

public interface ServiceInt {
    @PreAuthorize("(hasRole('ROLE_ADMIN'))")
    String doSomething();
}
