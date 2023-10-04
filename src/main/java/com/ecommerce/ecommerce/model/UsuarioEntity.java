package com.ecommerce.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "usuario")
public class UsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String nome;

    public String email;

    public String senha;

}
