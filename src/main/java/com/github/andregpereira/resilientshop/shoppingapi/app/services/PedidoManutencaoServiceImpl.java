package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.pedido.PedidoRegistrarDto;
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
    private final DetalhesPedidoRepository detalhePedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final DetalhePedidoMapper detalhePedidoMapper;
    private final ProdutoMapper produtoMapper;
    private final UsuarioFeignClient usuarioFeignClient;
    private final ProdutoFeignClient produtoFeignClient;

    @Override
    public PedidoDetalharDto criar(PedidoRegistrarDto dto) {
        Pedido pedido = pedidoMapper.toPedido(dto);
        List<DetalhePedido> detalhePedido = dto.detalhePedido().stream().parallel().map(dpDto -> {
            Produto produto = produtoMapper.toProduto(produtoFeignClient.consultarPorId(dpDto.idProduto()));
            DetalhePedido dp = detalhePedidoMapper.toDetalhePedido(dpDto);
            dp.setProduto(produto);
            dp.setPedido(pedido);
            dp.setSubtotal(produto.getValorUnitario().multiply(BigDecimal.valueOf(dpDto.quantidade())));
            return dp;
        }).toList();
        List<DetalhePedidoEntity> detalhePedidoEntity = detalhePedidoRepository.saveAll(
                detalhePedidoMapper.toListaDetalhePedidoEntity(detalhePedido));
        LocalDateTime agora = LocalDateTime.now();
        pedido.setDataCriacao(agora);
        pedido.setDataModificacao(agora);
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO.getStatus());
        pedido.setTotal(detalhePedido.stream().parallel().map(DetalhePedido::getSubtotal).reduce(BigDecimal.ZERO,
                BigDecimal::add));
        PedidoEntity pedidoEntity = pedidoRepository.save(pedidoMapper.toPedidoEntity(pedido));
        detalhePedidoEntity = detalhePedidoEntity.stream().parallel().map(dpE -> {
            dpE.setPedido(pedidoEntity);
            return dpE;
        }).toList();
        detalhePedido = detalhePedidoMapper.toListaDetalhePedido(detalhePedidoRepository.saveAll(detalhePedidoEntity));
        pedido.setDetalhePedido(detalhePedido);
        pedido.setId(pedidoEntity.getId());
        return pedidoMapper.toPedidoDetalharDto(pedido);
    }

    @Override
    public String cancelar(Long id) {
        pedidoRepository.findById(id).ifPresentOrElse(p -> {
            if (p.getStatus() != 1) {
                log.info("Pedido com status {}({}). Não é possível cancelar o pedido",
                        StatusPedido.getStatusPorId(p.getStatus()), p.getStatus());
                throw new RuntimeException();
            }
            p.setStatus(StatusPedido.CANCELADO.getStatus());
            pedidoRepository.save(p);
        }, () -> {
            log.info("Pedido com o id {} cancelado", id);
            throw new RuntimeException();
        });
        return "Pedido cancelado";
    }

}
