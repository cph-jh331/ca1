package exceptionmappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ErrorMessage;
import exceptions.NoPhoneNumbersFoundException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoPhoneNumbersFoundExceptionMapper implements ExceptionMapper<NoPhoneNumbersFoundException> {

    @Context
    private ServletContext context;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(NoPhoneNumbersFoundException e)
    {
        boolean isDebug = context.getInitParameter("debug").equals("true");
        ErrorMessage err = new ErrorMessage(e, 409, isDebug);
        err.setDescription("No phone numbers");
        err.setMessage("There need to be at least one phone number");

        return Response.status(409).entity(gson.toJson(err)).build();
    }
}
