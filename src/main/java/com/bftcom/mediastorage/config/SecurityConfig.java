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
                .antMatchers(HttpMethod.GET,
                        "/api/tags/**",
                        "/api/media/**",
                        "/api/category/**",
                        "/api/files/**",
                        "/api/media_type/**",
                        "/api/media_file/**",
                        "/api/file_types/**").permitAll()
                .antMatchers(HttpMethod.GET,
                        "/api/users/**",
                        "/api/roles/**").hasRole(Role.ADMIN)

                .antMatchers(HttpMethod.POST,
                        "/api/auth/**").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/api/media/**",
                        "/api/media_tag/**",
                        "/api/files/**").authenticated()
                .antMatchers(HttpMethod.POST,
                        "/api/tags/**",
                        "/api/category/**").hasRole(Role.ADMIN)
                .antMatchers(HttpMethod.POST,
                        "/api/user_role/**").hasRole(Role.SUPER_ADMIN)

                .antMatchers(HttpMethod.PUT,
                        "/api/media/{id}").access("(@securityUtils.checkUserOwning(authentication, #id)) || hasRole(\"ADMIN\")")
                .antMatchers(HttpMethod.PUT,
                        "/api/tags/**",
                        "/api/category/**").hasRole(Role.ADMIN)

                .antMatchers(HttpMethod.DELETE,
                        "/api/media_tag/**",
                        "/api/files/**").authenticated()
                .antMatchers(HttpMethod.DELETE,
                        "/api/media/{id}").access("(@securityUtils.checkUserOwning(authentication, #id)) || hasRole(\"ADMIN\")")
                .antMatchers(HttpMethod.DELETE,
                        "/api/tags/**",
                        "/api/category/**",
                        "/api/users/**").hasRole(Role.ADMIN);

        http.authenticationProvider(authenticationProvider);

        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}