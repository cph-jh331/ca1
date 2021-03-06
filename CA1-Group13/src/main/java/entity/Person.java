/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author bloch
 */
@Entity
@Table(name = "person")
@NamedQueries(
        {
            @NamedQuery(name = "Person.findAllPersons", query = "SELECT p FROM Person p")
            ,@NamedQuery(name = "Person.findPersonsByZip", query = "SELECT p FROM Person p WHERE p.address.cityInfo.zip =:zipcode")
            ,@NamedQuery(name = "Person.findPersonByPhone", query = "SELECT DISTINCT p FROM Person p, IN(p.phones) t WHERE t.number = :phoneNumber")
            ,@NamedQuery(name = "Person.findPersonByEmail", query = "SELECT DISTINCT p FROM Person p WHERE p.email =:email")
        //,@NamedQuery(name = "Person.findPersonsByHobby", query = "SELECT p FROM Person p where p.hobbies.name =:hobbyName")
        })
public class Person extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Hobby> hobbies = new ArrayList<>();

    public Person()
    {
    }

    public Person(String firstName, String lastName, String email, List<Phone> phones, Address address)
    {
        super(email, phones, address);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, String email, Address address)
    {
        super(email, address);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, String email)
    {
        super(email);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean addHobby(Hobby hobby)
    {
        return hobbies.add(hobby);

    }

    public boolean removeHobby(Hobby hobby)
    {
        return hobbies.remove(hobby);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<Hobby> getHobbies()
    {
        return hobbies;
    }

    @Override
    public Address getAddress()
    {
        return super.getAddress();
    }

    public void setHobbies(List<Hobby> hobbies)
    {
        this.hobbies = hobbies;
    }

}
