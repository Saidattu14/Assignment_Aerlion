package com.example.Assignment_Arelion.Config;
import com.example.Assignment_Arelion.Security.CustomAuthenticationProvider;
import com.example.Assignment_Arelion.Security.RegisterAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Order(1)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;


    public SecurityConfig() {
        super();
    }


    public SecurityConfig(CustomAuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    public SecurityConfig(boolean disableDefaults, CustomAuthenticationProvider authProvider) {
        super(disableDefaults);
        this.authProvider = authProvider;
    }

    /**
     * This is method override the configure function and authorize and authenticate every request.
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .authenticationProvider(authProvider).antMatcher("/api/**")
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}