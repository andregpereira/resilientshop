package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.constant.StatusPedido;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoConsultaService {

    PedidoDetalharDto consultarPorId(Long id);

    Page<PedidoDto> consultarPorIdUsuario(Long id, Pageable pageable);

    Page<PedidoDto> consultarPorStatus(StatusPedido status, Pageable pageable);

}
