package com.github.andregpereira.resilientshop.discountsapi.app.service.desconto;

import com.github.andregpereira.resilientshop.discountsapi.app.dto.desconto.DescontoDto;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public sealed interface DescontoConsultaService permits DescontoConsultaServiceImpl {

    Page<DescontoDto> consultarTodos(Pageable pageable);

    Page<DescontoDto> consultarTodosByTipoDesconto(String tipoDesconto, Pageable pageable);

    DescontoDto consultarPorId(Long id);

}
