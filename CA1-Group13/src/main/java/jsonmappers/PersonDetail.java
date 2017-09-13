package jsonmappers;

import entity.Address;
import entity.CityInfo;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;

public class PersonDetail {

    private String firstName;
    private String lastName;
    private String email;
    private List<PhoneDetail> phones;
    private String street;
    private String additionalInfo;
    private String zipcode;
    private String city;

    public PersonDetail()
    {
    }

    public PersonDetail(String firstName, String lastName, String email, List<PhoneDetail> phones, String street, String additionalInfo, String zipcode, String city)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phones = phones;
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.zipcode = zipcode;
        this.city = city;
    }

    public PersonDetail(Person p)
    {
        List<Phone> phoneList = p.getPhones();
        List<PhoneDetail> pdList = new ArrayList<>();
        if (!phoneList.isEmpty())
        {
            for (Phone phone : phoneList)
            {
                pdList.add(new PhoneDetail(phone.getNumber(), phone.getDescription()));
            }
        } else
        {
            pdList = null;
        }

        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.email = p.getEmail();
        this.phones = pdList;
        this.additionalInfo = p.getAddress().getAdditional();
        this.city = p.getAddress().getCityInfo().getCity();
        this.street = p.getAddress().getStreet();
        this.zipcode = p.getAddress().getCityInfo().getZipCode();
    }

    public String getFirstName()
    {
        return firstName;
    }

    public Person convertToPerson()
    {
        CityInfo ci = new CityInfo(this.zipcode, this.city);
        Address Address = new Address(this.street, this.additionalInfo, ci);

        Person p = new Person(this.firstName, this.lastName, this.email, Address);
        for (PhoneDetail pd : this.phones)
        {
            p.addPhone(pd.getPhoneNumber(), pd.getDescription());
        }
        return p;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<PhoneDetail> getPhones()
    {
        return phones;
    }

    public void setPhones(List<PhoneDetail> phones)
    {
        this.phones = phones;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

}
