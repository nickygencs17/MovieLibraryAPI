package com.sbu.services;

import javax.ws.rs.core.Response;

/**
 * Created by silda05 on 3/21/17.
 */

public class ResponseUtil {

    public static Response build400(String message) {

        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }

    public static Response build401() {
        return Response.status(Response.Status.UNAUTHORIZED).build();

    }

    public static Response build404(String message) {
        return Response.status(Response.Status.NOT_FOUND).entity(message).build();
    }

    public static Response build204() {
        return Response.noContent().build();
    }

    public static Response build200(Object obj) {
        return Response.status(Response.Status.OK).entity(obj).build();
    }

    public static Response build500(String message) {
        return Response.serverError().entity(message).build();
    }

    public static Response build201(Object obj) {
        return Response.status(Response.Status.CREATED).entity(obj).build();

    }


}