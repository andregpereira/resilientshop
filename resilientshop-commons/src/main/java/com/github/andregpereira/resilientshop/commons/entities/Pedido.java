package com.github.andregpereira.resilientshop.commons.entities;

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
    private String enderecoApelido;
    private int status;
    private String cupom;
    private BigDecimal total;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private Long idUsuario;
    private Long idEndereco;
    private Usuario usuario;
    private Endereco endereco;
    private Set<DetalhePedido> detalhesPedido;

}
