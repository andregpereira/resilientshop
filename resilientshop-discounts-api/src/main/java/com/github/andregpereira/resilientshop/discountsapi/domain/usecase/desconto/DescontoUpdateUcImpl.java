package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DescontoUpdateUcImpl implements DescontoUpdateUc {

    private final DescontoGateway gateway;

    @Override
    public Desconto update(Long id, Desconto descontoAtualizado) {
        Desconto desconto = gateway.findById(id);
        desconto.setPercentual(descontoAtualizado.getPercentual());
        desconto.setTipoDesconto(descontoAtualizado.getTipoDesconto());
        desconto.setDataExpiracao(descontoAtualizado.getDataExpiracao());
        desconto.setAtivo(descontoAtualizado.isAtivo());
        desconto.setIdObjetoDoDesconto(descontoAtualizado.getIdObjetoDoDesconto());
        return gateway.save(desconto);
    }

}
