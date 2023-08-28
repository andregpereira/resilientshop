package com.github.andregpereira.resilientshop.shoppingapi.infra.dataprovider;

import com.github.andregpereira.resilientshop.shoppingapi.cross.exception.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.PedidoGateway;
import com.github.andregpereira.resilientshop.shoppingapi.domain.model.Pedido;
import com.github.andregpereira.resilientshop.shoppingapi.infra.mapper.PedidoDataProviderMapper;
import com.github.andregpereira.resilientshop.shoppingapi.infra.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
@Slf4j
@Service
public class PedidoDataProvider implements PedidoGateway {

    /**
     * Injeção da dependência {@link PedidoRepository} para realizar operações de
     * consulta e manutenção na tabela de pedidos no banco de dados.
     */
    private final PedidoRepository repository;

    /**
     * Injeção da dependência {@link PedidoDataProviderMapper} para realizar
     * conversões de model e entidade de pedido.
     */
    private final PedidoDataProviderMapper mapper;

    @Override
    @Transactional
    public Pedido save(Pedido pedido) {
        return mapper.toPedido(repository.save(mapper.toPedidoEntity(pedido)));
    }

    @Override
    public Pedido findById(Long id, UnaryOperator<Pedido> op) {
        return op.apply(repository.findById(id).map(mapper::toPedido).orElseThrow(
                () -> new PedidoNotFoundException(id)));
    }

    @Override
    public void findByIdAndStatusAguardandoPagamento(Long id, Consumer<Pedido> op) {
        op.accept(repository.findByIdAndStatusAguardandoPagamento(id).map(mapper::toPedido).orElseThrow(
                () -> new PedidoNotFoundException(id)));
    }

    @Override
    public Page<Pedido> findAllByIdUsuario(Long id, Pageable pageable) {
        return repository.findAllByIdUsuario(id, pageable).map(mapper::toPedido);
    }

    @Override
    public Page<Pedido> findAllByStatus(Integer status, Pageable pageable) {
        return repository.findAllByStatus(status, pageable).map(mapper::toPedido);
    }

}
