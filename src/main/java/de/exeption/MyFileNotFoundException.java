package de.exeption;

public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String string) {
        super(string);
    }
}