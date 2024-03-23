package com.basic.niroj.backend_social_media.Config;


import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

 import com.org.jwt.pkg.security.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@Component
public class Appconfig {

    @Autowired
 private  JwtAuthenticationEntryPoint point;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Disable session management for a stateless API
                .csrf(csrf -> csrf.disable())
                .httpBasic(httoBasic->httoBasic.disable())
                .authorizeHttpRequests(auth -> auth  // Configure authorization rules
                        .requestMatchers("/auth/**").permitAll() // Allow unauthenticated access to authentication endpoints
                        .requestMatchers("/api/**").authenticated() // Require authentication for API endpoints
                        .anyRequest().permitAll()) // Allow other requests (optional, adjust as needed)
                 .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .addFilterBefore(new JwtAuthenticationFilter(), BasicAuthenticationFilter.class); // Add JWT filter before BasicAuthenticationFilter

        return http.build();
    }



    // Separate configuration for "/auth/**" (example)



    @Bean
    PasswordEncoder passwordEncoder()
    {
  return new BCryptPasswordEncoder();
    }



}
