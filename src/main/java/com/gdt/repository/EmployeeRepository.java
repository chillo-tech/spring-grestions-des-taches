package com.gdt.repository;

import com.gdt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Accès à la base de données
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUserName(String username);
    @Query("SELECT e from Employee e where e.userName LIKE CONCAT('%',:username, '%')")
    Stream<Employee> search(@Param("username") String username);
}
