/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving.exceptions;

/**
 *
 * @author desio
 */
public class LoadingException extends Exception {

    /**
     * Creates a new instance of <code>LoadingException</code> without detail
     * message.
     */
    public LoadingException() {
    }

    /**
     * Constructs an instance of <code>LoadingException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public LoadingException(String msg) {
        super(msg);
    }
}
