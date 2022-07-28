package com.gdt.repository;

import com.gdt.entity.ConfirmationToken;
import com.gdt.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;

/**
 * Manipoulation tache dans la  bse de dpnnées
 */
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    /**
     * SELECT * from confirmation_token ct join empployee e on  e.id = ct.id where ct.value = value and e.username = :username
     * @param value
     * @param username
     * @return
     */
    Optional<ConfirmationToken> findByValueAndEmployeeUserName(String value, String username);
    Optional<ConfirmationToken> findByValueAndEmployeeUserNameAndActivationNull(String token, String username);
    Optional<ConfirmationToken> findByValueAndEmployeeId(String value, int id);
}
