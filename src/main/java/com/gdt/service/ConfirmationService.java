package com.gdt.service;

import com.gdt.entity.ConfirmationToken;
import com.gdt.entity.Employee;

public interface ConfirmationService {
    void sendEmployeeVerificationToken(Employee employee);
    ConfirmationToken getEmployeeVerificationToken(String username, String token);
}
