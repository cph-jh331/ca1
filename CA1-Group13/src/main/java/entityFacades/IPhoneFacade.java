package entityFacades;

import entity.Phone;
import java.util.List;

public interface IPhoneFacade {
    
    public Phone getPhone(String phoneNumber);
    public Phone addPhone(Phone phone);
    public Phone editPhone(Phone phone);
    public Phone deletePhone(Phone phone);
    public Phone deletePhone(String phoneNumber);
    public List<Phone> getPhones();

}
