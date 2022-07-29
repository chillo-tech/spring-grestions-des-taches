package com.gdt.controller;

import com.gdt.dto.ActivationDTO;
import com.gdt.dto.AuthenticationDTO;
import com.gdt.dto.TokenDTO;
import com.gdt.entity.Employee;
import com.gdt.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AccountCountroller {
    private AuthenticationManager authenticationManager;
    private AccountService accountService;

    @PostMapping(path = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody TokenDTO signin(@RequestBody AuthenticationDTO authenticationDTO){
        // auth via spring security
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDTO.getUsername(),
                        authenticationDTO.getPassword()
                )
        );
        // Générer tokens
        return this.accountService.generateTokens(authenticationDTO);
    }

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
}
