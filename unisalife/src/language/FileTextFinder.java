/**
 * Sprint #1
 * User Story: (13) As a player I would like more languages
 * Task: (7) Structure of the language package
 * Team Members: Alfonso De Masi, Giuseppe De Simone
 */
package language;

import language.exceptions.InvalidFileNameException;
import language.exceptions.FileNotSetException;

/**
 * Abstract class that implements the mechanisms to search for text in a file
 *
 * @author alfon
 */
public abstract class FileTextFinder implements TextFinder {

    private static String fileName = null;
    
    protected FileTextFinder(){}
    
    /**
     * @return the common current filename setted for that language
     */
    public static String getFileName() throws FileNotSetException {
        if (FileTextFinder.fileName==null)
            throw new FileNotSetException();
        return FileTextFinder.fileName;
    }

    /**
     * Change the common current filename to a new one
     *
     * @param fileName
     * @throws language.exceptions.FileNotSetException
     * @throws language.exceptions.InvalidFileNameException
     */
    public static void setFileName(String fileName) throws FileNotSetException, InvalidFileNameException {
        if(fileName==null || fileName.equals(""))
            throw new InvalidFileNameException();
        FileTextFinder.fileName = fileName;
        CacheFileTextFinder.getCacheFileTextFinder().cleanCache();
    }

    /**
     * Method to construct the right expression to query in the XML file to get
     * the right list of strings for the object that called getString()
     *
     * @return the expression to perform the query
     */
    protected String computeExpression() {
        return null;
    }

    /**
     * Method to get an istance of the FileTextFinder. Because it implements a
     * cache proxy patter, this actually points to a cache text finder
     *
     * @return an istance of the CacheFileTextFinder
     */
    public synchronized static FileTextFinder getFileTextFinder(String fileName) throws FileNotSetException {
        FileTextFinder.setFileName(fileName);
        return CacheFileTextFinder.getCacheFileTextFinder();
    }
    
}
