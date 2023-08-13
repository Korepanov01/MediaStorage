package com.bftcom.mediastorage.config;

import com.bftcom.mediastorage.enums.Role;
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

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/tags/**").permitAll()
                .antMatchers("/api/tags/**").hasRole(Role.ADMIN)

                .antMatchers(HttpMethod.GET, "/api/roles/**").permitAll()
                .antMatchers("/api/roles/**").hasRole(Role.ADMIN)

                .antMatchers(HttpMethod.GET, "/api/media_type/**").permitAll()
                .antMatchers("/api/media_type/**").hasRole(Role.ADMIN)

                .antMatchers(HttpMethod.GET, "/api/media/**").permitAll()
                .antMatchers( "/api/media/{id}").access(String.format("(@securityUtils.checkUserId(authentication, #id)) || hasRole(%s)", Role.ADMIN))
                .antMatchers(HttpMethod.POST, "/api/media/").authenticated()

                .antMatchers(HttpMethod.GET, "/api/file_types/**").permitAll()
                .antMatchers("/api/file_types/**").hasRole(Role.ADMIN)

                .antMatchers(HttpMethod.GET, "/api/category/**").permitAll()
                .antMatchers("/api/category/**").hasRole(Role.ADMIN)

                .antMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/api/users/**}").authenticated()
                .antMatchers(HttpMethod.GET, "/api/users/**").hasRole(Role.ADMIN)
                .antMatchers(HttpMethod.DELETE, "/api/users/{id}").access("(@securityUtils.checkUserId(authentication, #id)) || hasRole(\"ADMIN\")")


                .antMatchers("/api/user_role/**").hasRole(Role.SUPER_ADMIN)

                .antMatchers("/api/media_tag").authenticated()

                .antMatchers(HttpMethod.GET, "/api/files/**").permitAll()
                .antMatchers("/api/files/**").authenticated()

                .antMatchers("/api/auth/login").permitAll()

                .antMatchers(HttpMethod.GET, "/api/media_file/**").permitAll();

        http.authenticationProvider(authenticationProvider);

        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}