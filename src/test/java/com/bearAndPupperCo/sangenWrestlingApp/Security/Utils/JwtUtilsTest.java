package com.bearAndPupperCo.sangenWrestlingApp.Security.Utils;

import com.bearAndPupperCo.sangenWrestlingApp.Exception.JwtCookieNotFoundException;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Entity.Role;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseCookie;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(SpringExtension.class)
class JwtUtilsTest {

    @InjectMocks
    JwtUtils jwtUtils;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(jwtUtils, "jwtCookie", "access_token");
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret", "bL7d5NkW3f7m+7T0W8p2cL9v0fapdsX/IOdMfKY8QibB2a3qkwM+k0+dQvK+bw2ZWzFU/fOxQsXQ2K07eh1cZg==");
        ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 315360000L);
        ReflectionTestUtils.setField(jwtUtils, "authorityKeys", "roles");
    }

    @Test
    void getJwtFromCookiesSuccessfullyTest() {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();

        Cookie cookie = new Cookie("access_token","JWT_Token");
        request.setCookies(cookie);

        //Act
        String cookieValueReturned = jwtUtils.getJwtFromCookies(request);

        //Assert
        assertEquals(cookieValueReturned, "JWT_Token");
    }

    @Test
    public void testGetJwtFromCookiesReturnsNullTest() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();

        //Act
        String cookieValueReturned = jwtUtils.getJwtFromCookies(request);

        // Act and Assert
        assertNull(cookieValueReturned);
    }

    @Test
    void generateJwtCookieTest() {
        //Arrange
        Role roleAdmin = new Role(1L, "ADMIN");
        Role roleUser = new Role(2L, "USER");
        Set<Role> roles = new HashSet<>(Arrays.asList(roleUser, roleAdmin));
        Collection<String> roleNames = roles.stream()
                .map(Role::toString).toList();

        Collection<? extends GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(roleNames.toArray(new String[0]));

        UserDetailsImpl userDetails = new UserDetailsImpl("TestUser",authorities);

        //Act
        ResponseCookie cookieReturned = jwtUtils.generateJwtCookie(userDetails);

        assertTrue(cookieReturned.isHttpOnly());
        assertEquals("access_token", cookieReturned.getName());
        assertEquals("access_token", cookieReturned.getName());
        assertEquals("/teiai-api", cookieReturned.getPath());
        assertEquals(24 * 60 * 60, cookieReturned.getMaxAge().getSeconds());

    }

    @Test
    void getCleanJwtCookieTest() {

        //Assert
        ResponseCookie responseCookie = ResponseCookie.from("access_token", null)
                .path("/teiai-api").maxAge(0)
                .secure(false)
                .httpOnly(true).build();

        //Act
        ResponseCookie cookieReturned = jwtUtils.getCleanJwtCookie();


        assertTrue(cookieReturned.isHttpOnly());
        assertEquals("access_token", cookieReturned.getName());
        assertEquals("", cookieReturned.getValue());
        assertEquals("/teiai-api", cookieReturned.getPath());
        assertEquals(0, cookieReturned.getMaxAge().getSeconds());

    }

    @Test
    void getUserNameFromJwtTokenTest() {
        //Assert
        Role roleAdmin = new Role(1L, "ADMIN");
        Role roleUser = new Role(2L, "USER");
        Set<Role> roles = new HashSet<>(Arrays.asList(roleUser, roleAdmin));
        Collection<String> roleNames = roles.stream()
                .map(Role::toString).toList();

        Collection<? extends GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(roleNames.toArray(new String[0]));

        UserDetailsImpl userDetails = new UserDetailsImpl("TestUser",authorities);

        String token = jwtUtils.generateTokenFromUser(userDetails);

        //Act
        String username = jwtUtils.getUserNameFromJwtToken(token);

        //Assert
        assertEquals("TestUser", username);

    }

    @Test
    void validateJwtTokenTest() {
        //Assert
        Role roleAdmin = new Role(1L, "ADMIN");
        Role roleUser = new Role(2L, "USER");
        Set<Role> roles = new HashSet<>(Arrays.asList(roleUser, roleAdmin));
        Collection<String> roleNames = roles.stream()
                .map(Role::toString).toList();

        Collection<? extends GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(roleNames.toArray(new String[0]));

        UserDetailsImpl userDetails = new UserDetailsImpl("TestUser",authorities);

        String token = jwtUtils.generateTokenFromUser(userDetails);

        //Act
        boolean isValidToken = jwtUtils.validateJwtToken(token);

        //Assert
        assertTrue(isValidToken);
    }

    @Test
    void catchSignatureExceptionJwtTokenTest() {
        //Assert
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0VXNlciIsInJvbGVzIjpbImNvbS5iZWFyQW5kUHVwcGVyQ28uc2FuZ2VuV3" +
                "Jlc3RsaW5nQXBwLlNlY3VyaXR5LkVudGl0eS5Sb2xlQDQyZTIyYTUzIiwiY29tLmJlYXJBbmRQdXBwZXJDby5zYW5nZW5XcmVzdGxpbmd" +
                "BcHAuU2VjdXJpdHkuRW50aXR5LlJvbGVANTdhZGZhYjAiXSwiZXhwIjoxNzEzNjY3NDQ5LCJpYXQiOjE3MTMzNTIwODl9.AZ8GmhlpFO" +
                "7DSmf5YTyDsY0YYd0LTxPEI0f35kw20PBEWHPxMyMqOEASa4S5R91L6DJtld";

        //Act & Assert
        assertThrows(SignatureException.class, ()-> jwtUtils.validateJwtToken(token));
    }

    @Test
    void catchMalformedJwtExceptionJwtTokenTest() {
        //Assert
        String token = "Malformed_Test";

        //Act & Assert
        assertThrows(MalformedJwtException.class, ()-> jwtUtils.validateJwtToken(token));
    }

    @Test
    void catchIllegalArgumentExceptionJwtTokenTest() {
        //Assert
        String token = "";

        //Act & Assert
        assertThrows(IllegalArgumentException.class, ()-> jwtUtils.validateJwtToken(token));
    }

    @Test
    void catchUnsupportedJwtExceptionJwtTokenTest() {
        //Assert
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0Ij" +
                "oxNTE2MjM5MDIyfQ.InvalidSignature";

        //Act & Assert
        assertThrows(UnsupportedJwtException.class, ()-> jwtUtils.validateJwtToken(token));
    }

    @Test
    void catchExpiredJwtExceptionJwtTokenTest() {
        //Assert
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode("bL7d5NkW3f7m+7T0W8p2cL9v0fapdsX/IOdMfKY8QibB2" +
                "a3qkwM+k0+dQvK+bw2ZWzFU/fOxQsXQ2K07eh1cZg=="));

        String token = Jwts.builder()
                .subject("TestUser")
                .expiration(new Date())
                .issuedAt(new Date())
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();

        //Act & Assert
        assertThrows(RuntimeException.class, ()-> jwtUtils.validateJwtToken(token));
    }

    @Test
    void generateTokenFromUser() {
        //Arrange
        Role roleAdmin = new Role(1L, "ADMIN");
        Role roleUser = new Role(2L, "USER");
        Set<Role> roles = new HashSet<>(Arrays.asList(roleUser, roleAdmin));
        Collection<String> roleNames = roles.stream()
                .map(Role::toString).toList();

        Collection<? extends GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(roleNames.toArray(new String[0]));

        UserDetailsImpl userDetails = new UserDetailsImpl("TestUser",authorities);

        String tokenKey = "bL7d5NkW3f7m+7T0W8p2cL9v0fapdsX/IOdMfKY8QibB2a3qkwM+k0+dQvK+bw2ZWzFU/fOxQsXQ2K07eh1cZg==";

        String tokenReturned = jwtUtils.generateTokenFromUser(userDetails);


        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenKey));
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(tokenReturned);


        assertNotNull(tokenReturned);
        assertEquals("TestUser", claimsJws.getPayload().getSubject());
        assertEquals(Jwts.SIG.HS512.getId(), claimsJws.getHeader().getAlgorithm());

    }
}