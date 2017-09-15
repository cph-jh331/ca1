/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author bloch
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "Company.findAllCompanies", query = "SELECT c FROM Company c")
            ,@NamedQuery(name = "Company.findCompWithCVR", query = "SELECT c FROM Company c where c.cvr = :cvr")
        })
public class Company extends InfoEntity implements Serializable {

    private String name;
    private String description;
    @Column(unique = true)
    private long cvr;
    private int numEmployees;
    private double marketValue;

    public Company()
    {
    }

    public Company(String name, String description, long cvr, int numEmployees, double marketValue, String email)
    {
        super(email);
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.numEmployees = numEmployees;
        this.marketValue = marketValue;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public long getCvr()
    {
        return cvr;
    }

    public void setCvr(long cvr)
    {
        this.cvr = cvr;
    }

    public int getNumEmployees()
    {
        return numEmployees;
    }

    public void setNumEmployees(int numEmployees)
    {
        this.numEmployees = numEmployees;
    }

    public double getMarketValue()
    {
        return marketValue;
    }

    public void setMarketValue(double marketValue)
    {
        this.marketValue = marketValue;
    }

}
