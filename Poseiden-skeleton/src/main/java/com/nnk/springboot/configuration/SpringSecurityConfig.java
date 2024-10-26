package com.nnk.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
/**
 * Provides the necessary configuration to enable user login securely
 *
 * @author Poseidon Capital Solutions
 *
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Builds the authentication manager with the registered users and the password encrypting method
     *
     * @param http web based security configuration
     * @param bCryptPasswordEncoder Encrypting password encoder
     * @throws Exception of every kind
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    /**
     * Sets up the required filters to restrain accessibility of endpoints to registered users.
     * Configures also the session parameters: only 1 session at a time, stores cookies only if needed
     *
     * @param http web based security configuration
     * @throws Exception of every kind
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/home.html").permitAll();
                    auth.requestMatchers("/user/*").hasRole("ADMIN");
                    auth.requestMatchers("/403").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .formLogin(form -> form
                        .defaultSuccessUrl("/loginsuccessful", true))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl("/403.html")
                )
                .build();
    }

    /**
     * Provides an encryption method for password
     *
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
