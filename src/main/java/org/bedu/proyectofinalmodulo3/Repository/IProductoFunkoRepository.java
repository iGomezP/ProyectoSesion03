package org.bedu.proyectofinalmodulo3.Repository;

import org.bedu.proyectofinalmodulo3.Entity.ProductoFunkoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IProductoFunkoRepository extends JpaRepository<ProductoFunkoEntity, Long> {
    ProductoFunkoEntity findOneByName(String name);
    List<ProductoFunkoEntity> findAllByNameContaining(String name);

}