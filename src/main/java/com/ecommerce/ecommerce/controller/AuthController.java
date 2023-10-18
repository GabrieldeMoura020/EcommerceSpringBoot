package com.ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.model.AuthenticationDTO;
import com.ecommerce.ecommerce.model.LoginResponseDTO;
import com.ecommerce.ecommerce.model.UsuarioEntity;
import com.ecommerce.ecommerce.repository.UsuarioRepository;
import com.ecommerce.ecommerce.service.TokenService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {
    
    @Autowired
    public UsuarioRepository repositorio;

    @Autowired
    public AuthenticationManager auth_manager;

    @Autowired
    private TokenService token_service;

    @PostMapping(value="/auth")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.auth_manager.authenticate(usernamePassword);
        var token = token_service.generateToken((UsuarioEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
