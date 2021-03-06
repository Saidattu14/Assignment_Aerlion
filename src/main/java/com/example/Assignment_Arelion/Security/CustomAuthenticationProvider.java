package com.example.Assignment_Arelion.Security;

import java.util.ArrayList;
import java.util.List;
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

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    AuthService authService;

    public CustomAuthenticationProvider(AuthService authService) {
        this.authService = authService;
    }

    /**
     * This method verifies the userdata and grant access the API.
     */
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

    /**
     * This method authenticate the user Sign In to access the API.
     */
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        boolean result = this.authService.userDataAuthentication(authentication);
        if(result == false)
        {
            return null;
        }
        return getAuth(authentication);
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}