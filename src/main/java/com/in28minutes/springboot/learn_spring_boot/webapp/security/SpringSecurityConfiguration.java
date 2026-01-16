package com.in28minutes.springboot.learn_spring_boot.webapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {


    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {

        final UserDetails userDetails1 = createNewUser("in28minutes", "dummy");
        final UserDetails userDetails2 = createNewUser("mleiria", "dummy1");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    @NonNull
    private UserDetails createNewUser(String username, String password) {
        final UserDetails userDetails =
                User.builder()
                        .passwordEncoder(passwordEncoder()::encode)
                        .username(username)
                        .password(password)
                        .roles("USER", "ADMIN")
                        .build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().permitAll())
                .formLogin(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
                return http.build();
        
    }
        
}
