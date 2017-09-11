/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author bloch
 */
@Entity
public class Person extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Hobby> hobby;

    public Person()
    {
    }

    public Person(String firstName, String lastName, List<Hobby> hobby, String email, List<Phone> phone, Address address)
    {
        super(email, phone, address);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobby = hobby;
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

    public List<Hobby> getHobby()
    {
        return hobby;
    }

    public void setHobby(List<Hobby> hobby)
    {
        this.hobby = hobby;
    }

}
