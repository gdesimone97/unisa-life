/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.*;
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
    public void testGetAvailableLanguagesOneLangueges() throws NoLanguegesFileFoundException, NoFileLanguageManagerCreatedException {
        System.out.print("getLanguageManager - only one language ");
        FileLanguageManager result = FileLanguageManager.getLanguageManager();
        Set<String> s = result.getAvailableLanguages();
        Iterator<String> iter = s.iterator();
        String lang = iter.next();
        assertEquals("errore lingua", "eng", lang);
    }

    /**
     * Test of getAvailableLanguages method, of class FileLanguageManager.
     */
    /*
    @Ignore
    @Test(expected = NoLanguegesFileFoundException.class)
    public void testGetAvailableLanguagesNoLangueges() throws NoLanguegesFileFoundException, NoFileLanguageManagerCreatedException {
        System.out.print("getAvailableLanguages - test no file in directory ");
        FileLanguageManager result = FileLanguageManager.getLanguageManager();
    }
     */
    /**
     * Test of getLanguageManager method, of class FileLanguageManager.
     */
    @Test
    public void testGetLanguageManager() throws Exception {
        System.out.println("getLanguageManager");
        FileLanguageManager expResult = null;
        FileLanguageManager result = FileLanguageManager.getLanguageManager();
        assertNotEquals(expResult, result);
        fail("Da implementare");
    }

    /**
     * Test of getAvailableLanguages method, of class FileLanguageManager.
     */
    @Test
    public void testGetAvailableLanguages() throws IOException {
        System.out.println("getAvailableLanguages");
        FileLanguageManager instance = null;
        Set<String> expResult = readDirectory();
        Set<String> result = instance.getAvailableLanguages();
        assertEquals(expResult, result);
    }

    private Set<String> readDirectory() throws IOException {
        final String PATH_STRING = "..//lang";
        Path dir = Paths.get(PATH_STRING);
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
        Set<String> files = new HashSet<>();
        for (Path file : stream) {
            files.add(file.getFileName().toString());
        }
        return files;
    }
}
