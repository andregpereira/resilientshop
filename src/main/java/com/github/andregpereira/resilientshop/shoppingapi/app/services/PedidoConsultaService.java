package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoConsultaService {

    Page<PedidoDto> listar(Pageable pageable);

    Page<PedidoDto> listarPorUsuario(Long id, Pageable pageable);

    PedidoDetalharDto consultarPorId(Long id);

    Page<PedidoDto> consultarPorStatus(int status, Pageable pageable);

}
