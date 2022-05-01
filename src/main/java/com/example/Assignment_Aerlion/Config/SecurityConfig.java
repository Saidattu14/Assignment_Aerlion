package com.example.Assignment_Aerlion.Config;
import com.example.Assignment_Aerlion.Security.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    public SecurityConfig() {
        super();
    }

    /**
     * This is method override the configure function and verifies the auth Sign In details.
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(authProvider);
    }
    /**
     * This is method override the configure function and authorize and authenticate every request.
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
//             http
//                .authorizeRequests()
//                .antMatchers("/register/user/**").permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/v1/**").hasAnyRole()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
             http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}