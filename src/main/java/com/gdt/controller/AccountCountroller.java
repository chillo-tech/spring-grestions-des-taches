package com.gdt.controller;

import com.gdt.dto.ActivationDTO;
import com.gdt.dto.AuthenticationRequestDTO;
import com.gdt.entity.Employee;
import com.gdt.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@AllArgsConstructor
@RestController
public class AccountCountroller {


    private AuthenticationManager authenticationManager;
    private AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signup(@RequestBody Employee employee){
        this.accountService.signup(employee);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(path = "activate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void activate(@RequestBody ActivationDTO activationDTO){
        this.accountService.activate(activationDTO.getUsername(), activationDTO.getToken());
    }

    @PostMapping(path = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void connexion(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        authenticate(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword());
        log.info("SUCCESS LOGIN WITH {} AND {}", authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword());
    }

    private void authenticate(String username, String password) {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new IllegalArgumentException("CREDENTIALS_INVALID");
        }
    }
}
