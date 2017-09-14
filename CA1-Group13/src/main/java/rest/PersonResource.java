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
import exceptions.EmailAlreadyExistsException;
import exceptions.NoPersonsAtZipcodeException;
import exceptions.PersonNotFoundException;
import exceptions.ZipCodeNotValidException;
import java.util.ArrayList;
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
import jsonmappers.PersonDetail;

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
        List<PersonDetail> jpList = new ArrayList<>();
        for (Person p : pList)
        {
            jpList.add(new PersonDetail(p));
        }
        return Response.status(200).entity(gson.toJson(jpList)).build();
    }

    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") long id)
    {
        Person p = pf.getPerson(id);
        if (p == null)
        {
            throw new PersonNotFoundException("Hvad satan");
        }

        return Response
                .status(200)
                .entity(gson.toJson(new PersonDetail(p)))
                .build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(String entity)
    {
        PersonDetail pd = gson.fromJson(entity, PersonDetail.class);

        Person p = pf.getByEmail(pd.getEmail());

        if (p != null)
        {
            throw new EmailAlreadyExistsException();
        }

        p = pd.convertToPerson();
        pd = new PersonDetail(pf.addPerson(p));

        return Response
                .status(Response.Status.CREATED)
                .entity(gson.toJson(pd))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") int id)
    {
        Person p = pf.getPerson(id);
        if (p == null)
        {
            throw new PersonNotFoundException();
        }
        PersonDetail pd = new PersonDetail(pf.deletePerson(p));

        return Response
                .status(Response.Status.OK)
                .entity(gson.toJson(pd))
                .build();
    }

    @GET
    @Path("zipcode/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByZip(@PathParam("id") String zipcode)
    {
        if (zipcode.length() > 4)
        {
            throw new ZipCodeNotValidException();

        }
        //spytter et array ud!
        List<Person> pList = pf.getPersons(zipcode);
        if (pList.isEmpty())
        {
            throw new NoPersonsAtZipcodeException();
        }
        List<PersonDetail> pdList = new ArrayList<>();
        for (Person person : pList)
        {
            pdList.add(new PersonDetail(person));
        }
        return Response.status(Response.Status.ACCEPTED)
                .entity(gson.toJson(pdList))
                .build();

    }

}
