package org.bedu.proyectofinalmodulo3.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bedu.proyectofinalmodulo3.Entity.DireccionEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private DireccionEntity address;

}
