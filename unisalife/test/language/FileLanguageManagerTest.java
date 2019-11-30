/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import java.util.Iterator;
import java.util.Set;
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
    public void testGetLanguageManager() {
        System.out.println("getLanguageManager - only one language");
        FileLanguageManager result = FileLanguageManager.getLanguageManager();
        assertNotEquals("errore creazione del componente",null,result);
        Set<String> s = result.getAvailableLanguages();
        Iterator<String> iter = s.iterator();
        String lang = iter.next();
        assertEquals("errore lingua","eng", lang);
    }

    /**
     * Test of getAvailableLanguages method, of class FileLanguageManager.
     */
    @Ignore
    @Test
    public void testGetAvailableLanguages() {
        System.out.println("getAvailableLanguages");
        FileLanguageManager instance = null;
        Set<String> expResult = null;
        Set<String> result = instance.getAvailableLanguages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
