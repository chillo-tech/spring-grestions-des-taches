package com.gdt.dto;

import com.sun.istack.NotNull;

public class UserTask {
    @NotNull
    Long taskId;
    @NotNull
    Long employeeId;

    public UserTask() {
    }

    public UserTask(Long taskId, Long employeeId) {
        this.taskId = taskId;
        this.employeeId = employeeId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
