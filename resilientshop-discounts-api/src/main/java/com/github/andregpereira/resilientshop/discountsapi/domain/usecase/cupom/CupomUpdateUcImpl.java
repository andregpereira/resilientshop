package com.github.andregpereira.resilientshop.discountsapi.domain.usecase.cupom;

import com.github.andregpereira.resilientshop.discountsapi.domain.gateway.CupomGateway;
import com.github.andregpereira.resilientshop.discountsapi.domain.model.Cupom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CupomUpdateUcImpl implements CupomUpdateUc {

    private final CupomGateway gateway;

    @Override
    public Cupom update(Long id, Cupom cupomAtualizado) {
        Cupom cupom = gateway.findById(id);
        cupom.setCodigo(cupom.getCodigo());
        cupom.setPercentual(cupom.getPercentual());
        cupom.setQtdMinimaProdutos(cupom.getQtdMinimaProdutos());
        cupom.setValorMinimoPedido(cupom.getValorMinimoPedido());
        cupom.setDescontoMaximo(cupom.getDescontoMaximo());
        cupom.setDataExpiracao(cupom.getDataExpiracao());
        cupom.setAtivo(cupom.isAtivo());
        return gateway.save(cupom);
    }

}
