package com.gdt.service.impl;

import com.gdt.entity.Task;
import com.gdt.repository.TaskRepository;
import com.gdt.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void create(Task task) {
        // check TI > TR
        // TR = TI
        task.setRt(task.getIt());
        this.taskRepository.save(task);
    }

    @Override
    public Task read(Long id) {
        return this.taskRepository.findById(id).orElse(null);
    }
}
