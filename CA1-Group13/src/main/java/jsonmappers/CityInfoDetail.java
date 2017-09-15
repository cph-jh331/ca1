/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonmappers;

import entity.CityInfo;

/**
 *
 * @author Lasse Andersen
 */
public class CityInfoDetail {
    
    private String zipcode;
    private String city;

    public CityInfoDetail(CityInfo c) {
        this.zipcode = c.getZipCode();
        this.city = c.getCity();
    }

    public CityInfoDetail() {
    }

    
    
    public CityInfoDetail(String zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
    
}
