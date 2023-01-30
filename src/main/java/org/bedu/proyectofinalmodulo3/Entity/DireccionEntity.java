package org.bedu.proyectofinalmodulo3.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Data
@Table(name="direcciones")
@RestResource(exported = false)
public class DireccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No debe ir vacío")
    private String calle;

    @Positive(message = "No debe negativo ni cero")
    private int numero;

    @NotEmpty(message = "No debe ir vacío")
    private String ciudad;

    @NotEmpty(message = "No debe ir vacío")
    private String estado;

    @Positive(message = "No debe negativo ni cero")
    private int codigoPostal;

    @NotEmpty(message = "No debe ir vacío")
    private String colonia;
}
