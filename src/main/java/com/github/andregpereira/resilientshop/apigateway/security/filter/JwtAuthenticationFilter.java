package com.github.andregpereira.resilientshop.apigateway.security.filter;

import com.github.andregpereira.resilientshop.commons.security.role.Role;
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

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION)).filter(
                authHeader -> authHeader.startsWith("Bearer ")).map(
                authHeader -> authHeader.replace("Bearer ", "")).map(this::getRoleFromToken).defaultIfEmpty(
                Role.ANONYMOUS).filter(role -> hasPermission(role, exchange.getRequest().getURI().getPath(),
                exchange.getRequest().getMethod())).flatMap(role -> chain.filter(exchange)).switchIfEmpty(
                Mono.defer(() -> onError(exchange, "Acesso negado", HttpStatus.UNAUTHORIZED)));
    }

    @Override
    public int getOrder() {
        return -100;
    }

    private boolean hasPermission(Role role, String path, HttpMethod method) {
        return role.getPermissions(method).stream().anyMatch(
                permittedPath -> new AntPathMatcher().match(permittedPath, path));
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus) {
        log.error(error);
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private Role getRoleFromToken(String token) {
        try {
            return Role.valueOf(Jwts.parserBuilder().setSigningKey(
                    Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY))).build().parseClaimsJws(token).getBody().get(
                    "role", String.class));
        } catch (JwtException e) {
            log.warn(e.getMessage());
            return Role.ANONYMOUS;
        }
    }

}
