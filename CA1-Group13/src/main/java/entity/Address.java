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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
            @NamedQuery(name = "Address.findAllAddresses", query = "SELECT a FROM Address a")
            ,@NamedQuery(name = "Address.findAddressWithNoId", query = "SELECT a FROM Address a where a.cityInfo.zip =:zipcode AND a.additional =:addInfo AND a.street =:street")
        })
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    @OneToMany(mappedBy = "address", cascade = CascadeType.MERGE)
    private List<InfoEntity> infoEntities = new ArrayList<>();
    private String additional;
    @ManyToOne(cascade = CascadeType.MERGE)
    private CityInfo cityInfo;

    public Address()
    {

    }

    public Address(String street, String additional, CityInfo cityInfo)
    {
        this.street = street;
        this.additional = additional;
        this.cityInfo = cityInfo;
    }

    public boolean AddInfoEntityToList(InfoEntity infoEntity)
    {
        return this.infoEntities.add(infoEntity);
    }

    public boolean removeInfoEntity(InfoEntity infoEntity)
    {
        return this.infoEntities.remove(infoEntity);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public List<InfoEntity> getInfoEntities()
    {
        return infoEntities;
    }

    public void setInfoEntities(List<InfoEntity> infoEntities)
    {
        this.infoEntities = infoEntities;
    }

    public CityInfo getCityInfo()
    {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo)
    {
        this.cityInfo = cityInfo;
    }

    public String getAdditional()
    {
        return additional;
    }

    public void setAdditional(String additional)
    {
        this.additional = additional;
    }

}
