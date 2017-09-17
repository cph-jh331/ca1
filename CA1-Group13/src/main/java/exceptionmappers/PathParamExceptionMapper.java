package exceptionmappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ErrorMessage;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.server.ParamException;

@Provider
public class PathParamExceptionMapper implements ExceptionMapper<ParamException.PathParamException> {

    @Context
    private ServletContext context;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(ParamException.PathParamException e)
    {
        boolean isDebug = context.getInitParameter("debug").equals("true");
        ErrorMessage err = new ErrorMessage(e, 404, isDebug);
        err.setDescription("Wrong type of path parameter");
        err.setMessage("Wrong type of path parameter");
        return Response.status(404).entity(gson.toJson(err)).build();
    }

}
