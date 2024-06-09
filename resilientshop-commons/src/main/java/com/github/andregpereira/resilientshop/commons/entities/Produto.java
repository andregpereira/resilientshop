package com.github.andregpereira.resilientshop.commons.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private Long id;
    private Long sku;
    private String nome;
    private String descricao;
    private BigDecimal valorUnitario;
    private int estoque;

    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/uuuu HH:mm")
    private LocalDateTime dataCriacao;

    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/uuuu HH:mm")
    private LocalDateTime dataModificacao;
    private boolean ativo;
    private Categoria categoria;
    private Subcategoria subcategoria;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Produto produto = (Produto) o;
        return estoque == produto.estoque && ativo == produto.ativo && Objects.equals(id,
                produto.id) && Objects.equals(sku, produto.sku) && Objects.equals(nome, produto.nome) && Objects.equals(
                descricao, produto.descricao) && Objects.equals(valorUnitario, produto.valorUnitario) && Objects.equals(
                dataCriacao, produto.dataCriacao) && Objects.equals(dataModificacao, produto.dataModificacao) && Objects.equals(
                categoria, produto.categoria) && Objects.equals(subcategoria, produto.subcategoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku, nome, descricao, valorUnitario, estoque, dataCriacao, dataModificacao, ativo, categoria,
                subcategoria);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Produto.class.getSimpleName() + "[", "]").add("id=" + id).add("sku=" + sku).add(
                "nome='" + nome + "'").add("descricao='" + descricao + "'").add("valorUnitario=" + valorUnitario).add(
                "estoque=" + estoque).add("dataCriacao=" + dataCriacao).add("dataModificacao=" + dataModificacao).add(
                "ativo=" + ativo).add("categoria=" + categoria).add("subcategoria=" + subcategoria).toString();
    }

}
