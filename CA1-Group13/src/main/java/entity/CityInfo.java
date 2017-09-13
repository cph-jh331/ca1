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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author bloch
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "CityInfo.findAllCityInfo", query = "SELECT c FROM CityInfo c")
        })
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String zip;
    private String city;
    @OneToMany(mappedBy = "cityInfo", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public CityInfo()
    {
    }

    public CityInfo(String zip, String city)
    {
        this.zip = zip;
        this.city = city;
    }

    public CityInfo(String zipCode, String city, List<Address> addresses)
    {
        this.zip = zipCode;
        this.city = city;
        this.addresses = addresses;
    }

    public boolean addAddress(Address address)
    {
        return this.addresses.add(address);
    }

    public String getZipCode()
    {
        return zip;
    }

    public void setZipCode(String zipCode)
    {
        this.zip = zipCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public List<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<Address> addresses)
    {
        this.addresses = addresses;
    }

}
