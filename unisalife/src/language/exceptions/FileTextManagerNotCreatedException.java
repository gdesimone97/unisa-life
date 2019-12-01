/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.exceptions;

/**
 * Report to developer that something went wrong in the creation of FileTextManager instamce
 * @author Giuseppe De Simone
 */
public class FileTextManagerNotCreatedException extends Exception {

    /**
     * Creates a new instance of <code>FileTextManagerNotCreatedException</code>
     * without detail message.
     */
    public FileTextManagerNotCreatedException() {
    }

    /**
     * Constructs an instance of <code>FileTextManagerNotCreatedException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FileTextManagerNotCreatedException(String msg) {
        super(msg);
    }
}
