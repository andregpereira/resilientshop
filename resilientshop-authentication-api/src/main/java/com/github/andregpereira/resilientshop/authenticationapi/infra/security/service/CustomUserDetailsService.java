package com.github.andregpereira.resilientshop.authenticationapi.infra.security.service;

import com.github.andregpereira.resilientshop.authenticationapi.infra.repository.UserCredentialRepository;
import com.github.andregpereira.resilientshop.authenticationapi.infra.security.domain.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserCredentialRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByEmail(username).map(CustomUserDetails::new).orElseThrow(
                () -> new UsernameNotFoundException("User not found with name: " + username));
    }

}
