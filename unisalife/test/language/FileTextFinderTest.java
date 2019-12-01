/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import language.exceptions.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alfon
 */
public class FileTextFinderTest {

    public FileTextFinderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getFileName method, of class FileTextFinder.
     */
    @Test
    public void testGetFileNameWithInstance() throws FileNotSetException, InvalidFileNameException {
        System.out.println("getFileName");
        String fileName = "italiano.xml";
        FileTextFinder finder = FileTextFinder.getFileTextFinder(fileName);
        String expResult = "italiano.xml";
        String result = FileTextFinder.getFileName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFileName method, of class FileTextFinder.
     */
    @Test
    public void testGetFileNameWithoutInstance() throws FileNotSetException, InvalidFileNameException {
        System.out.println("getFileName");
        String fileName = "italiano.xml";
        String expResult = "italiano.xml";
        FileTextFinder.setFileName(fileName);
        String result = FileTextFinder.getFileName();
        assertEquals(expResult, result);
    }

    @Test(expected = FileNotSetException.class)
    public void testGetFileNameException() throws FileNotSetException {
        System.out.println("getFileName");
        String expResult = "";
        String result = FileTextFinder.getFileName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFileName method, of class FileTextFinder.
     */
    @Test
    public void testSetFileName() throws Exception {
        System.out.println("setFileName");
        String fileName = "italiano.xml";
        FileTextFinder.setFileName(fileName);
    }
    
    
        /**
     * Test of setFileName method, of class FileTextFinder.
     */
    @Test
    public void testSetFileNameEmpty() throws Exception {
        System.out.println("setFileName");
        String fileName = "italiano.xml";
        FileTextFinder.setFileName(fileName);
    }

    /**
     * Test of computeExpression method, of class FileTextFinder.
     */
    /*
    @Test
    public void testComputeExpression() {
        System.out.println("computeExpression");
        FileTextFinder instance = new FileTextFinderImpl();
        String expResult = "";
        String result = instance.computeExpression();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of getFileTextFinder method, of class FileTextFinder.
     */
    @Test
    public void testGetFileTextFinder() throws Exception {
        System.out.println("getFileTextFinder");
        String fileName = "italiano.xml";
        FileTextFinder expResult = null;
        FileTextFinder result = FileTextFinder.getFileTextFinder(fileName);
        assertNotEquals(expResult, result);

    }

    /**
     * Test of getFileTextFinder method, of class FileTextFinder.
     */
    @Test(expected = FileNotSetException.class)
    public void testGetFileTextFinderException() throws Exception {
        System.out.println("getFileTextFinder");
        String fileName = null;
        FileTextFinder expResult = null;
        FileTextFinder result = FileTextFinder.getFileTextFinder(fileName);
        assertNotEquals(expResult, result);

    }

    /*
    public class FileTextFinderImpl extends FileTextFinder {
    }
     */
}
