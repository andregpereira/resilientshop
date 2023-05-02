package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.ProdutoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Pedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.enums.StatusPedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutoFeignClient;
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
    private final PedidoMapper pedidoMapper;
    private final ProdutoFeignClient produtoFeignClient;
    private final ProdutoMapper produtoMapper;

    @Override
    public Page<PedidoDto> listar(Pageable pageable) {
        Page<Pedido> pedidos = repository.findAll(pageable).map(pedidoMapper::toPedido);
        if (pedidos.isEmpty()) {
            log.info("Nenhum pedido foi encontrado");
            throw new PedidoNotFoundException();
        }
        log.info("Retornando pedidos");
        return pedidos.map(pedidoMapper::toPedidoDto);
    }

    @Override
    public PedidoDetalharDto consultarPorId(Long id) {
        return repository.findById(id).map(p -> {
            Pedido pedido = pedidoMapper.toPedido(p);
            log.info("Setando produto(s)...");
            pedido.getDetalhePedido().parallelStream().forEach(
                    dp -> dp.setProduto(produtoMapper.toProduto(produtoFeignClient.consultarPorId(dp.getIdProduto()))));
            log.info("Retornando pedido com id " + id);
            return pedidoMapper.toPedidoDetalharDto(pedido);
        }).orElseThrow(() -> {
            log.info("Pedido com id {} não encontrado", id);
            return new PedidoNotFoundException(id);
        });
    }

    @Override
    public Page<PedidoDetalharDto> consultarPorIdUsuario(Long id, Pageable pageable) {
        Page<Pedido> pedidos = repository.findAllByIdUsuario(id, pageable).map(pedidoMapper::toPedido);
        if (pedidos.isEmpty()) {
            log.info("Nenhum pedido foi encontrado");
            throw new PedidoNotFoundException();
        }
        log.info("Retornando pedidos");
        return pedidos.map(pedidoMapper::toPedidoDetalharDto);
    }

    @Override
    public Page<PedidoDto> consultarPorStatus(int status, Pageable pageable) {
        Page<Pedido> pedidos = repository.findAllByStatus(status, pageable).map(pedidoMapper::toPedido);
        if (pedidos.isEmpty()) {
            log.info("Pedidos com status {} ({}) não encontrados", status, StatusPedido.getStatusPorId(status));
            throw new PedidoNotFoundException(status);
        }
        log.info("Retornando pedidos com status {} ({})", status, StatusPedido.getStatusPorId(status));
        return pedidos.map(pedidoMapper::toPedidoDto);
    }

}
