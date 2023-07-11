package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.produto.ProdutoAtualizarEstoqueDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entities.enums.StatusPedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.UsuariosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.DetalhePedidoEntity;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Classe de serviço de manutenção de {@link PedidoEntity}.
 *
 * @author André Garcia
 * @see PedidoManutencaoService
 */
@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class PedidoManutencaoServiceImpl implements PedidoManutencaoService {

    /**
     * Injeção da dependência {@link PedidoRepository} para realizar operações de
     * consulta e manutenção na tabela de pedidos no banco de dados.
     */
    private final PedidoRepository pedidoRepository;

    /**
     * Injeção da dependência {@link PedidoMapper} para realizar
     * conversões de DTO e entidade de pedidos.
     */
    private final PedidoMapper pedidoMapper;

    /**
     * Injeção da dependência {@link UsuariosFeignClient} para realizar
     * requisoções na API de Usuários.
     */
    private final UsuariosFeignClient usuariosFeignClient;

    /**
     * Injeção da dependência {@link ProdutosFeignClient} para realizar
     * requisoções na API de Produtos.
     */
    private final ProdutosFeignClient produtosFeignClient;

    private static Set<ProdutoAtualizarEstoqueDto> coletarProdutos(List<DetalhePedidoEntity> detalhePedido) {
        return detalhePedido.stream().map(
                dp -> new ProdutoAtualizarEstoqueDto(dp.getIdProduto(), dp.getQuantidade())).collect(
                Collectors.toSet());
    }

    /**
     * Cancela um {@linkplain PedidoEntity pedido} por {@code id}.
     * Retorna uma mensagem de confirmação de cancelamento.
     *
     * @param id o {@code id} do pedido a ser cancelado.
     *
     * @return uma mensagem de confirmação de cancelamento.
     *
     * @throws PedidoNotFoundException           caso o pedido não seja encontrado.
     * @throws FeignException.ServiceUnavailable caso a API de Produtos esteja indisponível.
     */
    @Override
    public String cancelar(Long id) {
        return pedidoRepository.findByIdAndStatusAguardandoPagamento(id).map(p -> {
            log.info("Retornando produtos ao estoque...");
            produtosFeignClient.retornarEstoque(coletarProdutos(p.getDetalhePedido()));
            p.setStatus(StatusPedido.CANCELADO.getStatus());
            pedidoRepository.save(p);
            log.info("Pedido com id {} cancelado com sucesso", id);
            return MessageFormat.format("Pedido com id {0} cancelado", id);
        }).orElseThrow(() -> {
            log.info("Pedido aguardando pagamento com id {} não encontrado", id);
            return new PedidoNotFoundException(
                    MessageFormat.format("Poxa! Não foi encontrado um pedido aguardando pagamento com o id {0}", id));
        });
    }

    /**
     * Cadastra um {@linkplain PedidoEntity pedido}.
     * Retorna um {@linkplain PedidoDetalharDto pedido detalhado}.
     *
     * @param dto o pedido a ser cadastrado.
     *
     * @return o pedido salvo no banco de dados.
     *
     * @throws FeignException.NotFound           caso o usuário, endereço ou produto não sejam encontrados.
     * @throws FeignException.ServiceUnavailable caso a API de Usuários ou Produtos estejam indisponíveis.
     */
    @Override
    // TODO refatorar busca por endereço
    public PedidoDetalharDto criar(PedidoRegistrarDto dto) {
        PedidoEntity pedido = pedidoMapper.toPedidoEntity(dto);
        log.info("Procurando usuário...");
        pedido.setUsuario(usuariosFeignClient.consultarUsuarioPorId(dto.idUsuario()));
        log.info("Usuário OK");
        log.info("Procurando endereço...");
        pedido.setEndereco(usuariosFeignClient.consultarEnderecoPorApelido(dto.idUsuario(), dto.enderecoApelido()));
        log.info("Endereço OK");
        pedido.setIdEndereco(pedido.getEndereco().getId());
        log.info("Removendo produtos duplicados...");
        pedido.setDetalhePedido(new ArrayList<>(pedido.getDetalhePedido().stream().collect(
                Collectors.toMap(DetalhePedidoEntity::getIdProduto, Function.identity(),
                        (oldVal, newVal) -> oldVal.getQuantidade() < newVal.getQuantidade() ? newVal
                                : oldVal)).values()));
        log.info("Deduzindo produtos do estoque...");
        produtosFeignClient.subtrair(coletarProdutos(pedido.getDetalhePedido()));
        log.info("Calculando subtotal e setando produto(s)...");
        pedido.getDetalhePedido().parallelStream().forEach(
                dp -> Optional.of(produtosFeignClient.consultarPorId(dp.getIdProduto())).ifPresent(p -> {
                    dp.setSubtotal(p.getValorUnitario().multiply(BigDecimal.valueOf(dp.getQuantidade())));
                    dp.setProduto(p);
                    dp.setPedido(pedido);
                }));
        LocalDateTime agora = LocalDateTime.now();
        pedido.setDataCriacao(agora);
        pedido.setDataModificacao(agora);
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO.getStatus());
        log.info("Calculando total...");
        pedido.setTotal(
                pedido.getDetalhePedido().parallelStream().map(DetalhePedidoEntity::getSubtotal).reduce(BigDecimal.ZERO,
                        BigDecimal::add));
        log.info("Salvando pedido...");
        return pedidoMapper.toPedidoDetalharDto(pedidoRepository.save(pedido));
    }

}
