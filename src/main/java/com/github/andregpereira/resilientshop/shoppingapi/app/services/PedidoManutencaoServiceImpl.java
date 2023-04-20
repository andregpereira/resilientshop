package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.DetalhePedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.ProdutoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.DetalhePedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Pedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.Produto;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.StatusPedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutoFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.UsuarioFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.DetalhesPedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.DetalhePedidoEntity;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class PedidoManutencaoServiceImpl implements PedidoManutencaoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final DetalhesPedidoRepository detalhePedidoRepository;
    private final DetalhePedidoMapper detalhePedidoMapper;
    private final UsuarioFeignClient usuarioFeignClient;
    private final ProdutoFeignClient produtoFeignClient;
    private final ProdutoMapper produtoMapper;

    @Override
    public PedidoDetalharDto criar(PedidoRegistrarDto dto) {
        Pedido pedido = pedidoMapper.toPedido(dto);
        log.info("Calculando subtotal e setando produto(s)...");
        List<DetalhePedido> detalhePedido = dto.detalhePedido().parallelStream().map(dpDto -> {
            Produto produto = produtoMapper.toProduto(produtoFeignClient.consultarPorId(dpDto.idProduto()));
            DetalhePedido dp = detalhePedidoMapper.toDetalhePedido(dpDto);
            dp.setProduto(produto);
            dp.setSubtotal(produto.getValorUnitario().multiply(BigDecimal.valueOf(dpDto.quantidade())));
            return dp;
        }).toList();
        log.info("Setando id(s) do(s) produto(s)... ");
        List<DetalhePedidoEntity> detalhePedidoEntity = detalhePedidoRepository.saveAll(
                detalhePedido.parallelStream().map(dp -> {
                    DetalhePedidoEntity dpE = detalhePedidoMapper.toDetalhePedidoEntity(dp);
                    dpE.setIdProduto(dp.getProduto().getId());
                    return dpE;
                }).toList());
        LocalDateTime agora = LocalDateTime.now();
        pedido.setDataCriacao(agora);
        pedido.setDataModificacao(agora);
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO.getStatus());
        log.info("Calculando total...");
        pedido.setTotal(detalhePedido.parallelStream().map(DetalhePedido::getSubtotal).reduce(BigDecimal.ZERO,
                BigDecimal::add));
        PedidoEntity pedidoEntity = pedidoRepository.save(pedidoMapper.toPedidoEntity(pedido));
        detalhePedidoEntity.parallelStream().forEach(dpe -> dpe.setPedido(pedidoEntity));
        detalhePedido = detalhePedidoMapper.toListaDetalhePedido(detalhePedidoRepository.saveAll(detalhePedidoEntity));
        pedido.setDetalhePedido(detalhePedido);
        pedido.setId(pedidoEntity.getId());
        return pedidoMapper.toPedidoDetalharDto(pedido);
    }

    @Override
    public String cancelar(Long id) {
        pedidoRepository.findByIdAndStatusAguardandoPagamento(id).ifPresentOrElse(p -> {
            p.setStatus(StatusPedido.CANCELADO.getStatus());
            pedidoRepository.save(p);
        }, () -> {
            log.info("Pedido aguardando pagamento com id {} não encontrado", id);
            throw new PedidoNotFoundException("Poxa! Não foi encontrado um pedido aguardando pagamento com o id " + id);
        });
        log.info("Pedido com id {} cancelado com sucesso", id);
        return "Pedido cancelado";
    }

}
