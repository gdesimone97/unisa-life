/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.exceptions;

/**
 * Report to developer that something has gone wrong in the creation of
 * FileTextManager instamce
 *
 * @author Giuseppe De Simone
 */
public class FileTextManagerException extends Exception {

    /**
     * Creates a new instance of <code>FileTextManagerNotCreatedException</code>
     * without detail message.
     */
    public FileTextManagerException() {
    }

    /**
     * Constructs an instance of <code>FileTextManagerNotCreatedException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FileTextManagerException(String msg) {
        super(msg);
    }
}
