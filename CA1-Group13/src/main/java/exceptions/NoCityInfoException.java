package exceptions;

public class NoCityInfoException extends RuntimeException {

    public NoCityInfoException()
    {
    }

    public NoCityInfoException(String msg)
    {
        super(msg);
    }
}
