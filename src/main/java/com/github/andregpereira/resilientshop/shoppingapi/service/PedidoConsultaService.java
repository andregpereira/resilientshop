package com.github.andregpereira.resilientshop.shoppingapi.service;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.PedidoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoConsultaService {

    Page<PedidoDto> listar(Pageable pageable);

    PedidoDto consultarPorId(Long id);

}
