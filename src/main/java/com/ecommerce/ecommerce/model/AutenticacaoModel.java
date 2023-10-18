package com.ecommerce.ecommerce.model;

public class AutenticacaoModel {

    private String email;
    private String senha;

    public void setSenha(String senha){
        this.senha = senha;
    }
    public String getSenha(){
       return this.senha;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }    
    
}
