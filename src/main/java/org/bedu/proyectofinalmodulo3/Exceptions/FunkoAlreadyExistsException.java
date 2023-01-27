package org.bedu.proyectofinalmodulo3.Exceptions;

public class FunkoAlreadyExistsException extends RuntimeException{
    private String message;
    public FunkoAlreadyExistsException(){}

    public FunkoAlreadyExistsException(String msg){
        super(msg);
        this.message=msg;
    }
}
