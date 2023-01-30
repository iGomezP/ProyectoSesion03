package org.bedu.proyectofinalmodulo3.Service.impl;

import lombok.RequiredArgsConstructor;
import org.bedu.proyectofinalmodulo3.Auth.AuthenticationRequest;
import org.bedu.proyectofinalmodulo3.Auth.AuthenticationResponse;
import org.bedu.proyectofinalmodulo3.Auth.RegisterRequest;
import org.bedu.proyectofinalmodulo3.Config.Security.JwtService;
import org.bedu.proyectofinalmodulo3.Entity.Role;
import org.bedu.proyectofinalmodulo3.Entity.UsuarioEntity;
import org.bedu.proyectofinalmodulo3.Repository.IUsuarioRepository;
import org.bedu.proyectofinalmodulo3.Service.IAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Create user, save to DB and return the jwt
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = UsuarioEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                // Encode password before save
                .password(passwordEncoder.encode(request.getPassword()))
                .direccion(request.getAddress())
                .rol(Role.USER)
                .build();
        usuarioRepository.save(user);

        /*Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("rol", Role.USER);
*/
        // Generate Token
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
