package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.model.UsuarioEntity;
import com.ecommerce.ecommerce.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepositorio;

    @GetMapping(value = "/usuario/listar")
    public List<UsuarioEntity> getMethodName(){
        return usuarioRepositorio.findAll();
    }

    @PostMapping(value = "/usuario")
    public ResponseEntity<UsuarioEntity> salvar(@RequestBody UsuarioEntity usuario) {
        UsuarioEntity _user = usuarioRepositorio.save(usuario);
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

    @DeleteMapping(path = {"/usuario/{id}"})
    public ResponseEntity <?> deletar(@PathVariable long id) {
        usuarioRepositorio.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
