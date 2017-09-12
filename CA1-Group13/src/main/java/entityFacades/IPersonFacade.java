
package entityFacades;

import entity.Person;
import java.util.List;

public interface IPersonFacade {
    
    public Person getPerson(int id);
    public Person addPerson(Person person);
    public Person editPerson(Person person);
    public Person deletePerson(Person person);
    public Person deletePerson(int id);
    public List<Person> getPersons();
    public List<Person> getPersons(String zipCode);
    public Person getPerson(long phoneNumber);
    
    
}
