/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptionmappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ErrorMessage;
import exceptions.NoPersonsAtZipcodeException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoPersonsAtZipcodeExceptionMapper implements ExceptionMapper<NoPersonsAtZipcodeException> {

    @Context
    private ServletContext context;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(NoPersonsAtZipcodeException e)
    {
        boolean isDebug = context.getInitParameter("debug").equals("true");
        ErrorMessage err = new ErrorMessage(e, 404, isDebug);
        err.setDescription("No persons at zipcode");
        err.setMessage("No persons at zipcode");
        
        return Response.status(404).entity(gson.toJson(err)).build();
    }

}
