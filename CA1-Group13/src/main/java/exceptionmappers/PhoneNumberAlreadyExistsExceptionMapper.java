package exceptionmappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ErrorMessage;
import exceptions.PhoneNumberAlreadyExistsException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PhoneNumberAlreadyExistsExceptionMapper implements ExceptionMapper<PhoneNumberAlreadyExistsException> {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private ServletContext context;

    @Override
    public Response toResponse(PhoneNumberAlreadyExistsException exception)
    {
        boolean isDebug = context.getInitParameter("debug").equals("true");

        ErrorMessage err = new ErrorMessage(exception, 409, isDebug);
        err.setDescription("PhoneNumber already exists!");
        err.setMessage("PhoneNumber already exists!");

        return Response
                .status(Response.Status.CONFLICT)
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
