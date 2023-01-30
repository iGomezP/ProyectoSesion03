package org.bedu.proyectofinalmodulo3.Entity;

import jakarta.validation.ConstraintViolationException;
import org.bedu.proyectofinalmodulo3.Repository.IProductoFunkoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
class ProductoFunkoEntityTest {
    private ProductoFunkoEntity funkoEntity = new ProductoFunkoEntity();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IProductoFunkoRepository funkoRepository;

    public ProductoFunkoEntity implementaFunko(){
        ProductoFunkoEntity newFunko = new ProductoFunkoEntity();
        newFunko.setName("Funko Test");
        newFunko.setPrice(5000);
        newFunko.setStock(10);
        newFunko.setLayaway(5);

        return newFunko;
    }

    @Test
    @DisplayName("Crear Funko")
    public void testCrearFunko(){
        ProductoFunkoEntity savedFunko = funkoRepository.save(implementaFunko());
        ProductoFunkoEntity existFunko = entityManager.find(ProductoFunkoEntity.class, savedFunko.getId());
        assertEquals(existFunko.getName(), implementaFunko().getName());
    }

    @Test
    @DisplayName("Modifica Funko")
    public void testModificaFunko(){
        funkoEntity.setName("Updated Test Funko");
        ProductoFunkoEntity savedFunko = funkoRepository.save(implementaFunko());
        ProductoFunkoEntity existFunko = entityManager.find(ProductoFunkoEntity.class, savedFunko.getId());
        assertNotEquals(existFunko.getName(), funkoEntity.getName());
    }

    @Test
    @DisplayName("Nombre vacio")
    public void testNombreVacio(){
        ProductoFunkoEntity newFunko = implementaFunko();
        newFunko.setName("");
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            ProductoFunkoEntity savedFunko = funkoRepository.save(newFunko);
        });
    }

    @Test
    @DisplayName("Nombre lleno")
    public void testNombreLleno(){
        ProductoFunkoEntity newFunko = implementaFunko();
        newFunko.setName("New test name");
        ProductoFunkoEntity exception = assertDoesNotThrow(() -> funkoRepository.save(newFunko));
    }

    @Test
    @DisplayName("Precio en cero")
    public void testPrecioCero(){
        ProductoFunkoEntity newFunko = implementaFunko();
        newFunko.setPrice(0);

        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            ProductoFunkoEntity savedFunko = funkoRepository.save(newFunko);
        });
    }

    @Test
    @DisplayName("Precio valido")
    public void testPrecioValido(){
        ProductoFunkoEntity newFunko = implementaFunko();
        newFunko.setPrice(110);
        ProductoFunkoEntity exception = assertDoesNotThrow(() -> funkoRepository.save(newFunko));
    }

    @Test
    @DisplayName("Stock negativo")
    public void testStockNegativo(){
        ProductoFunkoEntity newFunko = implementaFunko();
        newFunko.setStock(-1);
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            ProductoFunkoEntity savedFunko = funkoRepository.save(newFunko);
        });
    }

    @Test
    @DisplayName("Stock positivo o cero")
    public void testStockPositivoCero(){
        ProductoFunkoEntity newFunko = implementaFunko();
        newFunko.setStock(1);
        ProductoFunkoEntity exception1 = assertDoesNotThrow(() -> funkoRepository.save(newFunko));
        newFunko.setStock(0);
        ProductoFunkoEntity exception2 = assertDoesNotThrow(() -> funkoRepository.save(newFunko));
    }

    @Test
    @DisplayName("Apartado negativo")
    public void testApartadoNegativo(){
        ProductoFunkoEntity newFunko = implementaFunko();
        newFunko.setLayaway(-1);
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            ProductoFunkoEntity savedFunko = funkoRepository.save(newFunko);
        });
    }

    @Test
    @DisplayName("Stock positivo o cero")
    public void testApartadoPositivoCero(){
        ProductoFunkoEntity newFunko = implementaFunko();
        newFunko.setLayaway(1);
        ProductoFunkoEntity exception1 = assertDoesNotThrow(() -> funkoRepository.save(newFunko));
        newFunko.setLayaway(0);
        ProductoFunkoEntity exception2 = assertDoesNotThrow(() -> funkoRepository.save(newFunko));
    }
}