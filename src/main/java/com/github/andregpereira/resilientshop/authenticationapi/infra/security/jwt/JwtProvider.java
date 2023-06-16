package com.github.andregpereira.resilientshop.authenticationapi.infra.security.jwt;

import com.github.andregpereira.resilientshop.authenticationapi.domain.entity.UsuarioCredential;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtProvider {

    private static final String SECRET = "Som35ecretK3y109jP2n8PaMS05mDKAPOjd23ur98yoej";
    private int jwtExpirationInMs = 3600000;

    public void validateToken(String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    public String generateToken(UsuarioCredential user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
//        return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(
//                new Date(System.currentTimeMillis())).setExpiration(
//                new Date(System.currentTimeMillis() + 1000 * 60 * 30)).signWith(getSignKey(),
//                SignatureAlgorithm.HS256).compact();
        return Jwts.builder().setSubject(user.getEmail()).claim("role", user.getRole()).setIssuedAt(now).setExpiration(
                expiryDate).signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
