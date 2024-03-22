package com.basic.niroj.backend_social_media.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Appconfig {

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests( Authorize->Authorize
                .requestMatchers("api/**").authenticated()
                .anyRequest().permitAll());
        return http.build();
    }
}
