package com.github.andregpereira.resilientshop.commons.security.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {
    ADMIN(Map.of(HttpMethod.GET, Set.of("/**"), HttpMethod.POST, Set.of("/**"), HttpMethod.PUT, Set.of("/**"),
            HttpMethod.DELETE, Set.of("/**"), HttpMethod.PATCH, Set.of("/**"))),
    USER(Map.of(HttpMethod.GET, Set.of("/usuarios/{id}", "/produtos/**", "/enderecos/usuario/{idUsuario}",
                    "/enderecos/usuario/{idUsuario}/apelido", "/pedidos/**"), HttpMethod.POST,
            Set.of("/enderecos/**", "/pedidos/**"), HttpMethod.PUT, Set.of("/usuarios/**", "/enderecos/**"),
            HttpMethod.PATCH, Set.of("/usuarios/**", "/auth/**"), HttpMethod.DELETE,
            Set.of("/usuarios/{id}", "/enderecos/{id}", "/pedidos/**"))),
    ANONYMOUS(Map.of(HttpMethod.GET, Set.of("/produtos/**"), HttpMethod.POST, Set.of("/usuarios/**", "/auth/**")));
    private final Map<HttpMethod, Set<String>> permissions;

    public static Role getRole(String nome) {
        return Stream.of(values()).filter(r -> r.name().equalsIgnoreCase(nome)).findFirst().orElseGet(() -> ANONYMOUS);
    }

    public Set<String> getPermissions(HttpMethod method) {
        return permissions.getOrDefault(method, Collections.emptySet());
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
