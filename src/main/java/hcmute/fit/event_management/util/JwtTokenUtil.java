package hcmute.fit.event_management.util;

import hcmute.fit.event_management.dto.AccountDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil {
    @Value("${jwt.privateKey}")
    private String privateKey;
    @Value("${jwt.expirationMs}")
    private Long expirationMs;
    @Value("${jwt.expirationLoginMs}")
    private Long expirationLoginMs;

    public String generateToken(Authentication authentication, List<String> roles){
        AccountDetail accountPrincipal = (AccountDetail) authentication.getPrincipal();
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationLoginMs);
        return Jwts.builder()
                .subject(accountPrincipal.getUsername())
                .issuedAt(now)
                .expiration(expiration)
                .claim("roles", roles)
                .signWith(key)
                .compact();
    }
    public String generateResetToken(String email){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }
    public String getEmailFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }
    public boolean validateToken(String token){
        try{
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<String> getRolesFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return claims.get("roles", List.class); // Lấy danh sách roles từ "roles" claim
    }
}
