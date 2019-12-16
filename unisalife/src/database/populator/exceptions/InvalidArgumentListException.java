/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator.exceptions;

/**
 *
 * @author cmarino
 */
public class InvalidArgumentListException extends RuntimeException {

    public InvalidArgumentListException() {
        super("Actual list of parameters differ in length/format, cannot crate the object");
    }
    
}
