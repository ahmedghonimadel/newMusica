package com.ahmed.music.musica.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.ahmed.music.musica.security.SecurityConstants.*;

/**
 * @author Ghonim
 **/
/*
 * responsible for applicationUser authorization
 * filter handles all HTTP requests and checks if there is an Authorization header with the correct token.
 * For example, if the token is not expired or if the signature key is correct
 * If the token is valid then the filter will add authentication data into Spring’s security context.
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }


    /*
     * private getAuthentication method. This method reads the JWT from the Authorization header,
     * and then uses JWT to validate the token. If everything is in place, we set the applicationUser in the SecurityContext and allow the request to move on
     *
     *
     * */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String applicationUser = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            if (applicationUser != null) {
                return new UsernamePasswordAuthenticationToken(applicationUser, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
