/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving.exceptions;

/**
 * Report to developer that something has gone wrong in the saving operation
 *
 * @author Giuseppe De Simone
 */
public class SavingException extends RuntimeException {

    /**
     * Creates a new instance of <code>SavingException</code> without detail
     * message.
     */
    public SavingException() {
    }

    /**
     * Constructs an instance of <code>SavingException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SavingException(String msg) {
        super(msg);
    }
}
