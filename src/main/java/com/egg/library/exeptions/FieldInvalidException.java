package com.egg.library.exeptions;

public class FieldInvalidException extends BadRequestException{
    private static final String DESCRIPTION ="Field Invalid Exception ";

    public FieldInvalidException(String message){super((DESCRIPTION+". "+message));}
}
