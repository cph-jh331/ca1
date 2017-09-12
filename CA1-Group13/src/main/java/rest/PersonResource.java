/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Person;
import entityFacades.PersonFacade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author bloch
 */
@Path("person")
public class PersonResource {

    @Context
    private UriInfo context;

    private PersonFacade pf;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource()
    {
        pf = new PersonFacade(Persistence.createEntityManagerFactory("devPU"));
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson()
    {
        List<Person> pList = pf.getPersons();
        return Response.status(200).entity(gson.toJson(pList)).build();
    }

    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") int id)
    {
        Person p = pf.getPerson(id);
        return Response.status(200).entity(gson.toJson(p)).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(String entity)
    {
        Person p = gson.fromJson(entity, Person.class);
        p = pf.addPerson(p);
        return Response
                .status(Response.Status.CREATED)
                .entity(gson.toJson(p))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") int id)
    {
        Person p = pf.deletePerson(id);
        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(p))
                .build();
    }
}
