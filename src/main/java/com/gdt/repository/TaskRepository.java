package com.gdt.repository;

import com.gdt.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Manipoulation tache dans la  bse de dpnnées
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
