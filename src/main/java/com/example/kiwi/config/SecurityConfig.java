package com.example.kiwi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enable from user where username=?")
                .authoritiesByUsernameQuery("select username,authority from user where username=?")
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)//csrf 공격 꺼두기
                .authorizeHttpRequests((authorize) -> {
//                    authorize.requestMatchers("/login","sign-up").permitALL();
//                    authorize.anyRequest().authenticated()
                    authorize.requestMatchers("/admin/**").hasRole("ADMIN");
                    authorize.anyRequest().permitAll();
                })
                .formLogin((formalin) -> {
                    formalin.loginPage("http://localhost:3000/login");
                })
                .logout((logout) -> {
                    logout.logoutSuccessUrl("/logout");
                })
                .rememberMe((remember) -> {
                    remember.rememberMeParameter("remember")
                            .alwaysRemember(true);
                })
                .build();
    }
}