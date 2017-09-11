/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author bloch
 */
@Entity
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String number;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private InfoEntity infoEntity;

    public Phone()
    {
    }

    public Phone(String number, String description, InfoEntity infoEntity)
    {
        this.number = number;
        this.description = description;
        this.infoEntity = infoEntity;
    }
    
    

    public InfoEntity getInfoEntity()
    {
        return infoEntity;
    }

    public void setInfoEntity(InfoEntity infoEntity)
    {
        this.infoEntity = infoEntity;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

   

}
