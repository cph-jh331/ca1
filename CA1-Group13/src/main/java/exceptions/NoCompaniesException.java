package exceptions;

public class NoCompaniesException extends RuntimeException {

    public NoCompaniesException()
    {
    }

    public NoCompaniesException(String msg)
    {
        super(msg);
    }
}
