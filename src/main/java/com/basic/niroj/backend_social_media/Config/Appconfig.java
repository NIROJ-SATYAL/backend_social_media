package com.basic.niroj.backend_social_media.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

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
                .csrf(csrf->csrf.disable());
        return http.build();
    }
}
