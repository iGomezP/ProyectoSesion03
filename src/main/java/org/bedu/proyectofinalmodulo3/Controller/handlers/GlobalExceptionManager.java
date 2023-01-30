package org.bedu.proyectofinalmodulo3.Controller.handlers;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.bedu.proyectofinalmodulo3.Entity.builders.RespuestaError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionManager extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new TreeMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }

        for(ObjectError error : ex.getBindingResult().getGlobalErrors()){
            errors.put(error.getObjectName(), error.getDefaultMessage());
        }

        RespuestaError respuestaError = new RespuestaError();
        respuestaError.setErrores(errors);
        respuestaError.setRuta(request.getDescription(false).substring(4));

        return handleExceptionInternal(ex, respuestaError, headers, HttpStatus.BAD_REQUEST,request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintException(ConstraintViolationException ex, WebRequest request){
        List<String> errors = new ArrayList<String>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName()
                    + " "
                    + violation.getPropertyPath()
                    + ": "
                    + violation.getMessage());
            log.warn(violation.getMessage());
        }

        RespuestaError respuestaError = new RespuestaError(
                HttpStatus.BAD_REQUEST,
                ex.getLocalizedMessage(),
                errors);
        respuestaError.setRuta(request.getDescription(false).substring(4));

        return handleExceptionInternal(ex, respuestaError, new HttpHeaders(), respuestaError.getStatus(), request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){

        RespuestaError respuestaError = new RespuestaError(
                HttpStatus.NOT_FOUND,
                "No se encuentra el producto");
        respuestaError.setRuta(request.getDescription(false).substring(4));

        return handleExceptionInternal(ex, respuestaError, new HttpHeaders(), respuestaError.getStatus(), request);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request){
        RespuestaError respuestaError = new RespuestaError(
                HttpStatus.BAD_REQUEST,
                "El correo ya se encuentra registrado");
        respuestaError.setRuta(request.getDescription(false).substring(4));

        return handleExceptionInternal(ex, respuestaError, new HttpHeaders(), respuestaError.getStatus(), request);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UsernameNotFoundException ex, WebRequest request){
        RespuestaError respuestaError = new RespuestaError(
                HttpStatus.NOT_FOUND,
                "El usuario no existe"
        );
        return handleExceptionInternal(ex, respuestaError, new HttpHeaders(), respuestaError.getStatus(), request);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleMalformedJwt(MalformedJwtException ex, WebRequest request){
        RespuestaError respuestaError = new RespuestaError(
                HttpStatus.NOT_FOUND,
                "El formato de JWT no es correcto"
        );
        return handleExceptionInternal(ex, respuestaError, new HttpHeaders(), respuestaError.getStatus(), request);
    }


}
