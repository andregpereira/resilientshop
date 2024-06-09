package com.github.andregpereira.resilientshop.shoppingapi.app.service;

import com.github.andregpereira.resilientshop.shoppingapi.app.constant.StatusPedido;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDetalharDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.dto.pedido.PedidoDto;
import com.github.andregpereira.resilientshop.shoppingapi.app.mapper.PedidoServiceMapper;
import com.github.andregpereira.resilientshop.shoppingapi.cross.exception.PedidoNotFoundException;
import com.github.andregpereira.resilientshop.shoppingapi.domain.usecase.PedidoFindAllByIdUsuarioUc;
import com.github.andregpereira.resilientshop.shoppingapi.domain.usecase.PedidoFindAllByStatusUc;
import com.github.andregpereira.resilientshop.shoppingapi.domain.usecase.PedidoFindByIdUc;
import com.github.andregpereira.resilientshop.shoppingapi.infra.entity.PedidoEntity;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    private final PedidoFindByIdUc findByIdUc;
    private final PedidoFindAllByIdUsuarioUc findAllByIdUsuarioUc;
    private final PedidoFindAllByStatusUc findAllByStatusUc;

    /**
     * Injeção da dependência {@link PedidoServiceMapper} para realizar
     * conversões de DTO e model de pedido.
     */
    private final PedidoServiceMapper mapper;

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
        return mapper.toPedidoDetalharDto(findByIdUc.findById(id));
    }

    /**
     * Lista todos os {@linkplain PedidoEntity pedidos} de um usuário.
     * Retorna uma {@linkplain Page sublista} de uma lista de {@linkplain PedidoDto pedidos}.
     *
     * @param id       o {@code id} do usuário.
     * @param pageable o {@code pageable} padrão.
     *
     * @return uma sublista de uma lista com todos os pedidos do usuário.
     */
    @Override
    public Page<PedidoDto> consultarPorIdUsuario(Long id, Pageable pageable) {
        return findAllByIdUsuarioUc.findAllByIdUsuario(id, pageable).map(mapper::toPedidoDto);
    }

    @Override
    public Page<PedidoDto> consultarPorStatus(StatusPedido status, Pageable pageable) {
        return findAllByStatusUc.findAllByStatus(status.getStatus(), pageable).map(mapper::toPedidoDto);
    }

}
