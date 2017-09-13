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
public class NoPersonsAtZipcodeException extends RuntimeException {

    /**
     * Creates a new instance of <code>NoPersonsAtZipcodeException</code>
     * without detail message.
     */
    public NoPersonsAtZipcodeException()
    {
    }

    /**
     * Constructs an instance of <code>NoPersonsAtZipcodeException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoPersonsAtZipcodeException(String msg)
    {
        super(msg);
    }
}
