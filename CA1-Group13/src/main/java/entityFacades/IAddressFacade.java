
package entityFacades;

import entity.Address;
import java.util.List;

public interface IAddressFacade {
    
    public Address getAddress(int id);
    public Address addAddress(Address address);
    public Address editAddress(Address address);
    public Address deleteAddress(Address address);
    public Address deleteAddress(int id);
    public List<Address> getAllAdresses();
}
