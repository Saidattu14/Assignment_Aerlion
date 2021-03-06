package com.example.Assignment_Arelion.Controller;


import com.example.Assignment_Arelion.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/register/")
public class RegisterController  {

    @Autowired
    AuthService authService;

    public RegisterController()
    {

    }
    /**
     * This is a get request end point /register/user.
     * It makes user to get input their Sign In details.
     * @return the String in Json Form
     */
    @GetMapping("/user")
    public ResponseEntity<String> registerUser(Authentication authentication, HttpServletRequest request) {

        if(authentication == null)
        {
            return new ResponseEntity<>("Authentication Needed",HttpStatus.BAD_REQUEST);
        }
        else if(authentication.getName() != null)
        {
            if(authService.registerAuthentication(authentication) == true)
            {
                return new ResponseEntity<>("Account Created For Access",HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Account Already Created For Access",HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Invalid Username or Password",HttpStatus.NOT_ACCEPTABLE);
    }

}
