/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Bloch
 */
public class PhoneNumberAlreadyExistsException extends RuntimeException {

    /**
     * Creates a new instance of <code>PhoneNumberAlreadyExistsException</code>
     * without detail message.
     */
    public PhoneNumberAlreadyExistsException()
    {
    }

    /**
     * Constructs an instance of <code>PhoneNumberAlreadyExistsException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public PhoneNumberAlreadyExistsException(String msg)
    {
        super(msg);
    }
}
