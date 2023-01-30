package org.bedu.proyectofinalmodulo3.Entity;


import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDateTime;

@Data
@Entity
@RestResource(rel = "funko", path = "funkos")
@Table(name = "funkos")
public class ProductoFunkoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 80)
    @CsvBindByName(column = "Nombre")
    @NotEmpty(message = "El nombre del producto no puede estar vacío")
    private String name;

    @Column(name = "price", nullable = false)
    @CsvBindByName(column = "Precio")
    @Positive(message = "El precio debe ser mayor que 0")
    private int price;

    @Column(name="stock", nullable = false)
    @CsvBindByName(column = "Piezas")
    @PositiveOrZero(message = "El valor no puede ser negativo")
    private int stock;

    @Column(name = "layaway", nullable = false, length = 3)
    @CsvBindByName(column = "Apartado")
    @PositiveOrZero(message = "El valor no puede ser negativo")
    private int layaway;

    @Column(name = "create_date")
    @CreationTimestamp
    @Past(message = "No se puede añadir una fecha del pasado")
    private LocalDateTime create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    @Past(message = "No se puede añadir una fecha del pasado")
    private LocalDateTime last_update;
}
