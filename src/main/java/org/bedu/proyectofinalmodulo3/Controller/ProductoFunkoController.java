package org.bedu.proyectofinalmodulo3.Controller;

import jakarta.validation.Valid;
import org.bedu.proyectofinalmodulo3.Entity.ProductoFunkoEntity;
import org.bedu.proyectofinalmodulo3.Service.IProductoFunkoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto/funko")
public class ProductoFunkoController {

    private IProductoFunkoService funkoService;

    public ProductoFunkoController(IProductoFunkoService funkoService){
        this.funkoService = funkoService;
    }

    @GetMapping
    public ResponseEntity<List> getAll(){
        return ResponseEntity.ok().body(funkoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductoFunkoEntity>> getById(@PathVariable("id") Long id ){
        return ResponseEntity.ok().body(funkoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductoFunkoEntity> createFunko (@Valid @RequestBody ProductoFunkoEntity funko){
        funkoService.createFunko(funko);
        return ResponseEntity.created(URI.create("0")).build();
    }


}
