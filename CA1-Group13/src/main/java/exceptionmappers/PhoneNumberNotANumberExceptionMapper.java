package exceptionmappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ErrorMessage;
import exceptions.PhoneNumberNotANumberException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PhoneNumberNotANumberExceptionMapper implements ExceptionMapper<PhoneNumberNotANumberException> {

    @Context
    private ServletContext context;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(PhoneNumberNotANumberException e)
    {
        boolean isDebug = context.getInitParameter("debug").equals("true");
        ErrorMessage err = new ErrorMessage(e, 400, isDebug);
        err.setDescription("Phone number is not a valid number");
        err.setMessage("Phone number is not a number");

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
