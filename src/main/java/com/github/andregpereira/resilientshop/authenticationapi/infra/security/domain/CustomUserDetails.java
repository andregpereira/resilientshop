package com.github.andregpereira.resilientshop.authenticationapi.infra.security.domain;

import com.github.andregpereira.resilientshop.commons.security.role.Role;
import com.github.andregpereira.resilientshop.authenticationapi.domain.entity.UsuarioCredential;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Set;

@Getter
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Set<Role> authorities;
    private boolean enabled;

    public CustomUserDetails(UsuarioCredential usuario) {
        this.username = usuario.getEmail();
        this.password = usuario.getSenha();
        this.authorities = Collections.singleton(usuario.getRole());
        this.enabled = usuario.isAtivo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
