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
public class NoPhoneNumbersFoundException extends RuntimeException {

    /**
     * Creates a new instance of <code>NoPhoneNumbersFoundException</code>
     * without detail message.
     */
    public NoPhoneNumbersFoundException()
    {
    }

    /**
     * Constructs an instance of <code>NoPhoneNumbersFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoPhoneNumbersFoundException(String msg)
    {
        super(msg);
    }
}
