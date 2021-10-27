package com.egg.library.exeptions;

public class UnauthorizedException extends RuntimeException{
    private static final String DESCRIPTION ="Unauthorized Exception (401)";

    public UnauthorizedException(String message) {
        super((DESCRIPTION+". "+message));
    }
}
