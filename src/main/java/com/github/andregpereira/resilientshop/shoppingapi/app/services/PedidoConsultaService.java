package com.github.andregpereira.resilientshop.shoppingapi.app.service;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.produto.ProdutoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.usuario.UsuarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoConsultaService {

    Page<PedidoDto> listar(Pageable pageable);

    PedidoDto consultarPorId(Long id);

    UsuarioDto consultarUsuarioPorId(Long id);

    ProdutoDto getUser(Long id);

}
