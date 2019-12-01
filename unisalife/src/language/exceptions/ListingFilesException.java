/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language.exceptions;

/**
 * Report to developer that something is gone wrong in the reading of directory
 * that contain the languages files
 *
 * @author Giuseppe De Simone
 */
public class ListingFilesException extends Exception {

    /**
     * Creates a new instance of <code>ListingFilesException</code> without
     * detail message.
     */
    public ListingFilesException() {
    }

    /**
     * Constructs an instance of <code>ListingFilesException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ListingFilesException(String msg) {
        super(msg);
    }
}
