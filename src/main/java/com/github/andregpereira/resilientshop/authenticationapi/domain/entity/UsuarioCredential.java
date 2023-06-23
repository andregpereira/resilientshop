package com.github.andregpereira.resilientshop.authenticationapi.domain.entity;

import com.github.andregpereira.resilientshop.commons.security.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "usuario_credential", sequenceName = "sq_usuarios-credentials", allocationSize = 1)
public class UsuarioCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_credential")
    @Column(name = "id_usuario_credential")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Role role;

    @Override
    public String toString() {
        return new StringJoiner(", ", UsuarioCredential.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "nome='" + nome + "'").add("email='" + email + "'").add("senha='" + senha + "'").add(
                "role=" + role).toString();
    }

}
