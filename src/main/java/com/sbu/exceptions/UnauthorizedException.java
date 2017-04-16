package com.sbu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by silda05 on 11/18/16.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException{


    public UnauthorizedException(){}

    public UnauthorizedException(String message){
        super(message);
    }
}
