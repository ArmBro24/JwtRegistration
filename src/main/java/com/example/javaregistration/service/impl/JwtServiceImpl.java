package com.example.javaregistration.service.impl;

import com.example.javaregistration.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    @Override
    public String extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("userId", String.class);
    }

    @Override
    public String generateToken(Long userDetails) {
        return generateToken(new HashMap<>(), String.valueOf(userDetails));
    }

    private String generateToken(Map<String, Object> extraClaims, String userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningkey(), SignatureAlgorithm.HS256).compact();
    }

    @Override
    public boolean isTokenValid(String token) {
        final String userName = extractUserName(token);
        return (userName.equals(!isTokenExpired(token)));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningkey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningkey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
