package com.basic.niroj.backend_social_media.Config;


import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Appconfig {



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authorizeHttpRequests( Authorize->Authorize
                        .requestMatchers("/api/v1/adduser").permitAll()
                .requestMatchers("api/**").authenticated()
                .anyRequest().permitAll())
                .addFilterBefore( new JwtAuthenticationFilter(), BasicAuthenticationFilter.class)
                .csrf(csrf->csrf.disable());
        return http.build();
    }



}
