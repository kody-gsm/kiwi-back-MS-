package com.example.kiwi.config;

import com.example.kiwi.domain.user.UserRole;
import com.example.kiwi.service.Authen.AuthenFailHandler;
import com.example.kiwi.service.Authen.AuthenSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenSuccessHandler authenSuccessHandler;
    private final AuthenFailHandler authenFailHandler;

    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Arrays.asList("HEAD","POST","GET","DELETE","PUT"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> {
                    cors.configurationSource(configurationSource());
                })
                .csrf(AbstractHttpConfigurer::disable)//csrf 공격 꺼두기
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/login","sign-up","PW-check","filter").permitAll();
                    authorize.requestMatchers("/admin/**").hasRole(UserRole.ADMIN.name());
                    authorize.anyRequest().authenticated();
                })
                .formLogin((formalin) -> {
                    formalin.usernameParameter("email");
                    formalin.passwordParameter("password");
                    formalin.successHandler(authenSuccessHandler);
                    formalin.failureHandler(authenFailHandler);
                })
                .logout((logout) -> {
                    logout.logoutSuccessUrl("/");
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                    logout.logoutUrl("/logout");
                })
                .rememberMe((remember) -> {
                    remember.rememberMeParameter("remember");
                    remember.alwaysRemember(true);
                })
                .sessionManagement((session) -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}