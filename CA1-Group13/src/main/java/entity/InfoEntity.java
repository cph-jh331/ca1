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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author bloch
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)    
    private String email;

    @OneToMany(mappedBy = "infoEntity", cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    private Address address;

    public InfoEntity()
    {
    }

    public InfoEntity(String email)
    {
        this.email = email;
    }

    public InfoEntity(String email, Address address)
    {
        this.email = email;
        this.address = address;
    }

    public InfoEntity(String email, List<Phone> phones, Address address)
    {
        this.email = email;
        this.phones = phones;
        this.address = address;
    }

    public boolean addPhone(Phone phone)
    {
        return phones.add(phone);
    }

    public boolean addPhone(String phoneNumber, String desc)
    {
        return phones.add(new Phone(phoneNumber, desc, this));
    }

    public boolean removePhone(String phoneNumber)
    {
        for (Phone phone : phones)
        {
            if (phone.getNumber().equals(phoneNumber))
            {
                return phones.remove(phone);
            }
        }
        return false;
    }

    public boolean removePhone(Phone phone)
    {
        return phones.remove(phone);
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfoEntity))
        {
            return false;
        }
        InfoEntity other = (InfoEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
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

    public List<Phone> getPhones()
    {
        return phones;
    }

    public void setPhones(List<Phone> phones)
    {
        this.phones = phones;
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
