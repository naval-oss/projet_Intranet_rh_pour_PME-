package com.example.navany.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JwtTokenUtil {

    private static final Logger logger = Logger.getLogger(JwtTokenUtil.class.getName());

    @Value("${jwt.secret}")
    private String secretBase64;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    private Key signingKey;

    /**
     * Méthode exécutée après injection des propriétés pour initialiser la clé.
     */
    @PostConstruct
    public void init() {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(secretBase64);
            signingKey = Keys.hmacShaKeyFor(keyBytes);
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "Clé JWT invalide (décodage base64 échoué)", e);
            throw new RuntimeException("Erreur lors de l'initialisation de la clé JWT", e);
        }
    }

    /**
     * Génère un token JWT avec username, rôles, date émission et expiration.
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Récupère le username depuis un token JWT.
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Vérifie si le token est valide par rapport à l'utilisateur.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = getUsernameFromToken(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (JwtException | IllegalArgumentException e) {
            logger.log(Level.WARNING, "Token JWT invalide ou expiré", e);
            return false;
        }
    }

    /**
     * Vérifie si un token JWT est expiré.
     */
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
