package com.ecommerce.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.model.RegisterDTO;
import com.ecommerce.ecommerce.model.UsuarioEntity;
import com.ecommerce.ecommerce.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepositorio;

    @GetMapping(value = "/usuario/load/{id}")
    public Optional<UsuarioEntity> get(@PathVariable long id) {
        Optional<UsuarioEntity> _user = usuarioRepositorio.findById(id);
        return _user;
    }

    @PostMapping (value = "/usuario/salvar")
    public ResponseEntity salvar(@RequestBody RegisterDTO data){
        if (this.usuarioRepositorio.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        UsuarioEntity new_user = new UsuarioEntity(data.nome(), data.email(),encryptedPassword,data.role());

        this.usuarioRepositorio.save(new_user);
        return new ResponseEntity<>(new_user, HttpStatus.CREATED);
    }   

    @GetMapping(value="/usuario/listar")
    public List<UsuarioEntity> listar() {
    return usuarioRepositorio.findAll();
    }

    @DeleteMapping(path = {"/usuario/{id}"})
    public ResponseEntity <?> deletar(@PathVariable long id) {
        usuarioRepositorio.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping (value = "/usuario/{id}")
    public ResponseEntity<UsuarioEntity> atualizar(
    @RequestBody UsuarioEntity usuario,
    @PathVariable Long id) {
        UsuarioEntity _user = usuarioRepositorio.findById(id).get();
        _user = usuario;
        usuarioRepositorio.save(_user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/usuario/pesquisar/{termo}")
    public ResponseEntity pesquisar(
        @PathVariable String termo
    ){
        List<UsuarioEntity> list = this.usuarioRepositorio.findByNomeContaining(termo);

        return ResponseEntity.ok(list);
    }
}
   

