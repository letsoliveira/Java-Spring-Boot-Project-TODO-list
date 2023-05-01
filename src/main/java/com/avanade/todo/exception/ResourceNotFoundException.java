package com.avanade.todo.exception;

public class ResourceNotFoundException extends InvalidInputException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
