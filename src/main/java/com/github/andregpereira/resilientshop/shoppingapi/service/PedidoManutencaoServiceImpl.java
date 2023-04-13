package com.github.andregpereira.resilientshop.shoppingapi.service;

import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dtos.PedidoRegistrarDto;
import com.github.andregpereira.resilientshop.shoppingapi.domain.Status;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repositories.PedidoRepository;
import com.github.andregpereira.resilientshop.shoppingapi.service.mappers.PedidoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class PedidoManutencaoServiceImpl implements PedidoManutencaoService {

    private final PedidoRepository repository;
    private final PedidoMapper mapper;
    private final UsuarioConsumer usuarioConsumer;
    private final ProdutoConsumer produtoConsumer;

    @Override
    public PedidoDto criar(PedidoRegistrarDto dto) {
//        for (DetalhesPedidoRegistroDto detalhesPedido : dto.detalhesPedido()) {
//            if (produtoConsumer.consultarPorId(detalhesPedido.idProduto()))
//
//        }
//        if (produtoConsumer.consultarPorId(dto.detalhesPedido().g))
        // TODO
        return null;
    }

    @Override
    public String cancelar(Long id) {
        repository.findById(id).ifPresentOrElse((p) -> {
            p.setStatus(Status.CANCELADO);
            repository.save(p);
        }, () -> {
            log.info("Pedido com o id {} cancelado", id);
            throw new RuntimeException();
        });
        return "Pedido cancelado";
    }

}
