package com.github.andregpereira.resilientshop.shoppingapi.infra.dataprovider;

import com.github.andregpereira.resilientshop.commons.entities.Endereco;
import com.github.andregpereira.resilientshop.commons.entities.Usuario;
import com.github.andregpereira.resilientshop.shoppingapi.domain.gateway.feign.UsuarioFeignGateway;
import com.github.andregpereira.resilientshop.shoppingapi.infra.feignclient.UsuariosFeignClient;
import com.github.andregpereira.resilientshop.shoppingapi.infra.mapper.UsuarioDataProviderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UsuarioDataProvider implements UsuarioFeignGateway {

    /**
     * Injeção da dependência {@link UsuariosFeignClient} para realizar
     * requisições na API de Usuários.
     */
    private final UsuariosFeignClient usuariosFeignClient;
    private final UsuarioDataProviderMapper mapper;

    @Override
    public Usuario findUsuarioById(Long id) {
        return mapper.toUsuario(usuariosFeignClient.findUsuarioById(id));
    }

    @Override
    public Endereco findEnderecoById(Long id) {
        return usuariosFeignClient.findEnderecoById(id);
    }

}
