package com.github.andregpereira.resilientshop.apigateway.security.filter;

import com.github.andregpereira.resilientshop.apigateway.security.config.RolePermissionConfig;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private static final String SECRET_KEY = "Som35ecretK3y109jP2n8PaMS05mDKAPOjd23ur98yoej";
    private final RolePermissionConfig rolePermissionConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        HttpMethod method = exchange.getRequest().getMethod();
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String userRole;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwtToken = authHeader.replace("Bearer ", "");
            userRole = this.getRoleFromToken(jwtToken);
        } else {
            userRole = "anonymous";
        }
        if (hasPermission(userRole, path, method)) {
            return chain.filter(exchange);
        }
        return onError(exchange, "Access Denied", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public int getOrder() {
        return -100;
    }

    private boolean hasPermission(String role, String path, HttpMethod method) {
        return rolePermissionConfig.getRolePermissions(role).getPermissions(method).stream().anyMatch(
                permittedPath -> new AntPathMatcher().match(permittedPath, path));
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus) {
        log.error(error);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getRoleFromToken(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(
                    Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY))).build().parseClaimsJws(token).getBody().get(
                    "role", String.class);
        } catch (JwtException e) {
            log.warn(e.getMessage());
            return "anonymous";
        }
    }

}
