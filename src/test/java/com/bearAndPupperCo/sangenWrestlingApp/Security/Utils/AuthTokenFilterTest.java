package com.bearAndPupperCo.sangenWrestlingApp.Security.Utils;

import com.bearAndPupperCo.sangenWrestlingApp.Security.Entity.Role;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Service.UserDetailsImpl;
import com.bearAndPupperCo.sangenWrestlingApp.Security.Service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthTokenFilterTest {

    @InjectMocks
    AuthTokenFilter authTokenFilter;

    @Mock
    JwtUtils jwtUtils;

    @Mock
    UserDetailsServiceImpl userDetailsService;


    @Test
    void doFilterInternalSuccessfulTest() throws ServletException, IOException {
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain filterChain = mock(FilterChain.class);

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZXN0VXNlciIsInJvbGVzIjpbImNvbS5iZWFyQW5kUHVwcGVyQ28uc2FuZ2VuV3" +
                "Jlc3RsaW5nQXBwLlNlY3VyaXR5LkVudGl0eS5Sb2xlQDQyZTIyYTUzIiwiY29tLmJlYXJBbmRQdXBwZXJDby5zYW5nZW5XcmVzdGxpbmd" +
                "BcHAuU2VjdXJpdHkuRW50aXR5LlJvbGVANTdhZGZhYjAiXSwiZXhwIjoxNzEzNjY3NDQ5LCJpYXQiOjE3MTMzNTIwODl9.AZ8GmhlpFO" +
                "7DSmf5YTyDsY0YYd0LTxPEI0f35kw20PBEWHPxMyMqOEASa4S5R91L6DJtldhKhP3EuCCk6k6ySQ";

        Role roleAdmin = new Role(1L, "ADMIN");
        Role roleUser = new Role(2L, "USER");
        Set<Role> roles = new HashSet<>(Arrays.asList(roleUser, roleAdmin));
        Collection<String> roleNames = roles.stream()
                .map(Role::toString).toList();

        Collection<? extends GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(roleNames.toArray(new String[0]));

        UserDetails userDetails = new UserDetailsImpl("TestUser", authorities);


        when(jwtUtils.getJwtFromCookies(request)).thenReturn(token);
        when(jwtUtils.validateJwtToken(token)).thenReturn(true);
        when(jwtUtils.getUserNameFromJwtToken(token)).thenReturn("TestUser");
        when(userDetailsService.loadUserByUsername("TestUser")).thenReturn(userDetails);

        //Act
        authTokenFilter.doFilterInternal(request,response,filterChain);

        //Assert
        verify(filterChain, times(1)).doFilter(request, response);

    }
}