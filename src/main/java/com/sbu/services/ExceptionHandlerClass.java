package com.sbu.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.sbu.controller.StorageController;
import com.sbu.exceptions.MySQLNotConnectedException;
import com.sbu.exceptions.ResourceNotFoundException;
import com.sbu.main.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static com.sbu.services.ResponseUtil.*;

/**
 * Created by Nicholas Genco on 3/1/17.
 * this is our restendpoint Class
 */
@CrossOrigin
@RestController
public class ExceptionHandlerClass {

    @Autowired
    private StorageController storageController;

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
            errors.add(objectError.getDefaultMessage());
        }
        StringBuilder message = new StringBuilder("The following errors occurred: ");
        for(String error: errors){
            message.append(error + " \n " );
        }
        message.deleteCharAt(message.length() - 1); // delete extra comma
        return build400(message.toString());
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


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/movie/{movieID}/", method = RequestMethod.PATCH)
    public Response patchLatestState(@PathVariable String movieID, @RequestBody @Valid JsonNode patch) throws Exception {

        Utils.checkPatchValid(patch);
        Utils.checkMovieExists(movieID);

        boolean result = StorageController.patchMovieByID(movieID, patch);
        if (result){
            return build204();
        }else{
            throw new ResourceNotFoundException("Movie");
        }
    }



}


