package com.github.andregpereira.resilientshop.shoppingapi.domain.model;

import com.github.andregpereira.resilientshop.commons.entities.Endereco;
import com.github.andregpereira.resilientshop.commons.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private int status;
    private BigDecimal total;
    private Long idUsuario;
    private Long idEndereco;
    private Set<DetalhePedido> detalhesPedido;
    private Usuario usuario;
    private Endereco endereco;

}
