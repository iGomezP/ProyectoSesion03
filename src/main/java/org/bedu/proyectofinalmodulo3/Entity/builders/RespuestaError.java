package org.bedu.proyectofinalmodulo3.Entity.builders;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@NoArgsConstructor
public class RespuestaError {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private int estatus;

    private HttpStatus status;
    private String message;

    private Object errors;
    private Map<String, String> errores;
    private String ruta;

    public RespuestaError(int estatus, Map<String, String> errores, String ruta) {
        this.estatus = estatus;
        this.errores = errores;
        this.ruta = ruta;
    }

    public RespuestaError(HttpStatus status, String message, Object errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public RespuestaError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Map<String, String> getErrores() {
        return errores;
    }

    public void setErrores(Map<String, String> errores) {
        this.errores = errores;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
