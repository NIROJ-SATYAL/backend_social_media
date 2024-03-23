package com.basic.niroj.backend_social_media.Config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwttoken = request.getHeader(JwtConstant.Jwt_Header);


        if (jwttoken == null || !jwttoken.startsWith("Bearer")) {


            try {
                String email = JwtProvider.getEmailFromToken(jwttoken);

                List<GrantedAuthority> authorities = new ArrayList<>();
                Authentication auth = new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);


            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
            }


        }
        else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "please provide valid token");
        }
        filterChain.doFilter(request, response);
    }
}
