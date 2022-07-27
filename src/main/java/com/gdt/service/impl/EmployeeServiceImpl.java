package com.gdt.service.impl;

import com.gdt.entity.Employee;
import com.gdt.repository.EmployeeRepository;
import com.gdt.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> search() {
        return null;
    }

    @Override
    public void create(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee read(Long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee update(Employee employee, Long id) {
        Employee currentEmployee = this.read(id);
        currentEmployee.setFirstName(employee.getFirstName());
        currentEmployee.setLastName(employee.getLastName());
        return currentEmployee;
    }

    @Override
    public void delete(Long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public void taskToUser(Long taskId, Long userId) {

    }
}
