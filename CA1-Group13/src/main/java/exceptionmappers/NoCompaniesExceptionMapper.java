package exceptionmappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ErrorMessage;
import exceptions.NoCompaniesException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NoCompaniesExceptionMapper implements ExceptionMapper<NoCompaniesException> {

    @Context
    private ServletContext context;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(NoCompaniesException e)
    {
        boolean isDebug = context.getInitParameter("debug").equals("true");
        ErrorMessage err = new ErrorMessage(e, 404, isDebug);
        err.setDescription("No companies exists at all");
        err.setMessage("No companies exists at all");

        return Response.status(404).entity(gson.toJson(err)).build();
    }

}
