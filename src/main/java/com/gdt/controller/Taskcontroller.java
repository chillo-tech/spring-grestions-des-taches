package com.gdt.controller;

import com.gdt.entity.Employee;
import com.gdt.entity.Task;
import com.gdt.service.TaskService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "task", produces = MediaType.APPLICATION_JSON_VALUE)
public class Taskcontroller {
    private TaskService taskService;

    public Taskcontroller(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Task task) {
        this.taskService.create(task);
    }

    @GetMapping(path = "{id}")
    public Task create(@PathVariable Long id) {
        return this.taskService.read(id);
    }

}
