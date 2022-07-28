package com.gdt.service;

import com.gdt.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> search();
    void create(Employee employee);
    Employee read(Integer id);
    Employee update( Employee employee, Integer id);
    void delete(Integer id);

    void taskToUser(Integer taskId, Integer employeeId);
}
