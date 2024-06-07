package com.github.andregpereira.resilientshop.authenticationapi.infra.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class JwtProviderImpl implements JwtProvider {

    private static final String SECRET_KEY = "Som35ecretK3y109jP2n8PaMS05mDKAPOjd23ur98yoej";

    @Override
    public String gerarToken(String email, List<? extends GrantedAuthority> roles) {
        Date inicio = Date.from(Instant.now(Clock.systemUTC()));
        return Jwts.builder()
                .subject(email)
                .claim("role", roles.get(0))
                .issuedAt(inicio)
                .expiration(new Date(inicio.getTime() + 3600000))
                .signWith(getSignKey())
                .compact();
    }

    @Override
    public void validarToken(String token) {
        Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

}
