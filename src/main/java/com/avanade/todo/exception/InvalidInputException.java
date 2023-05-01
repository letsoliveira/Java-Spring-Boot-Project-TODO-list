package com.avanade.todo.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message){
        super(message);
    }
    public InvalidInputException(){
        super("Invalid Payload");
    }
}
