package com.sbu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

/**
 * Created by Nicholas Genco on 3/1/17.
 * this class will talk to client
 */
@Component
public class StorageController {


    public static boolean patchMovieByID(String movieID, JsonNode patch) {
        return true;

    }
}
