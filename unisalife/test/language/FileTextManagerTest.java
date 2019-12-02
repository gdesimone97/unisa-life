/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import language.exceptions.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giuseppe De Simone
 */
public class FileTextManagerTest {
    
    public FileTextManagerTest() {
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
     * Test of getFileTextManager method, of class FileTextManager.
     */
    @Test
    public void testGetFileTextManager() throws Exception {
        System.out.println("getFileTextManager");
        FileTextManager expResult = null;
        FileTextManager result = FileTextManager.getFileTextManager();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getAvailableLanguages method, of class FileTextManager.
     */
    @Test
    public void testGetAvailableLanguages() throws IOException, FileTextManagerException {
        System.out.println("getAvailableLanguages");
        FileTextManager instance = FileTextManager.getFileTextManager();
        Set<String> expResult = ReadDirectory.readDirectory();
        Set<String> result = instance.getAvailableLanguages();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCurrentLanguage method, of class FileTextManager.
     */
    @Test
    public void testGetCurrentLanguage() throws FileTextManagerException, FileLanguageManagerException {
        System.out.println("getCurrentLanguage");
        FileTextManager instance = FileTextManager.getFileTextManager();
        FileLanguageManager languageManager = FileLanguageManager.getLanguageManager();
        String expResult = languageManager.getCurrentLanguage();
        String result = instance.getCurrentLanguage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLanguage method, of class FileTextManager.
     */
    @Test
    public void testSetLanguage() throws Exception {
        System.out.println("setLanguage");
        FileTextManager instance = FileTextManager.getFileTextManager();
        FileLanguageManager languageManager = FileLanguageManager.getLanguageManager();
        String lang = "eng";
        instance.setLanguage(lang);
        String result = languageManager.getCurrentLanguage();
        assertEquals(result, lang);
    }
    
    /**
     * Test of setLanguage method, of class FileTextManager.
     */
    @Test(expected = LanguageSelectedNotAvailableException.class)
    public void testSetLanguageException() throws Exception {
        System.out.println("setLanguage");
        FileTextManager instance = FileTextManager.getFileTextManager();
        FileLanguageManager languageManager = FileLanguageManager.getLanguageManager();
        String lang = "test";
        instance.setLanguage(lang);
        String result = languageManager.getCurrentLanguage();
        assertEquals(result, lang);
    }

    /**
     * Test of getString method, of class FileTextManager.
     */
    @Test
    public void testGetString() throws Exception {
        System.out.println("getString");
        FileTextManager instance = null;
        List<String> expResult = null;
        List<String> result = instance.getString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
