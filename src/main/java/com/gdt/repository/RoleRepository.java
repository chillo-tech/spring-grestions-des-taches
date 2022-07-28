package com.gdt.repository;

import com.gdt.entity.Role;
import com.gdt.entity.Task;
import com.gdt.enums.UserRole;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Manipoulation tache dans la  bse de dpnnées
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByLabel(UserRole label);
}
