package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.desconto;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.DescontoGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Desconto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateDescontoImpl implements UpdateDesconto {

    private final DescontoGateway gateway;

    @Override
    public Desconto update(Long id, Desconto desconto) {
        return gateway.findById(id).map(d -> {
            d.setPercentual(desconto.getPercentual());
            d.setTipoDesconto(desconto.getTipoDesconto());
            d.setDataExpiracao(desconto.getDataExpiracao());
            d.setAtivo(desconto.isAtivo());
            d.setIdObjetoDoDesconto(desconto.getIdObjetoDoDesconto());
            return gateway.save(d);
        });
    }

}
