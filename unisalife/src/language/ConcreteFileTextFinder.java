/**
 * Sprint #1
 * User Story: (13) As a player I would like more languages
 * Task: (7) Structure of the language package
 * Team Members: Alfonso De Masi, Giuseppe De Simone
*/
package language;

import language.exceptions.StringNotFoundException;
import language.exceptions.FileNotSetException;
import java.util.List;

/**
 *
 * @author alfon
 */
public class ConcreteFileTextFinder extends FileTextFinder{

    private static ConcreteFileTextFinder istance = new ConcreteFileTextFinder();
    
    public static synchronized ConcreteFileTextFinder getConcreteFileTextFinder() throws FileNotSetException{
        if(FileTextFinder.getFileName() == null){
            throw new FileNotSetException();
        }
        return istance;
    }
    
    @Override
    public List<String> getString() throws StringNotFoundException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
