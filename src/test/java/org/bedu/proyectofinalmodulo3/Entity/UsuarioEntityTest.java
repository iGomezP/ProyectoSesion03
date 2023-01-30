package org.bedu.proyectofinalmodulo3.Entity;

import jakarta.validation.ConstraintViolationException;
import org.bedu.proyectofinalmodulo3.Repository.IUsuarioRepository;
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
class UsuarioEntityTest {

    private UsuarioEntity usuarioEntity = new UsuarioEntity();

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IUsuarioRepository clienteRepository;

    public DireccionEntity implementaDireccion(){
        DireccionEntity newDireccion = new DireccionEntity();
        newDireccion.setCalle("Calle 1");
        newDireccion.setCiudad("Neza");
        newDireccion.setNumero(500);
        newDireccion.setEstado("Mexico");
        newDireccion.setCodigoPostal(57800);
        newDireccion.setColonia("Siempre Viva");

        return newDireccion;
    }

    public UsuarioEntity implementaCliente(){
        UsuarioEntity newCliente = new UsuarioEntity();
        newCliente.setName("Ignacio");
        newCliente.setPassword("Contra@123");
        newCliente.setEmail("mail@mail.com");
        newCliente.setDireccion(implementaDireccion());

        return newCliente;
    }

    @Test
    @DisplayName("Crea Cliente")
    public void testCrearCliente(){
        UsuarioEntity savedCliente = clienteRepository.save(implementaCliente());
        UsuarioEntity existCliente = entityManager.find(UsuarioEntity.class, savedCliente.getId());
        assertEquals(existCliente.getName(), implementaCliente().getName());
    }

    @Test
    @DisplayName("Modifica Cliente Correo")
    public void testModificaClienteCorreo(){
        usuarioEntity.setEmail("correo@correo.com");
        UsuarioEntity savedCliente = clienteRepository.save(implementaCliente());
        UsuarioEntity existCliente = entityManager.find(UsuarioEntity.class, savedCliente.getId());
        assertNotEquals(existCliente.getEmail(), usuarioEntity.getEmail());
    }

    @Test
    @DisplayName("Nombre 2 caracteres")
    public void testNombreDosCaracteres(){
        UsuarioEntity newCliente = new UsuarioEntity();
        newCliente.setName("Be");
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            UsuarioEntity savedCliente = clienteRepository.save(newCliente);
        });
    }

    @Test
    @DisplayName("Nombre 15 Caracteres")
    public void testNombreQuinceCaracteres(){
        UsuarioEntity newCliente = implementaCliente();
        newCliente.setName("BetoBeduIgnacio");
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            UsuarioEntity savedCliente = clienteRepository.save(newCliente);
        });
    }

    @Test
    @DisplayName("Email sin @")
    public void testEmailSinArroba(){
        UsuarioEntity newCliente = implementaCliente();
        newCliente.setEmail("correo");
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            UsuarioEntity savedCliente = clienteRepository.save(newCliente);
        });
    }

    @Test
    @DisplayName("Password sin minúscula")
    public void testPasswordSinMinus(){
        UsuarioEntity newCliente = implementaCliente();
        newCliente.setPassword("CONTRA@123");
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            UsuarioEntity savedCliente = clienteRepository.save(newCliente);
        });
    }

    @Test
    @DisplayName("Password sin mayúscula")
    public void testPasswordSinMayus(){
        UsuarioEntity newCliente = implementaCliente();
        newCliente.setPassword("123@contra");
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            UsuarioEntity savedCliente = clienteRepository.save(newCliente);
        });
    }

    @Test
    @DisplayName("Password sin símbolo")
    public void testPasswordSinSimbolo(){
        UsuarioEntity newCliente = implementaCliente();
        newCliente.setPassword("conTra7891");
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            UsuarioEntity savedCliente = clienteRepository.save(newCliente);
        });
    }
}