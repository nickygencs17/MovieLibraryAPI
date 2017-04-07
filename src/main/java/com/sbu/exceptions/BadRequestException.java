package com.sbu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by silda05 on 11/17/16.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception{
    public BadRequestException(){}
    public BadRequestException(String message){
        super(message);
    }
}
