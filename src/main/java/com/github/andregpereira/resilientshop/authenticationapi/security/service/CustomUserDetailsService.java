package com.github.andregpereira.resilientshop.authenticationapi.security.service;

import com.github.andregpereira.resilientshop.authenticationapi.infra.entity.UsuarioCredential;
import com.github.andregpereira.resilientshop.authenticationapi.infra.repository.UserCredentialRepository;
import com.github.andregpereira.resilientshop.authenticationapi.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioCredential> credential = repository.findByEmail(username);
        return credential.map(usuarioCredential -> new CustomUserDetails(usuarioCredential.getEmail(),
                usuarioCredential.getSenha())).orElseThrow(
                () -> new UsernameNotFoundException("user not found with nome : " + username));
    }

}
