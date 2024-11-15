package com.quetionpro.grocerystore.controller;

import com.quetionpro.grocerystore.security.AuthRequest;
import com.quetionpro.grocerystore.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> getStatus() {

        return new ResponseEntity<String>("Allowed", HttpStatus.OK);

    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getAdminStatus() {

        return new ResponseEntity<String>("Allowed", HttpStatus.OK);

    }


    @PostMapping("/login")

    public ResponseEntity<String> login(@RequestBody AuthRequest loginRequest) {

        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));


        String token = jwtService.generateToken(loginRequest.getUsername());
        return new ResponseEntity<String>("Bearer " + token, HttpStatus.OK);

    }


}
