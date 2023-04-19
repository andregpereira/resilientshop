package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.DetalhePedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.ProdutoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Pedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutoFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.UsuarioFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class PedidoConsultaServiceImpl implements PedidoConsultaService {

    private final PedidoRepository repository;
    private final PedidoMapper pedidoMapper;
    private final DetalhePedidoMapper detalhePedidoMapper;
    private final ProdutoMapper produtoMapper;
    private final UsuarioFeignClient usuarioFeignClient;
    private final ProdutoFeignClient produtoFeignClient;

    @Override
    public Page<PedidoDto> listar(Pageable pageable) {
        Page<PedidoEntity> pagePedidos = repository.findAll(pageable);
        if (pagePedidos.isEmpty()) {
            log.info("Nenhum pedido foi encontrado");
            throw new PedidoNotFoundException("Poxa! Ainda não há pedidos cadastrados");
        }
        Page<Pedido> pedidos = pagePedidos.map(pedidoMapper::toPedido);
        log.info("Retornando pedidos");
        return pedidos.map(pedidoMapper::toPedidoDto);
    }

    public PedidoDetalharDto consultarPorId(Long id) {
        Optional<PedidoEntity> optionalPedido = repository.findById(id);
        if (optionalPedido.isEmpty()) {
            log.info("Pedido com id {} não encontrado", id);
            throw new PedidoNotFoundException(id);
        }
        Pedido pedido = pedidoMapper.toPedido(optionalPedido.get());
        log.info("Setando produto(s)...");
        pedido.getDetalhePedido().parallelStream().forEach(
                dp -> dp.setProduto(produtoMapper.toProduto(produtoFeignClient.consultarPorId(dp.getIdProduto()))));
        log.info("Retornando pedido");
        return pedidoMapper.toPedidoDetalharDto(pedido);
    }

}
