/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionmappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.CityInfoDoesNotExistException;
import exceptions.ErrorMessage;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Bloch
 */
@Provider
public class CityInfoDoesNotExistExceptionMapper implements ExceptionMapper<CityInfoDoesNotExistException> {

    @Context
    private ServletContext context;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(CityInfoDoesNotExistException e)
    {
        boolean isDebug = context.getInitParameter("debug").equals("true");
        ErrorMessage err = new ErrorMessage(e, 409, isDebug);
        err.setDescription("Zipcode does not exist in the Kingdom of Denmark.");
        err.setMessage("Zipcode does not exist in the Kingdom of Denmark.");

        return Response.status(409).entity(gson.toJson(err)).build();
    }

}
