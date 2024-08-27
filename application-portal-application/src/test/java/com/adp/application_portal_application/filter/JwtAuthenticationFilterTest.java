package com.adp.application_portal_application.filter;

import com.adp.application_portal_application.config.JwtAuthenticationFilter;
import com.adp.application_portal_application.models.User;
import com.adp.application_portal_application.service.ApplicationUserDetailsService;
import com.adp.application_portal_application.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private ApplicationUserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testDoFilterInternalValidToken() throws ServletException, IOException {
        String token = "validToken";
        String userId = "user123";
        User user = mock(User.class);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtService.extractUsername(token)).thenReturn(userId);
        when(userDetailsService.loadUserByUserId(userId)).thenReturn(user);
        when(jwtService.isTokenValid(token, user)).thenReturn(true);
        when(user.getAuthorities()).thenReturn(null);

        jwtAuthenticationFilter.doFilter(request, response, filterChain);

        verify(jwtService, times(1)).extractUsername(token);
        verify(userDetailsService, times(1)).loadUserByUserId(userId);
        verify(jwtService, times(1)).isTokenValid(token, user);
        verify(filterChain, times(1)).doFilter(request, response);

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        assert authentication.getPrincipal().equals(user);
    }

    @Test
    public void testDoFilterInternalInvalidToken() throws ServletException, IOException {
        String token = "invalidToken";
        String userId = "user123";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtService.extractUsername(token)).thenReturn(userId);
        when(userDetailsService.loadUserByUserId(userId)).thenReturn(null);
        when(jwtService.isTokenValid(token, null)).thenReturn(false);

        jwtAuthenticationFilter.doFilter(request, response, filterChain);

        verify(jwtService, times(1)).extractUsername(token);
        verify(userDetailsService, times(1)).loadUserByUserId(userId);
        verify(jwtService, times(1)).isTokenValid(token, null);
        verify(filterChain, times(1)).doFilter(request, response);

        assert SecurityContextHolder.getContext().getAuthentication() == null;
    }

    @Test
    public void testDoFilterInternalNoAuthorizationHeader() throws ServletException, IOException {
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthenticationFilter.doFilter(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);

        assert SecurityContextHolder.getContext().getAuthentication() == null;
    }

    @Test
    public void testDoFilterInternalAlreadyAuthenticated() throws ServletException, IOException {
        String token = "validToken";

        UsernamePasswordAuthenticationToken existingAuth = new UsernamePasswordAuthenticationToken("user", null, null);
        SecurityContextHolder.getContext().setAuthentication(existingAuth);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        jwtAuthenticationFilter.doFilter(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);

        assertEquals(existingAuth, SecurityContextHolder.getContext().getAuthentication());
    }
}
