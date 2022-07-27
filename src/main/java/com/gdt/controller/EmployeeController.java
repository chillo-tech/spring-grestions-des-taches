package com.gdt.controller;

import com.gdt.entity.Employee;
import com.gdt.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public void create(@RequestBody Employee employee){
        this.employeeService.create(employee);
    }
}
