package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import deploy.DeployConfig;
import entity.Company;
import entityFacades.CompanyFacade;
import exceptions.CompanyNotFoundException;
import exceptions.NoCompaniesException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jsonmappers.CompanyDetail;

@Path("company")
public class CompanyResource {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private CompanyFacade cf;

    @Context
    private UriInfo context;

    public CompanyResource()
    {
        cf = new CompanyFacade(Persistence.createEntityManagerFactory(DeployConfig.PU_NAME));
    }

    @GET
    @Path("complete/{cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("cvr") long cvr)
    {
        Company c = cf.getCompany(cvr);
        if (c == null)
        {
            throw new CompanyNotFoundException();
        }
        return Response
                .status(200)
                .entity(gson.toJson(new CompanyDetail(c)))
                .build();
    }

    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCompanies()
    {
        List<Company> cList = cf.getCompanies();

        if (cList.isEmpty())
        {
            throw new NoCompaniesException();
        }

        List<CompanyDetail> cdList = new ArrayList<>();
        for (Company company : cList)
        {
            cdList.add(new CompanyDetail(company));
        }

        return Response
                .status(200)
                .entity(gson.toJson(cdList))
                .build();
    }
}
