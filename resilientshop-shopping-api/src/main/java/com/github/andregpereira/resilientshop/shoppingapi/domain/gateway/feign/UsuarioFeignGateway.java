package com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.feign;

import com.github.andregpereira.resilientshop.commons.entities.Endereco;
import com.github.andregpereira.resilientshop.commons.entities.Usuario;

public interface UsuarioFeignGateway {

    Usuario findUsuarioById(Long id);

    Endereco findEnderecoById(Long id);

}
