package com.egg.library.util;

import com.egg.library.exeptions.FieldInvalidException;
import com.egg.library.exeptions.InvalidDataException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validations {

    public  static void validNotEmptyfield(String field){
        if (field.trim().isEmpty()) {
            throw new FieldInvalidException("The field can't be empty");
        }
    }

    public static void validString(String field){
        validNotEmptyfield(field);

        if (field.matches("^-?[0-9]+$")) {
            throw new FieldInvalidException("Only characters are allowed");
        }
    }

    public static String formatNames(String field){
        StringBuffer strbf = new StringBuffer();
        Matcher match = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(field);
        while(match.find()){
            match.appendReplacement(strbf, match.group(1).toUpperCase() + match.group(2).toLowerCase());
        }
        return match.appendTail(strbf).toString();
    }

    private static final String YEAR_REGEX = "\\d{1,4}";
    private static final String ISBN_REGEX  = "\\d{13}";
    private static final String DOCUMENT_REGEX  = "\\d{8}";

    public static void validISBN(Long fieldISBN){
        String parse = String.valueOf(fieldISBN);
        validNotEmptyfield(parse);

        if (!parse.matches(ISBN_REGEX)) {
            throw new FieldInvalidException("The 'ISBN' must contain 13 digits");
        }
    }
    public static void validYear(Integer fieldYear){
        String parse = String.valueOf(fieldYear);
        validNotEmptyfield(parse);

        if (!parse.matches(YEAR_REGEX)) {
            throw new FieldInvalidException("The 'year' must contain between 1 and 4 digits");
        }
    }
    public static void validDocument(Long document){
        String parse = String.valueOf(document);
        validNotEmptyfield(parse);

        if (!parse.matches(DOCUMENT_REGEX)) {
            throw new FieldInvalidException("The 'document' must contain 8 digits");
        }
    }

    public static void validCopy(Integer copy, Integer loanedCopy){
        if(loanedCopy>copy) throw new FieldInvalidException("The number of loaned copies cant't be greater than total copies");

    }

    public static void validLapse(LocalDate initDate, LocalDate endDate){
        if(initDate.isAfter(endDate)) throw new FieldInvalidException("The start date cannot be later than the end date");
    }

    public static Genre getGenre(String genre){
        return Arrays.stream(Genre.values())
                .filter(f -> f.getValueFormat().equals(genre))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Invalid value"));
    }
}
