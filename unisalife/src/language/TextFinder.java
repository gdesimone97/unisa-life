/**
 * Sprint #1
 * User Story: (13) As a player I would like more languages
 * Task: (7) Structure of the language package
 * Team Members: Alfonso De Masi, Giuseppe De Simone
*/
package language;

import language.exceptions.StringNotFoundException;
import java.util.List;

/**
 *
 * @author alfon
 */
interface TextFinder {
    
    /**
     * Method to perform a query and get the messages for that object
     * @return a list of strings
     * @throws StringNotFoundException 
     */
    List<String> getString() throws StringNotFoundException;

}
