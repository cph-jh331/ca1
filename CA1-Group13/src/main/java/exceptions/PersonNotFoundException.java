package exceptions;

import javax.ws.rs.ext.Provider;


public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException()
    {
    }

    public PersonNotFoundException(String msg)
    {
        super(msg);
    }
}
