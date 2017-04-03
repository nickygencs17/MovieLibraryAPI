package com.sbu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by silda05 on 3/27/17.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    String type;
    
    public ResourceNotFoundException(String stream) {
//        if(stream.equals(Constants.USER_STREAM_NAME)) {
//            type = "USER";
//        } else if (stream.equals(Constants.SESSION_STREAM_NAME)) {
//            type = "SESSION";
//        } else {
//            type = "STATE";
//        }
    }
    
    @Override
    public String getLocalizedMessage(){
        return type + "constant";
    }
}
