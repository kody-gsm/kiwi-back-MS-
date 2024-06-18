package com.example.kiwi.config;

import com.example.kiwi.domain.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)//csrf 공격 꺼두기
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/login","sign-up").permitAll();
                    authorize.requestMatchers("/admin/**").hasRole(UserRole.ADMIN.name());
                    authorize.anyRequest().authenticated();
                })
                .formLogin((formalin) -> {
                    formalin.usernameParameter("email");
                    formalin.passwordParameter("password");
//                    formalin.loginPage("/login");
//                    formalin.defaultSuccessUrl("/asdf");
                })
                .logout((logout) -> {
                    logout.logoutSuccessUrl("/");
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                    logout.logoutUrl("/logout");
                })
                .rememberMe((remember) -> {
                    remember.rememberMeParameter("remember");
                })
                .sessionManagement((session) -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .build();
    }
}