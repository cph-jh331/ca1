/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author bloch
 */
public class ZipCodeNotValidException extends RuntimeException {

    
    /**
     * Creates a new instance of <code>zipCodeNotValidException</code> without
     * detail message.
     */
    public ZipCodeNotValidException()
    {
    }

    /**
     * Constructs an instance of <code>zipCodeNotValidException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ZipCodeNotValidException(String msg)
    {
        super(msg);
    }
}
