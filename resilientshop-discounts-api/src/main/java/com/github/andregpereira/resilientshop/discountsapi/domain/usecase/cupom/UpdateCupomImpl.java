package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateCupomImpl implements UpdateCupom {

    private final CupomGateway gateway;

    @Override
    public Cupom update(Long id, Cupom cupom) {
        return gateway.findById(id).map(c -> {
            c.setCodigo(cupom.getCodigo());
            c.setPercentual(cupom.getPercentual());
            c.setQtdMinimaProdutos(cupom.getQtdMinimaProdutos());
            c.setValorMinimoPedido(cupom.getValorMinimoPedido());
            c.setDescontoMaximo(cupom.getDescontoMaximo());
            c.setDataExpiracao(cupom.getDataExpiracao());
            c.setAtivo(cupom.isAtivo());
            return gateway.save(c);
        });
    }

}
