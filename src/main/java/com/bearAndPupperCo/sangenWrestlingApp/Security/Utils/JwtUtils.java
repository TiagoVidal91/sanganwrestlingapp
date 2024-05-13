package com.bearAndPupperCo.sangenWrestlingApp.Security.Utils;

import com.bearAndPupperCo.sangenWrestlingApp.Exception.JwtCookieNotFoundException;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bearAndPupperCo.sangenWrestlingApp.APIUtils.MessageConstants.JWT_COOKIE_NOT_FOUND_MSG;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${sanganwrestling.app.jwtSecret}")
    private String jwtSecret;

    @Value("${sanganwrestling.app.jwtExpirationMs}")
    private Long jwtExpirationMs;

    @Value("${sanganwrestling.app.jwtCookieName}")
    private String jwtCookie;

    @Value("${sanganwrestling.app.jwtAuthorityKeys}")
    private String authorityKeys;

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(jwtCookie)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
        String jwt = generateTokenFromUser(userPrincipal);
        return ResponseCookie
                .from(jwtCookie, jwt)
                .path("/teiai-api").maxAge(24 * 60 * 60)
                .secure(false)
                .httpOnly(true).build();
    }

    public ResponseCookie getCleanJwtCookie() {
        return ResponseCookie
                .from(jwtCookie, null)
                .path("/teiai-api").maxAge(0)
                .secure(false)
                .httpOnly(true).build();
    }

    public String getUserNameFromJwtToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
            throw new SignatureException(e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            throw new MalformedJwtException(e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            throw new UnsupportedJwtException(e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String generateTokenFromUser(UserDetailsImpl userDetails) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

        Set<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim(authorityKeys, authorities)
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .issuedAt(new Date())
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }

}
