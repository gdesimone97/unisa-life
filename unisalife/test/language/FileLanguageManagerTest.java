/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import language.exceptions.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Giuseppe De Simone
 */
public class FileLanguageManagerTest {

    public FileLanguageManagerTest() {
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
     * Test of getLanguageManager method, of class FileLanguageManager.
     */
    @Test
    public void testGetLanguageManager() throws FileLanguageManagerException {
        System.out.println("getLanguageManager");
        FileLanguageManager expResult = null;
        FileLanguageManager result = FileLanguageManager.getLanguageManager();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getAvailableLanguages method, of class FileLanguageManager.
     */
    @Test
    public void testGetAvailableLanguages() throws IOException, FileLanguageManagerException {
        System.out.println("getAvailableLanguages");
        FileLanguageManager instance = FileLanguageManager.getLanguageManager();
        Set<String> expResult = ReadDirectory.readDirectory();
        Set<String> result = instance.getAvailableLanguages();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAvailableLanguages method, of class FileLanguageManager.
     */
    @Test(expected = FileLanguageManagerException.class)
    public void testGetAvailableLanguagesException() throws IOException, FileLanguageManagerException {
        System.out.println("getAvailableLanguages - exception");
        FileLanguageManager instance = FileLanguageManager.getLanguageManager();
        Set<String> expResult = ReadDirectory.readDirectory("..//lang");
        Set<String> result = instance.getAvailableLanguages();
        assertEquals(expResult, result);
    }
}
