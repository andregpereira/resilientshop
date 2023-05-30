package com.github.andregpereira.resilientshop.shoppingapi.app.services;

import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exceptions.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.cross.mappers.PedidoMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.ProdutosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclients.UsuariosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.function.Predicate.not;

@RequiredArgsConstructor
@Slf4j
@Service
public class PedidoConsultaServiceImpl implements PedidoConsultaService {

    private final PedidoRepository repository;
    private final PedidoMapper pedidoMapper;
    private final UsuariosFeignClient usuariosFeignClient;
    private final ProdutosFeignClient produtosFeignClient;

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

    @Override
    public PedidoDetalharDto consultarPorId(Long id) {
        return repository.findById(id).map(p -> {
            log.info("Setando usuário...");
            p.setUsuario(usuariosFeignClient.consultarUsuarioPorId(p.getIdUsuario()));
            log.info("Setando endereço...");
            p.setEndereco(usuariosFeignClient.consultarEnderecoPorId(p.getIdEndereco(), p.getUsuario().getId()));
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
