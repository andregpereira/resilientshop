package com.github.andregpereira.resilientshop.authenticationapi.infra.security.jwt;

import com.github.andregpereira.resilientshop.authenticationapi.domain.entity.UsuarioCredential;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtProviderImpl implements JwtProvider {

    private static final String SECRET = "Som35ecretK3y109jP2n8PaMS05mDKAPOjd23ur98yoej";

    @Override
    public String gerarToken(UsuarioCredential user) {
        Date inicio = Date.from(Instant.now(Clock.systemUTC()));
        return Jwts.builder().setSubject(user.getEmail()).claim("role", user.getRole()).setIssuedAt(
                inicio).setExpiration(new Date(inicio.getTime() + 3600000)).signWith(getSignKey(),
                SignatureAlgorithm.HS256).compact();
    }

    @Override
    public void validarToken(String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
