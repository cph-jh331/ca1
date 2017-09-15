package jsonmappers;

import entity.Company;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;

public class CompanyDetail {

    private Long cvr;
    private String name;
    private String email;
    private String desc;
    private String streetInfo;
    private String street;
    private String city;
    private String zipCode;
    private List<PhoneDetail> phones;
    private double mValue;
    private int numbEmp;

    public CompanyDetail()
    {
    }

    public CompanyDetail(Company c)
    {
        this.cvr = c.getCvr();
        this.name = c.getName();
        this.email = c.getEmail();
        this.street = c.getAddress().getStreet();
        this.streetInfo = c.getAddress().getAdditional();
        this.desc = c.getDescription();
        this.numbEmp = c.getNumEmployees();
        this.zipCode = c.getAddress().getCityInfo().getZipCode();
        this.city = c.getAddress().getCityInfo().getCity();
        this.mValue = c.getMarketValue();
        List<Phone> phoneList = c.getPhones();
        List<PhoneDetail> cdPhones = new ArrayList<>();
        if (!phoneList.isEmpty())
        {
            for (Phone phone : phoneList)
            {
                cdPhones.add(new PhoneDetail(phone.getNumber(), phone.getDescription()));
            }
        } else
        {
            cdPhones = null;
        }
        this.phones = cdPhones;
    }

    public Long getCvr()
    {
        return cvr;
    }

    public void setCvr(Long cvr)
    {
        this.cvr = cvr;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getStreetInfo()
    {
        return streetInfo;
    }

    public void setStreetInfo(String streetInfo)
    {
        this.streetInfo = streetInfo;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public List<PhoneDetail> getPhones()
    {
        return phones;
    }

    public void setPhones(List<PhoneDetail> phones)
    {
        this.phones = phones;
    }

    public double getmValue()
    {
        return mValue;
    }

    public void setmValue(double mValue)
    {
        this.mValue = mValue;
    }

    public int getNumbEmp()
    {
        return numbEmp;
    }

    public void setNumbEmp(int numbEmp)
    {
        this.numbEmp = numbEmp;
    }

}
