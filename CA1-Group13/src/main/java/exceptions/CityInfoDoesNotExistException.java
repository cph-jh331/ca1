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
public class CityInfoDoesNotExistException extends RuntimeException {

    /**
     * Creates a new instance of <code>CityInfoDoesNotExistException</code>
     * without detail message.
     */
    public CityInfoDoesNotExistException()
    {
    }

    /**
     * Constructs an instance of <code>CityInfoDoesNotExistException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CityInfoDoesNotExistException(String msg)
    {
        super(msg);
    }
}
