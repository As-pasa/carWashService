package com.asPasa.testTask.services;

import com.asPasa.testTask.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private String jwtSigningKey = "secretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecret";

    private String generateToken(Map<String, Object> extraClaims, UserDetails details) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(details.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + Duration.ofDays(1).toMillis()))
                .signWith(getSigningKey())
                .compact();
    }
    public String generateToken(UserDetails details) {
        Map<String, Object> claims = new HashMap<>();
        if (details instanceof User user) {
            claims.put("id", user.getId());
            claims.put("email", user.getEmail());
            claims.put("role", user.getRole());
        }
        return generateToken(claims, details);

    }
    public boolean isTokenValid(String token, UserDetails details){
        return extractName(token).equals(details.getUsername()) && !isExpired(token);
    }
    public boolean isExpired(String token){
        return extractExpirationDate(token).before(new Date());
    }
    public String extractName(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public Date extractExpirationDate(String token){return extractClaim(token,Claims::getExpiration);}
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private <T> T extractClaim(String token, Function<Claims, T> extractor) {
        Claims claims = extractAllClaims(token);
        return extractor.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
