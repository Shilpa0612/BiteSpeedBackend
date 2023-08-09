package org.example.exceptions;

public class InvalidRequestException extends Exception{
    public InvalidRequestException(String msg)
    {
        super(msg);
    }
}
