package com.github.andregpereira.resilientshop.apigateway.security.config;

import com.github.andregpereira.resilientshop.apigateway.security.role.RolePermissions;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RolePermissionConfig {

    private final Map<String, RolePermissions> rolePermissions = new HashMap<>();

    public RolePermissionConfig() {
        Map<HttpMethod, List<String>> adminPermissions = new HashMap<>();
        adminPermissions.put(HttpMethod.GET,
                Arrays.asList("/pedidos/**", "/produtos/**", "/subcategorias/**", "/categorias/**", "/usuarios/**",
                        "/enderecos/**", "/auth/**"));
        adminPermissions.put(HttpMethod.POST,
                Arrays.asList("/pedidos/**", "/produtos/**", "/subcategorias/**", "/categorias/**", "/usuarios/**",
                        "/enderecos/**", "/auth/**"));
        adminPermissions.put(HttpMethod.PUT,
                Arrays.asList("/pedidos/**", "/produtos/**", "/subcategorias/**", "/categorias/**", "/usuarios/**",
                        "/enderecos/**", "/auth/**"));
        adminPermissions.put(HttpMethod.DELETE,
                Arrays.asList("/pedidos/**", "/produtos/**", "/usuarios/**", "/auth/**"));
        adminPermissions.put(HttpMethod.PATCH, List.of("/usuarios/**"));
        rolePermissions.put("admin", new RolePermissions(adminPermissions));
        Map<HttpMethod, List<String>> userPermissions = new HashMap<>();
        userPermissions.put(HttpMethod.GET,
                Arrays.asList("/produtos/**", "/usuarios/{id}", "/enderecos/usuario/{idUsuario}",
                        "/enderecos/usuario/{idUsuario}/apelido", "/pedidos/**"));
        userPermissions.put(HttpMethod.POST, Arrays.asList("/usuarios/**", "/enderecos/**", "/pedidos/**"));
        userPermissions.put(HttpMethod.PUT, Arrays.asList("/usuarios/**", "/enderecos/**"));
        userPermissions.put(HttpMethod.PATCH, List.of("/usuarios/**"));
        rolePermissions.put("user", new RolePermissions(userPermissions));
        Map<HttpMethod, List<String>> anonymousPermissions = new HashMap<>();
        anonymousPermissions.put(HttpMethod.GET, List.of("/produtos/**"));
        anonymousPermissions.put(HttpMethod.POST, Arrays.asList("/usuarios/**", "/auth/**"));
        rolePermissions.put("anonymous", new RolePermissions(anonymousPermissions));
    }

    public RolePermissions getRolePermissions(String role) {
        return rolePermissions.getOrDefault(role, rolePermissions.get("anonymous"));
    }

}
