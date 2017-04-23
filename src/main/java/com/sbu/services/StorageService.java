package com.sbu.services;

import com.sbu.exceptions.MySQLNotConnectedException;
import com.sbu.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.ws.rs.core.Response;

import static com.sbu.services.ResponseUtil.*;

/**
 * Created by nicholasgenco on 4/15/17.
 */
@RequestMapping("/storage")
@CrossOrigin
public class StorageService {



    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response handleValidationException(MethodArgumentNotValidException exception) {
        return build400("Bad Request");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleMappingException(HttpMessageNotReadableException exception){
        String message = "Invalid JSON syntax or format";
        return build400(message);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleRsourceNotFound(ResourceNotFoundException ex){
        return build404(ex.getLocalizedMessage());
    }

    @ExceptionHandler(MySQLNotConnectedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleMYSQLException(MySQLNotConnectedException e){
        return build500(e.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleInternalException(Exception e){
        return build500(e.getLocalizedMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleIllegalArgumentException(IllegalArgumentException e){
        return build400(e.getLocalizedMessage());
    }



}
