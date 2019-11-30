/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.exceptions;

/**
 *
 * @author desio
 */
public class LanguageSelectedNotAvailableException extends Exception {

    /**
     * Creates a new instance of
     * <code>LanguageSelectedNotAvailableException</code> without detail
     * message.
     */
    public LanguageSelectedNotAvailableException() {
    }

    /**
     * Constructs an instance of
     * <code>LanguageSelectedNotAvailableException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LanguageSelectedNotAvailableException(String msg) {
        super(msg);
    }
}
