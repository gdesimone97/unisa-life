/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.exceptions;

/**
 * Report to developer that something has gone wrong in creation of
 * FileLanguageManager instance
 *
 * @author Giuseppe De Simone
 */
public class FileLanguageManagerException extends Exception {

    /**
     * Creates a new instance of
     * <code>NoFileLanguageManagerCreatedException</code> without detail
     * message.
     */
    public FileLanguageManagerException() {
    }

    /**
     * Constructs an instance of
     * <code>NoFileLanguageManagerCreatedException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FileLanguageManagerException(String msg) {
        super(msg);
    }
}
