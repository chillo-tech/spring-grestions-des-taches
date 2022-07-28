package com.gdt.service;

import com.gdt.entity.Task;

public interface TaskService {
    void create(Task task);
    Task read(Integer id);
}
