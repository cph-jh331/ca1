/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author bloch
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(
        {
            @NamedQuery(name = "InfoEntity.findAllPerson", query = "SELECT p FROM Person p")
            ,@NamedQuery(name = "InfoEntity.findAllByZipcode", query = "SELECT p FROM Person p JOIN FETCH p.address a WHERE a.cityinfo_zip = :0800")
        })
public abstract class InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @OneToMany(mappedBy = "infoEntity")
    private List<Phone> phone;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    public InfoEntity()
    {
    }

    public InfoEntity(String email, List<Phone> phone, Address address)
    {
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<Phone> getPhone()
    {
        return phone;
    }

    public void setPhone(List<Phone> phone)
    {
        this.phone = phone;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

}
