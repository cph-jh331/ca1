package exceptions.exceptionmappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ErrorMessage;
import exceptions.ZipCodeNotValidException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ZipCodeNotValidExceptionMapper implements ExceptionMapper<ZipCodeNotValidException> {

    @Context
    private ServletContext context;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(ZipCodeNotValidException e)
    {
        boolean isDebug = context.getInitParameter("debug").equals("true");
        ErrorMessage err = new ErrorMessage(e, 404, isDebug);
        err.setDescription("Zipcode is too long");
        err.setMessage("Zipcode is too long");

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
