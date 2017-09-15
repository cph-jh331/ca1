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
public class PhoneNumberNotANumberException extends RuntimeException {

    public PhoneNumberNotANumberException()
    {
    }

    public PhoneNumberNotANumberException(String msg)
    {
        super(msg);
    }
}
