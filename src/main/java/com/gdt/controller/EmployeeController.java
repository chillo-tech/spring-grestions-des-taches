package com.gdt.controller;

import com.gdt.dto.UserTask;
import com.gdt.entity.Employee;
import com.gdt.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path ="employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Employee employee){
        this.employeeService.create(employee);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{employeeId}/task")
    public void taskToUser(@Validated @RequestBody UserTask userTask, @PathVariable Integer employeeId){
        this.employeeService.taskToUser(
                employeeId,
                userTask.getEmployeeId()
        );
    }


    @GetMapping
    public List<Employee> search(){
        return this.employeeService.search();
    }
}
