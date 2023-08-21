package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DescontoConsultaService {

    Page<DescontoDto> consultarTodos(Pageable pageable);

    Page<DescontoDto> consultarPorTipoDesconto(String tipoDesconto, Pageable pageable);

    DescontoDto consultarPorId(Long id);

}
