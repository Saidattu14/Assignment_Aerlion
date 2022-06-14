package com.example.Assignment_Arelion.Config;

import com.example.Assignment_Arelion.Security.RegisterAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Order(2)
@Configuration
@EnableWebSecurity
public class RegisterSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private RegisterAuthenticationProvider registerAuthenticationProvider;

    public RegisterSecurityConfig() {
        super();
    }

    public RegisterSecurityConfig(RegisterAuthenticationProvider registerAuthenticationProvider) {
        this.registerAuthenticationProvider = registerAuthenticationProvider;
    }

    public RegisterSecurityConfig(boolean disableDefaults, RegisterAuthenticationProvider registerAuthenticationProvider) {
        super(disableDefaults);
        this.registerAuthenticationProvider = registerAuthenticationProvider;
    }

    /**
     * This is method override the configure function and authorize and authenticate every request.
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

            http
                .authenticationProvider(this.registerAuthenticationProvider).antMatcher("/register/user")
                .httpBasic();
    }

}
