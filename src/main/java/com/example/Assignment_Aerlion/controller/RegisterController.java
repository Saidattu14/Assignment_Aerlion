package com.example.Assignment_Aerlion.controller;


import com.example.Assignment_Aerlion.Security.CustomAuthenticationProvider;
import com.example.Assignment_Aerlion.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register/")
public class RegisterController  {

    @Autowired
    AuthService authService;

    @Autowired
    private CustomAuthenticationProvider authProvider;

    public RegisterController()
    {

    }
    @GetMapping("/user")
    public ResponseEntity<String> registerUser(Authentication authentication) {

        if(authentication == null)
        {
            return new ResponseEntity<>("Authentication Needed",HttpStatus.BAD_REQUEST);
        }
        else if(authentication.getName() != null)
        {
            if(authService.RegisterAuthentication(authentication) == true)
            {
                return new ResponseEntity<>("Account Created For Access",HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Account Already Created For Access",HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Invalid Username or Password",HttpStatus.NOT_ACCEPTABLE);
    }

}
