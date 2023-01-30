package org.bedu.proyectofinalmodulo3.Auth;

import lombok.RequiredArgsConstructor;
import org.bedu.proyectofinalmodulo3.Auth.AuthenticationRequest;
import org.bedu.proyectofinalmodulo3.Auth.AuthenticationResponse;
import org.bedu.proyectofinalmodulo3.Auth.RegisterRequest;
import org.bedu.proyectofinalmodulo3.Service.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        authService.register(request);
        return ResponseEntity.created(URI.create("0")).build();
    }
}
