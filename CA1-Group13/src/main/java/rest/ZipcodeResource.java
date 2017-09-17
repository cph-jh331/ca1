/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import deploy.DeployConfig;
import entity.CityInfo;
import entityFacades.CityInfoFacade;
import exceptions.NoCityInfoException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jsonmappers.CityInfoDetail;

/**
 * REST Web Service
 *
 * @author Lasse Andersen
 */
@Path("zipcode")
public class ZipcodeResource {

    private CityInfoFacade cif;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ZipcodeResource
     */
    public ZipcodeResource()
    {
        cif = new CityInfoFacade(Persistence.createEntityManagerFactory(DeployConfig.PU_NAME));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllZipcodes()
    {
        List<CityInfo> ciList = cif.getAllCityInfos();
        if (ciList.isEmpty())
        {
            throw new NoCityInfoException();
        }
        List<CityInfoDetail> cidList = new ArrayList<>();
        for (CityInfo cityInfo : ciList)
        {
            cidList.add(new CityInfoDetail(cityInfo));
        }
        return Response.status(200).entity(gson.toJson(cidList)).build();
    }

    /**
     * PUT method for updating or creating an instance of ZipcodeResource
     *
     * @param content representation for the resource
     */
}
