package com.gdt.service.impl;

import com.gdt.entity.Employee;
import com.gdt.exceptions.BadrequestException;
import com.gdt.service.AccountService;
import com.gdt.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmployeeService employeeService;

    @Override
    public void signup(Employee employee) {
        // Check PASSWORD
        if(employee.getPassword() != null && employee.getPassword().length() < 8) {
            throw new BadrequestException("Mot de de passe invalide", null);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(employee.getPassword());
        log.info("Mot de passe en clair {} mot de passe crypté {}", employee.getPassword(), encodedPassword);
        employee.setPassword(encodedPassword);

        employeeService.create(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
