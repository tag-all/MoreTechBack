package ru.tagallteam.hackstarter.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tagallteam.hackstarter.config.JwtConfig;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private final JwtConfig jwtConfig;

    public String generateToken(TokenType type, String login) {
        Date now = new Date();
        Date exp = type.equals(TokenType.AUTH)
                ? Date.from(LocalDateTime.now().plusHours(jwtConfig.getTimeOfActionAuthToken())
                .atZone(ZoneId.systemDefault()).toInstant())
                : Date.from(LocalDateTime.now().plusHours(jwtConfig.getTimeOfActionAccessToken())
                .atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getJwtSecret())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getJwtSecret())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getWordForToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtConfig.getJwtSecret())
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
