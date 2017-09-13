package exceptions.exceptionmappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.EmailAlreadyExistsException;
import exceptions.ErrorMessage;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
@Provider
public class EmailAlreadyExistsExceptionMapper implements ExceptionMapper<EmailAlreadyExistsException> {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private ServletContext context;

    @Override
    public Response toResponse(EmailAlreadyExistsException exception)
    {
        boolean isDebug = context.getInitParameter("debug").equals("true");

        ErrorMessage err = new ErrorMessage(exception, 409, isDebug);
        err.setDescription("Email already exists!");
        err.setMessage("Email already exists!");

        return Response
                .status(Response.Status.CONFLICT)
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
