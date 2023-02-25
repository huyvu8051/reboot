package io.huyvu.reboot.backend.config.auth;

import io.huyvu.reboot.backend.config.SpaRedirectFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter, SpaRedirectFilter spaRedirectFilter) throws Exception {

        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/csrf", "/api/authenticate", "/api/test/**", "/api/data", "/h2-console/**",
                        "/v2/api-docs", "/webjars/**", "/resources/**", "/socket.io/**","/ws**", "/static/**", "/assets/**", "/client.html**", "/client2.html**", "/uploads/**")
                .permitAll()
                .antMatchers("/api/private").authenticated()
                .antMatchers("/api/v1/workspace").hasAuthority("ADMIN")
                .antMatchers("/api/v1/google-auth").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(spaRedirectFilter, jwtFilter.getClass());

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}