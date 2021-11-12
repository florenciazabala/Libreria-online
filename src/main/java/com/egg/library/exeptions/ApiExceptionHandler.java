package com.egg.library.exeptions;

import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
   // @ResponseBody
    public ModelAndView notFoundRequest(HttpServletRequest request, NoHandlerFoundException exception){
        ModelAndView model = new ModelAndView("error");
        model.addObject("codeError","404");
        model.addObject("textError","Not found");
        model.addObject("description",""+exception.getMessage());

        return model;
        //return new ErrorMessage(exception,request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request,Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ForbiddenException.class/*,
    org.springframework.security.authentication.InternalAuthenticationServiceException.class*/})
    @ResponseBody
    public ErrorMessage forbiddenRequest(HttpServletRequest request,Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ConflictException.class})
    @ResponseBody
    public ErrorMessage conflictRequest(HttpServletRequest request,Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class})
    public void unauthorized(){ }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ErrorMessage fatalErrorUnexpectedErrorRequest(HttpServletRequest request,Exception exception){
        return new ErrorMessage(exception,request.getRequestURI());
    }
}
