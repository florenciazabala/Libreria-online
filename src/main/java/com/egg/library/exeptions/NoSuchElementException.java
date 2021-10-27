package com.egg.library.exeptions;

public class NoSuchElementException extends NotFoundException{
    private static final String DESCRIPTION ="No Such Element Exception";

    public NoSuchElementException(String message){super((DESCRIPTION+". "+message));}
}
