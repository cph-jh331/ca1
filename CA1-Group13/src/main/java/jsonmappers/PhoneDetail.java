/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonmappers;

/**
 *
 * @author Bloch
 */
public class PhoneDetail {

    private String phoneNumber;
    private String description;

    public PhoneDetail(String phoneNumber, String description)
    {
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
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
