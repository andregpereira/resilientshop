package com.github.andregpereira.resilientshop.commons.entities;

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
public class Endereco {

    private Long id;
    private String apelido;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
    private boolean padrao;
    private Pais pais;
    private Usuario usuario;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Endereco endereco))
            return false;
        return Objects.equals(id, endereco.id) && Objects.equals(apelido, endereco.apelido) && Objects.equals(cep,
                endereco.cep) && Objects.equals(estado, endereco.estado) && Objects.equals(cidade,
                endereco.cidade) && Objects.equals(bairro, endereco.bairro) && Objects.equals(rua,
                endereco.rua) && Objects.equals(numero, endereco.numero) && Objects.equals(complemento,
                endereco.complemento) && Objects.equals(pais, endereco.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apelido, cep, estado, cidade, bairro, rua, numero, complemento, pais);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Endereco.class.getSimpleName() + "[", "]").add("id=" + id).add(
                "apelido='" + apelido + "'").add("cep='" + cep + "'").add("estado='" + estado + "'").add(
                "cidade='" + cidade + "'").add("bairro='" + bairro + "'").add("rua='" + rua + "'").add(
                "numero='" + numero + "'").add("complemento='" + complemento + "'").add("pais=" + pais).toString();
    }

}
