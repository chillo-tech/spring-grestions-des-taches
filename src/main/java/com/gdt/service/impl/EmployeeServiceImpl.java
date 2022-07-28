package com.gdt.service.impl;

import com.gdt.entity.Employee;
import com.gdt.entity.Task;
import com.gdt.exceptions.BadrequestException;
import com.gdt.repository.EmployeeRepository;
import com.gdt.service.EmployeeService;
import com.gdt.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private TaskService taskService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TaskService taskService) {
        this.employeeRepository = employeeRepository;
        this.taskService = taskService;
    }

    @Override
    public List<Employee> search() {
        return this.employeeRepository.findAll();
    }

    @Override
    public void create(Employee employee) {
        Optional<Employee> employeeOptional = this.employeeRepository.findByUserName(employee.getUserName());

        if(employeeOptional.isPresent()) {
            throw new BadrequestException("Message", "Code");
        }

        this.employeeRepository.save(employee);
    }

    @Override
    public Employee read(Integer id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee update(Employee employee, Integer id) {
        Employee currentEmployee = this.read(id);
        currentEmployee.setFirstName(employee.getFirstName());
        currentEmployee.setLastName(employee.getLastName());
        return currentEmployee;
    }

    @Override
    public void delete(Integer id) {
        this.employeeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void taskToUser(Integer taskId, Integer employeeId) {
        // Tache
        Task task = this.taskService.read(taskId);

        // user
        Employee employee = this.read(employeeId);


        Optional<Task> existingTask = employee.getTasks()
                                                .stream()
                                                .filter(userTask -> Objects.equals(userTask.getId(), taskId))
                                                .findFirst();
        if (existingTask.isPresent()) {
            throw new BadrequestException("Cette est déjà attribué à cet utilisateur", "TASK_AFFECTED");
        }

        employee.getTasks().add(task);
    }
}
