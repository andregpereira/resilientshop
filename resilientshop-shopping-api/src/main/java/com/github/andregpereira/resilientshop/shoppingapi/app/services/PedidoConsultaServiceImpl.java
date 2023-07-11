package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.UsuariosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.persistence.PedidoEntity;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.function.Predicate.not;

/**
 * Classe de serviço de consulta de {@link PedidoEntity}.
 *
 * @author André Garcia
 * @see PedidoConsultaService
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class PedidoConsultaServiceImpl implements PedidoConsultaService {

    /**
     * Injeção da dependência {@link PedidoRepository} para realizar operações de
     * consulta na tabela de pedidos no banco de dados.
     */
    private final PedidoRepository repository;

    /**
     * Injeção da dependência {@link PedidoMapper} para realizar
     * conversões de entidade para DTO de pedidos.
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

    /**
     * Lista todos os {@linkplain PedidoEntity pedidos} cadastrados.
     * Retorna uma {@linkplain Page sublista} de uma lista de {@link PedidoDto pedidos}.
     *
     * @param pageable o {@code pageable} padrão.
     *
     * @return uma sublista de uma lista com todos os pedidos cadastrados.
     */
    @Override
    public Page<PedidoDto> listar(Pageable pageable) {
        return Optional.of(repository.findAll(pageable)).filter(not(Page::isEmpty)).map(p -> {
            log.info("Retornando todos os pedidos");
            return p.map(pedidoMapper::toPedidoDto);
        }).orElseThrow(() -> {
            log.info("Nenhum pedido foi encontrado");
            return new PedidoNotFoundException();
        });
    }

    /**
     * Lista todos os {@linkplain PedidoEntity pedidos} de um usuário.
     * Retorna uma {@linkplain Page sublista} de uma lista de {@link PedidoDto pedidos}.
     *
     * @param id       o {@code id} do usuário.
     * @param pageable o {@code pageable} padrão.
     *
     * @return uma sublista de uma lista com todos os pedidos do usuário.
     */
    @Override
    public Page<PedidoDto> listarPorUsuario(Long id, Pageable pageable) {
        log.info("Procurando usuário com id {}...", id);
        return Optional.of(
                repository.findAllByIdUsuario(usuariosFeignClient.consultarUsuarioPorId(id).getId(), pageable)).filter(
                not(Page::isEmpty)).map(p -> {
            log.info("Retornando todos os pedidos do usuário");
            return p.map(pedidoMapper::toPedidoDto);
        }).orElseThrow(() -> {
            log.info("Nenhum pedido foi encontrado");
            return new PedidoNotFoundException();
        });
    }

    /**
     * Pesquisa um {@linkplain PedidoEntity pedido} por {@code id}.
     * Retorna um {@linkplain PedidoDetalharDto pedido detalhado}.
     *
     * @param id o {@code id} do pedido a ser consultado.
     *
     * @return um pedido encontrado pelo {@code id}.
     *
     * @throws PedidoNotFoundException           caso o pedido não seja encontrado.
     * @throws FeignException.NotFound           caso o usuário ou os produtos não sejam encontrados.
     * @throws FeignException.ServiceUnavailable caso a API de Uusários ou Produtos estejam indisponíveis.
     */
    @Override
    public PedidoDetalharDto consultarPorId(Long id) {
        return repository.findById(id).map(p -> {
            log.info("Setando usuário...");
            p.setUsuario(usuariosFeignClient.consultarUsuarioPorId(p.getIdUsuario()));
            log.info("Setando endereço...");
            p.setEndereco(usuariosFeignClient.consultarEnderecoPorId(p.getIdEndereco()));
            log.info("Setando produto(s)...");
            p.getDetalhePedido().parallelStream().forEach(
                    dp -> dp.setProduto(produtosFeignClient.consultarPorId(dp.getIdProduto())));
            log.info("Retornando pedido com id " + id);
            return pedidoMapper.toPedidoDetalharDto(p);
        }).orElseThrow(() -> {
            log.info("Pedido com id {} não encontrado", id);
            return new PedidoNotFoundException(id);
        });
    }

    @Override
    public Page<PedidoDto> consultarPorStatus(int status, Pageable pageable) {
        return Optional.of(repository.findAllByStatus(status, pageable)).filter(not(Page::isEmpty)).map(p -> {
            log.info("Retornando todos os usuários");
            return p.map(pedidoMapper::toPedidoDto);
        }).orElseThrow(() -> {
            log.info("Nenhum usuário foi encontrado");
            return new PedidoNotFoundException(status);
        });
    }

}
