/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.exceptions;

/**
 * Report to develoepr that the language directory is empty
 *
 * @author Giuseppe De Simone
 */
public class NoLanguegesFileFoundException extends Exception {

    /**
     * Creates a new instance of <code>NoLanguegesFileFound</code> without
     * detail message.
     */
    public NoLanguegesFileFoundException() {
    }

    /**
     * Constructs an instance of <code>NoLanguegesFileFound</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoLanguegesFileFoundException(String msg) {
        super(msg);
    }
}
