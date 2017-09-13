/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import javax.ws.rs.ext.Provider;

/**
 *
 * @author Bloch
 */

public class EmailAlreadyExistsException extends RuntimeException {

    /**
     * Creates a new instance of <code>EmailAlreadyExistsException</code>
     * without detail message.
     */
    public EmailAlreadyExistsException()
    {
    }

    /**
     * Constructs an instance of <code>EmailAlreadyExistsException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public EmailAlreadyExistsException(String msg)
    {
        super(msg);
    }
}
