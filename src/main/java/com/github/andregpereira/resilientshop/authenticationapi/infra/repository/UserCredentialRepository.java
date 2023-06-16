package com.github.andregpereira.resilientshop.authenticationapi.infra.repository;

import com.github.andregpereira.resilientshop.authenticationapi.domain.entity.UsuarioCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UsuarioCredential, Long> {

    Optional<UsuarioCredential> findByEmail(String email);

}
