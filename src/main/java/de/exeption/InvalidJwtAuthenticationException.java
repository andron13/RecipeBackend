package de.exeption;


public class InvalidJwtAuthenticationException extends RuntimeException {
    public InvalidJwtAuthenticationException(String string) {
        super(string);
    }
}
