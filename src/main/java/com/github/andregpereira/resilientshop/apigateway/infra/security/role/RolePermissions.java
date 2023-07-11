package com.github.andregpereira.resilientshop.apigateway.infra.security.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class RolePermissions {

    private final Map<HttpMethod, List<String>> permissions;

    public List<String> getPermissions(HttpMethod method) {
        return permissions.getOrDefault(method, Collections.emptyList());
    }

}
