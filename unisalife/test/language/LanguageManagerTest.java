/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import java.util.Set;
import language.exceptions.FileNotSetException;
import language.exceptions.LanguageSelectedNotAvailableException;
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
public class LanguageManagerTest {

    public LanguageManagerTest() {
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
     * Test of getCurrentLanguage method, of class LanguageManager.
     */
    @Test
    public void testGetCurrentLanguage() throws LanguageSelectedNotAvailableException, FileNotSetException {
        System.out.println("getCurrentLanguage");
        LanguageManager instance = new LanguageManagerImpl();
        instance.setLanguage("test");
        String expResult = "test";
        String result = instance.getCurrentLanguage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLanguage method, of class LanguageManager.
     */
    @Test
    public void testSetLanguage() throws LanguageSelectedNotAvailableException {
        System.out.println("setLanguage");
        String lang = "test";
        LanguageManager instance = new LanguageManagerImpl();
        instance.setLanguage(lang);
        String result = instance.getCurrentLanguage();
        assertEquals(result, lang);
    }

    public class LanguageManagerImpl extends LanguageManager {

        public Set<String> getAvailableLanguages() {
            return null;
        }
    }

}
