/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Address;
import entity.Person;
import entity.Phone;
import entityFacades.AddressFacade;
import entityFacades.CityInfoFacade;
import entityFacades.PersonFacade;
import entityFacades.PhoneFacade;
import exceptions.EmailAlreadyExistsException;
import exceptions.NoPersonsAtZipcodeException;
import exceptions.NoPhoneNumbersFoundException;
import exceptions.PersonNotFoundException;
import exceptions.PhoneNumberAlreadyExistsException;
import exceptions.PhoneNumberNotANumberException;
import exceptions.ZipCodeNotValidException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import jsonmappers.PersonDetail;
import jsonmappers.PhoneDetail;

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
    private AddressFacade af;
    private CityInfoFacade cityFac;
    private PhoneFacade phFac;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("devPU");
        this.pf = new PersonFacade(emf);
        this.cityFac = new CityInfoFacade(emf);
        this.af = new AddressFacade(emf);
        this.phFac = new PhoneFacade(emf);
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons()
    {
        List<Person> pList = pf.getPersons();

        List<PersonDetail> jpList = new ArrayList<>();
        for (Person p : pList)
        {
            jpList.add(new PersonDetail(p));
        }
        return Response
                .status(200)
                .entity(gson.toJson(jpList))
                .build();
    }

    @GET
    @Path("complete/{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("phoneNumber") long phoneNumber)
    {
        Person p = pf.getPerson(phoneNumber);
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

        //checking if the phoneNumber already exists, since our phone can only
        //have a single person, but a person can have many.
        List<PhoneDetail> pdPhoneList = pd.getPhones();

        if (pdPhoneList.isEmpty())
        {
            throw new NoPhoneNumbersFoundException();
        }

        for (PhoneDetail phoneDetail : pdPhoneList)
        {
            Long phoneNumber;
            try
            {
                phoneNumber = Long.parseLong(phoneDetail.getPhoneNumber());

            } catch (NumberFormatException e)
            {
                throw new PhoneNumberNotANumberException();
            }

            p = pf.getPerson(phoneNumber);
            if (p != null)
            {
                throw new PhoneNumberAlreadyExistsException();
            }
        }

        // creating a new person..:
        p = new Person(pd.getFirstName(), pd.getLastName(), pd.getEmail());
        p = pf.addPerson(p);

        List<Phone> phoneList = new ArrayList<>();
        for (PhoneDetail phoneDetail : pdPhoneList)
        {
            Phone phone = new Phone(phoneDetail.getPhoneNumber(), phoneDetail.getDescription(), p);
            p.addPhone(phone);
        }

        Address adr = af.getAddressWithoutId(pd.getStreet(), pd.getAdditionalInfo(), pd.getZipcode());

        if (adr != null)
        {
            adr.AddInfoEntityToList(p);
            adr = af.editAddress(adr);

        } else
        {
            //add new address
            adr = new Address(pd.getStreet(), pd.getAdditionalInfo(), cityFac.getCityInfo(pd.getZipcode()));
            adr = af.addAddress(adr);
        }
        p.setAddress(adr);
        p = pf.editPerson(p);

        pd = new PersonDetail(p);
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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(String entity)
    {
        PersonDetail pd = gson.fromJson(entity, PersonDetail.class);
        Person p = pf.getByEmail(pd.getEmail());
        if (p == null)
        {
            throw new PersonNotFoundException();
        }

        Person pEdited = pd.convertToPerson();
        pEdited.setAddress(null);

        pEdited.setId(p.getId());

        Address adr = af.getAddressWithoutId(pd.getStreet(), pd.getAdditionalInfo(), pd.getZipcode());

        if (adr == null)
        {
            adr = new Address(pd.getStreet(), pd.getAdditionalInfo(), cityFac.getCityInfo(pd.getZipcode()));
            adr = af.addAddress(adr);
        } else
        {
            adr.AddInfoEntityToList(pEdited);
            adr = af.editAddress(adr);
        }

        pEdited.setAddress(adr);

        p = pf.editPerson(pEdited);

        System.out.println(p.getFirstName());

        pd = new PersonDetail(p);
        return Response.status(201).entity(gson.toJson(pd)).build();
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
        return Response
                .status(Response.Status.ACCEPTED)
                .entity(gson.toJson(pdList))
                .build();
    }

}
