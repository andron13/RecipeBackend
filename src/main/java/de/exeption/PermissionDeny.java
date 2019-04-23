package de.exeption;

public class PermissionDeny extends RuntimeException {
    public PermissionDeny(String string) {
        super(string);
    }
}
