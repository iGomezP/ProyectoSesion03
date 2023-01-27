package org.bedu.proyectofinalmodulo3.Service;

import org.bedu.proyectofinalmodulo3.Entity.ProductoFunkoEntity;

import java.util.List;
import java.util.Optional;

public interface IProductoFunkoService {
    List<ProductoFunkoEntity> getAll();

    Optional<ProductoFunkoEntity> getById(Long id);

    ProductoFunkoEntity createFunko(ProductoFunkoEntity funko) ;
}
