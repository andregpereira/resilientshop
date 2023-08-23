package com.github.andregpereira.resilientshop.authenticationapi.infra.config;

import com.github.andregpereira.resilientshop.commons.security.role.Role;
import com.github.andregpereira.resilientshop.authenticationapi.domain.entity.UsuarioCredential;
import com.github.andregpereira.resilientshop.authenticationapi.infra.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class SuperAdminConfig {

    private final UserCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initSuperAdmin() {
        return args -> {
            log.info("Criando Super Admin...");
            if (!repository.existsByRole(Role.SUPER_ADMIN)) {
                repository.save(
                        new UsuarioCredential(null, "super_admin@resilientshop.com", passwordEncoder.encode("SuperAdmin123"),
                                Role.SUPER_ADMIN, true));
                log.info("Super Admin criado");
                return;
            }
            log.info("Super Admin já existe");
        };
    }

}
