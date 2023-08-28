package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.mapper.PedidoServiceMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.domain.usecase.PedidoCancelUc;
import com.github.andregpereira.resilientshop.shoppingapi.domain.usecase.PedidoCreateUc;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entity.PedidoEntity;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço de manutenção de {@link PedidoEntity}.
 *
 * @author André Garcia
 * @see PedidoManutencaoService
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class PedidoManutencaoServiceImpl implements PedidoManutencaoService {

    private final PedidoCreateUc createUc;
    private final PedidoCancelUc cancelUc;

    /**
     * Injeção da dependência {@link PedidoServiceMapper} para realizar
     * conversões de DTO e model de pedido.
     */
    private final PedidoServiceMapper mapper;

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
    public PedidoDetalharDto criar(PedidoRegistrarDto dto) {
        return mapper.toPedidoDetalharDto(createUc.criar(mapper.toPedido(dto)));
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
    public void cancelar(Long id) {
        cancelUc.cancelar(id);
    }
//    @Override
//    // TODO refatorar busca por endereço
//    public PedidoDetalharDto criar(PedidoRegistrarDto dto) {
//        PedidoEntity pedido = pedidoMapper.toPedidoEntity(dto);
//        log.info("Procurando usuário...");
//        pedido.setUsuario(usuariosFeignClient.findUsuarioById(dto.idUsuario()));
//        log.info("Usuário OK");
//        log.info("Procurando endereço...");
//        pedido.setEndereco(usuariosFeignClient.consultarEnderecoPorApelido(dto.idUsuario(), dto.enderecoApelido()));
//        log.info("Endereço OK");
//        pedido.setIdEndereco(pedido.getEndereco().getId());
//        log.info("Removendo produtos duplicados...");
//        pedido.setDetalhesPedido(new HashSet<>(pedido.getDetalhesPedido().stream().collect(
//                Collectors.toMap(DetalhePedidoEntity::getIdProduto, Function.identity(),
//                        (oldVal, newVal) -> oldVal.getQuantidade() < newVal.getQuantidade() ? newVal
//                                : oldVal)).values()));
//        log.info("Deduzindo produtos do estoque...");
//        produtosFeignClient.subtrair(coletarProdutos(pedido.getDetalhesPedido()));
//        log.info("Calculando subtotal e setando produto(s)...");
//        pedido.getDetalhesPedido().parallelStream().forEach(
//                dp -> Optional.of(produtosFeignClient.findProdutoById(dp.getIdProduto())).ifPresent(p -> {
//                    dp.setSubtotal(p.getValorUnitario().multiply(BigDecimal.valueOf(dp.getQuantidade())));
//                    dp.setProduto(p);
//                    dp.setPedido(pedido);
//                }));
//        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO.getStatus());
//        log.info("Calculando total...");
//        pedido.setTotal(pedido.getDetalhesPedido().parallelStream().map(DetalhePedidoEntity::getSubtotal).reduce(
//                BigDecimal.ZERO, BigDecimal::add));
//        log.info("Salvando pedido...");
//        return pedidoMapper.toPedidoDetalharDto(pedidoRepository.save(pedido));
//    }
}
