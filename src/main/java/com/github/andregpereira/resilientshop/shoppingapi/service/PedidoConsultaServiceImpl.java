package com.github.andregpereira.resilientshop.shoppingapi.service;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.service.mappers.PedidoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PedidoConsultaServiceImpl implements PedidoConsultaService {

    private final PedidoRepository repository;
    private final PedidoMapper mapper;
    private final UsuarioConsumer usuarioConsumer;

    @Override
    public Page<PedidoDto> listar(Pageable pageable) {
        // TODO
        return null;
    }

    public PedidoDto consultarPorId(Long id) {
        // TODO
        return null;
    }

}
