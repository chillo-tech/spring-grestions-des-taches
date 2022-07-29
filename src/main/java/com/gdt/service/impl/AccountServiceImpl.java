package com.gdt.service.impl;

import com.gdt.dto.AuthenticationDTO;
import com.gdt.dto.TokenDTO;
import com.gdt.entity.ConfirmationToken;
import com.gdt.entity.Employee;
import com.gdt.entity.Role;
import com.gdt.enums.UserRole;
import com.gdt.exceptions.BadrequestException;
import com.gdt.repository.RoleRepository;
import com.gdt.security.JWTTokenUtils;
import com.gdt.service.AccountService;
import com.gdt.service.ConfirmationService;
import com.gdt.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmployeeService employeeService;
    private ConfirmationService confirmationService;
    private JWTTokenUtils jwtTokenUtils;

    @Override
    public void signup(Employee employee) {
        // Check PASSWORD
        if(employee.getPassword() != null && employee.getPassword().length() < 8) {
            throw new BadrequestException("Mot de de passe invalide", null);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(employee.getPassword());
        log.info("Mot de passe en clair {} mot de passe crypté {}", employee.getPassword(), encodedPassword);
        employee.setPassword(encodedPassword);
        Role role = this.roleRepository.findByLabel(UserRole.USER).orElse(null);

        List<Role> currentRole = employee.getRoles();
        if(currentRole == null) {
            currentRole = new ArrayList<>();
        }
        currentRole.add(role);
        employee.setRoles(currentRole);

        employee.setEnabled(false);
        employeeService.create(employee);

        confirmationService.sendEmployeeVerificationToken(employee);
    }
    @Transactional
    @Override
    public void activate(String username, String token) {
        ConfirmationToken confirmationToken = this.confirmationService.getEmployeeVerificationToken(username,  token);
        Employee employee = this.employeeService.getByUserName(username);

        if(confirmationToken.getEmployee().getId() != employee.getId()){
             throw new UsernameNotFoundException ("Utilisateur inconnu");
        }
        confirmationToken.setActivation(Instant.now());
        employee.setEnabled(true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.employeeService.getByUserName(username);
    }

    @Override
    public TokenDTO generateTokens(AuthenticationDTO authenticationDTO) {
        Employee employee = this.employeeService.getByUserName(authenticationDTO.getUsername());
        String authenticationToken = this.jwtTokenUtils.generateToken(employee);
        String refreshToken = RandomStringUtils.random(100, true, true);
        TokenDTO tokens  = new TokenDTO();

        // Stocker un objet AUTHDATA (TOKENDTO et EMPLOYEE)

        tokens.setAuthentification(authenticationToken);
        tokens.setRefresh(refreshToken);
        return tokens;
    }
}
