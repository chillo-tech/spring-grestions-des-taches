package com.gdt.service.impl;

import com.gdt.entity.ConfirmationToken;
import com.gdt.entity.Employee;
import com.gdt.exceptions.BadrequestException;
import com.gdt.repository.ConfirmationTokenRepository;
import com.gdt.service.ConfirmationService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class ConfirmationServiceImpl implements ConfirmationService {
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Override
    public void sendEmployeeVerificationToken(Employee employee) {
        // Générer un token
        String valdationToken = RandomStringUtils.random(6, false, true);

        // Sauvegarder le token
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setValue(valdationToken);
        confirmationToken.setEmployee(employee);
        confirmationToken.setCreation(Instant.now());
        this.confirmationTokenRepository.save(confirmationToken);

        // Envoyer le TOKEN SMS/MAIL

    }

    @Override
    public ConfirmationToken getEmployeeVerificationToken(String username, String token) {
        ConfirmationToken confirmationToken = this.confirmationTokenRepository
                .findByValueAndEmployeeUserNameAndActivationNull(token, username)
                .orElseThrow(() -> new BadrequestException("Le compte est activé ou votre code est invalide", null ));

        if (ChronoUnit.MINUTES.between(confirmationToken.getCreation(), Instant.now()) > 10) {
           throw new BadrequestException("Le délai d'activation est dépassé", null );
        }

        return confirmationToken;
    }
}
