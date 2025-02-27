package fyuizee.com.ankizletbe.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String jwtSecret = generateSecretKey();

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() * 60 * 60 * 1000)) // 1 hour
                .and()
                .signWith(key())
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateSecretKey() {
        int length = 32;

        SecureRandom secureRandom = new SecureRandom();

        byte[] keyBytes = new byte[length];

        secureRandom.nextBytes(keyBytes);

        return Base64.getEncoder().encodeToString(keyBytes);
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isValidToken(String token, UserDetails expectedUser) {
        try {
            Claims claims = (Claims) Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token)
                    .getPayload();

            String username = claims.getSubject();
            if (!username.equals(expectedUser.getUsername())) {
                // todo: .
                return false;
            }

            Date expiration = claims.getExpiration();
            if (expiration.before(new Date(System.currentTimeMillis()))) {
                return false;
            }

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
