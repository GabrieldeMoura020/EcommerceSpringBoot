package com.ecommerce.ecommerce.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter 
@Getter
@Table(name = "usuario")
@Entity(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class UsuarioEntity implements UserDetails {
        
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public Long id;
    
        public String nome;
    
        public String email;
    
        public String senha;
    
        public UserRole role;
    
        public UsuarioEntity(String nome, String email, String senha, UserRole role){
            this.nome   = nome;
            this.email  = email;
            this.senha  = senha;
            this.role   = role;
        }
    
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            // TODO Auto-generated method stub
            
            if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
            else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    
        @Override
        public String getPassword() {
            // TODO Auto-generated method stub
            return senha;
        }
    
        @Override
        public String getUsername() {
            // TODO Auto-generated method stub
            return email;
        }
    
        @Override
        public boolean isAccountNonExpired() {
            // TODO Auto-generated method stub
            return true;
        }
    
        @Override
        public boolean isAccountNonLocked() {
            // TODO Auto-generated method stub
            return true;
        }
    
        @Override
        public boolean isCredentialsNonExpired() {
            // TODO Auto-generated method stub
            return true;
        }
    
        @Override
        public boolean isEnabled() {
            // TODO Auto-generated method stub
            return true;
        }
}
