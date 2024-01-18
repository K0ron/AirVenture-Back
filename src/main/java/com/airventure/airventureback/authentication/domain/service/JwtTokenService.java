package com.airventure.airventureback.authentication.domain.service;

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
import java.util.function.Function;

@Service
public class JwtTokenService {
        private String secretKey = System.getenv("JWT_KEY");
        private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
        public String generateToken(UserDetails userDetails) {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + JWT_TOKEN_VALIDITY * 1000);
            return Jwts.
                    builder()
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(expiryDate)
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact()
                    ;
        }

        private Key getSignInKey() {
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            return Keys.hmacShaKeyFor(keyBytes);
        }

        public String getEmailFromToken(String token) {
            return getClaimFromToken(token, Claims::getSubject);
        }

        public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        }

        private Claims getAllClaimsFromToken(String token) {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    ;
        }

        private Date extractExpiration(String token) {
            return getClaimFromToken(token, Claims::getExpiration);
        }

        private Boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        public boolean isTokenValid(String token, UserDetails userDetails) {
            final String email = getEmailFromToken(token);
            return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }
}
