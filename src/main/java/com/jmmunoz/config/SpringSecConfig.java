package com.jmmunoz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Realmente esta seguridad no vale para nada porque to-do el mundo tiene acceso a to-do
// Solo sirve para declarar dos usuarios (admin y user) que tienen dos roles distintos
@EnableWebSecurity
public class SpringSecConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build());

        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build());

        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/", "/index/**", "/product/**", "/checkout", "/docheckout").permitAll()
                .and().authorizeRequests().antMatchers("/login","logout").permitAll()
                .and().authorizeRequests().antMatchers("/static/css/**","/js/**", "/images/**", "/**/favicon.ico").permitAll()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
                .and().logout()
                .deleteCookies("remove")
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout-success")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        return http.build();
    }
}
