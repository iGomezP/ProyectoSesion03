package org.bedu.proyectofinalmodulo3.Service.impl;

import lombok.extern.slf4j.Slf4j;
import org.bedu.proyectofinalmodulo3.Entity.ProductoFunkoEntity;
import org.bedu.proyectofinalmodulo3.Exceptions.FunkoAlreadyExistsException;
import org.bedu.proyectofinalmodulo3.Repository.IProductoFunkoRepository;
import org.bedu.proyectofinalmodulo3.Service.IProductoFunkoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductoFunkoServiceImpl implements IProductoFunkoService{
    private IProductoFunkoRepository funkoRepository;

    @Autowired
    public ProductoFunkoServiceImpl (IProductoFunkoRepository funkoRepository){
        this.funkoRepository = funkoRepository;
    }

    @Override
    public List<ProductoFunkoEntity> getAll() {
        return funkoRepository.findAll();
    }

    @Override
    public Optional<ProductoFunkoEntity> getById(Long id) {
        Optional<ProductoFunkoEntity> existeFunko = funkoRepository.findById(id);
        if(existeFunko.isPresent()){
            log.info("Producto encontrado - {}", existeFunko.get().getName());
            return existeFunko;
        }
        log.warn("El producto no existe.");
        return null;
    }

    @Override
    public ProductoFunkoEntity createFunko(ProductoFunkoEntity funko) {
        ProductoFunkoEntity existeFunko = funkoRepository.findOneByName(funko.getName());

        if(existeFunko == null){
            log.info("Creando nuevo producto..." + funko.getName());
            funkoRepository.save(funko);
            return funko;
        }
        log.warn("El producto ya existe.");
        throw new ResponseStatusException(HttpStatus.CONFLICT, "El producto ya existe");
    }
}
