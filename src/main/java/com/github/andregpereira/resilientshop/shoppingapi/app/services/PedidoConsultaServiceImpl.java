package com.github.andregpereira.resilientshop.shoppingapi.app.service;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.usuario.UsuarioDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.consumers.ProdutoConsumer;
import com.github.andregpereira.resilientshop.shoppingapi.cross.consumers.UsuarioConsumer;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
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
    private final ProdutoConsumer produtoConsumer;

    @Override
    public Page<PedidoDto> listar(Pageable pageable) {
        // TODO
        return null;
    }

    public PedidoDto consultarPorId(Long id) {
        // TODO
        return null;
    }

    @Override
    public UsuarioDto consultarUsuarioPorId(Long id) {
        // TODO
        return usuarioConsumer.consultarPorId(id);
    }

    @Override
    public ProdutoDto getUser(Long id) {
        // TODO
        return produtoConsumer.consultarPorId(id);
    }

}
