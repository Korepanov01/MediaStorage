package com.bftcom.mediastorage.config;

import com.bftcom.mediastorage.model.entity.Role;
import com.bftcom.mediastorage.security.AppUserDetailsService;
import com.bftcom.mediastorage.security.AuthEntryPointJwt;
import com.bftcom.mediastorage.security.AuthTokenFilter;
import com.bftcom.mediastorage.security.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(AppUserDetailsService userDetailsService, JwtUtils jwtUtils) {
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            AppUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            AuthEntryPointJwt unauthorizedHandler,
            DaoAuthenticationProvider authenticationProvider,
            AuthTokenFilter authenticationJwtTokenFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.cors(AbstractHttpConfigurer::disable);

        http.authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()

                .antMatchers(HttpMethod.PATCH, "/api/users/**").authenticated()

                .antMatchers("/api/users/**").hasRole(Role.ADMIN)

                // Все остальные ресурсы могут получать все
                .antMatchers(HttpMethod.GET).permitAll()

                // Управлять медиа может только владелец
                .antMatchers("/api/media/{id}/**").access("(@securityUtils.checkUserOwning(authentication, #id))")
                // Публиковать медиа могут авторизованные пользователи
                .antMatchers(HttpMethod.POST, "/api/media/").authenticated()

                // Управлять тегами, категориями и ролями может только админ
                .antMatchers(
                        "/api/tags/**",
                        "/api/category/**",
                        "/api/user_role/**").hasRole(Role.ADMIN);

        http.authenticationProvider(authenticationProvider);

        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}