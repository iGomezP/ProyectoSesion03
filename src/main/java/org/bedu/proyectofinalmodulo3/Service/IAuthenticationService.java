package org.bedu.proyectofinalmodulo3.Service;

import org.bedu.proyectofinalmodulo3.Auth.AuthenticationRequest;
import org.bedu.proyectofinalmodulo3.Auth.AuthenticationResponse;
import org.bedu.proyectofinalmodulo3.Auth.RegisterRequest;

public interface IAuthenticationService {

    AuthenticationResponse register(RegisterRequest request);
}
