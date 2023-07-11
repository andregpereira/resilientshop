package com.github.andregpereira.resilientshop.authenticationapi.domain.entity;

import com.github.andregpereira.resilientshop.commons.security.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuarios_credentials",
        uniqueConstraints = {@UniqueConstraint(name = "uc_email", columnNames = "email")})
@SequenceGenerator(name = "usuario_credential", sequenceName = "sq_usuarios_credentials", allocationSize = 1)
public class UsuarioCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_credential")
    @Column(name = "id_usuario_credential", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean ativo;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UsuarioCredential usuario))
            return false;
        return ativo == usuario.ativo && Objects.equals(id, usuario.id) && Objects.equals(email,
                usuario.email) && Objects.equals(senha, usuario.senha) && role == usuario.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, senha, role, ativo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UsuarioCredential.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "email='" + email + "'").add("senha='" + senha + "'").add("role=" + role).add(
                "ativo=" + ativo).toString();
    }

}
