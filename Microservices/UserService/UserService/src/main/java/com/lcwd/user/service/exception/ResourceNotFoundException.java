package com.lcwd.user.service.exception;

public class ResourceNotFoundException extends RuntimeException{

    public  ResourceNotFoundException(String message){
        super(message);
    }
    public  ResourceNotFoundException(){
        super("Resource not found");
    }
}
