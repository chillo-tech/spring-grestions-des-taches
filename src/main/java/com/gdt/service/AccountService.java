package com.gdt.service;

import com.gdt.entity.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    void signup(Employee employee);
    void activate(String username, String token);
}
