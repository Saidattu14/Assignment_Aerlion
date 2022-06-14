package com.example.Assignment_Arelion.Security;
import com.example.Assignment_Arelion.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegisterAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    AuthService authService;


    public RegisterAuthenticationProvider(AuthService authService) {
        this.authService = authService;
    }

    public Authentication getAuth(final Authentication authentication)
    {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        final UserDetails principal = new User(name, password, grantedAuths);
        final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
        return auth;

    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if(this.authService.registerAuth(authentication))
        {
            return getAuth(authentication);
        }
        return null;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
