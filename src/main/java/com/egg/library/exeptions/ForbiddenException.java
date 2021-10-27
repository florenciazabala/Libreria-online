package com.egg.library.exeptions;

public class ForbiddenException extends RuntimeException{
    private static final String DESCRIPTION ="Forbidden Exception (403)";

    public ForbiddenException(String message) {
        super(DESCRIPTION+". "+message);
    }
}
