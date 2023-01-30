package org.bedu.proyectofinalmodulo3.Config.Security;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthfilter;
    private final AuthenticationProvider authenticacionProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception{

        JwtAuthFilter jwtAuthFilter2 = new JwtAuthFilter();
        jwtAuthFilter2.setAuthenticationManager(authManager);
        jwtAuthFilter2.setFilterProcessesUrl("/login");

        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/funkos/**")
                .hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/usuarios/**")
                .hasAuthority("ADMIN")
                .requestMatchers("/auth/**")
                .permitAll()
                .requestMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //.authenticationProvider(authenticacionProvider)
                .addFilter(jwtAuthFilter2)
                .addFilterBefore(jwtAuthfilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
