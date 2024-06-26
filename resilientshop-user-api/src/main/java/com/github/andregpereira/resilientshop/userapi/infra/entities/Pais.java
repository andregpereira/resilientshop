package com.github.andregpereira.resilientshop.userapi.infra.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_paises", uniqueConstraints = {@UniqueConstraint(name = "uc_nome", columnNames = "nome"),
        @UniqueConstraint(name = "uc_codigo", columnNames = "codigo")})
@SequenceGenerator(name = "pais", sequenceName = "sq_paises", allocationSize = 1)
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais")
    @Column(name = "id_pais")
    private Long id;

    @Column(length = 45, nullable = false)
    private String nome;

    @Column(length = 4, nullable = false)
    private String codigo;

    @OneToMany(mappedBy = "pais")
    private List<Endereco> enderecos;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Pais pais))
            return false;
        return Objects.equals(id, pais.id) && Objects.equals(nome, pais.nome) && Objects.equals(codigo, pais.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, codigo);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pais.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "nome='" + nome + "'").add("codigo='" + codigo + "'").toString();
    }

}
