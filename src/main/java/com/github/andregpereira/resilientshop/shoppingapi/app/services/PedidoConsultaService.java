package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoConsultaService {

    Page<PedidoDto> listar(Pageable pageable);

    PedidoDetalharDto consultarPorId(Long id);

}
