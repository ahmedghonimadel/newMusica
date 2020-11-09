package com.ahmed.music.musica.security;

import com.ahmed.music.musica.entity.User;
import com.ahmed.music.musica.service.UserService;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.ahmed.music.musica.security.SecurityConstants.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

/**
 * @author Ghonim
 */
/*
 * responsible for the authentication process
 * The first filter will be used directly for User authentication.
 * It’ll check for Username and password parameters from URL and calls Spring’s authentication manager to verify them
 *
 *If Username and password are correct, then the filter will create a JWT token and returns it in HTTP Authorization header
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    com.ahmed.music.musica.entity.User creds;
    UserService userService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;

        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            creds = new ObjectMapper()
                    .readValue(req.getInputStream(), com.ahmed.music.musica.entity.User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

        User user = userService.findByEmail(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write("{\"");
        res.getWriter().write("username" + "\":\"" + user.getUsername() + "\",");
        res.getWriter().write("\"" + "email" + "\":\"" + user.getEmail() + "\",");
        res.getWriter().write("\"" + "createdDate" + "\":\"" + user.getCreatedDate() + "\",");
        res.getWriter().write("\"" + "active" + "\":\"" + user.isActive() + "\",");
        res.getWriter().write("\"" + "id" + "\":\"" + user.getId());
        res.getWriter().write("\"}");
    }
}