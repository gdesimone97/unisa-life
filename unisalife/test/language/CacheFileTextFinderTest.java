/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import java.util.List;
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
public class CacheFileTextFinderTest {

    public CacheFileTextFinderTest() {
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
     * Test of getCacheFileTextFinder method, of class CacheFileTextFinder.
     */
    @Test
    public void testGetCacheFileTextFinder() throws Exception {
        System.out.println("getCacheFileTextFinder");
        CacheFileTextFinder expResult = null;
        CacheFileTextFinder result = CacheFileTextFinder.getCacheFileTextFinder();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getString method, of class CacheFileTextFinder.
     */
    @Test
    public void testGetStringSingle() throws Exception {
        System.out.println("getString");
        String exp = "first";
        int expResult = 1;
        FileTextFinder finder = FileTextFinder.getFileTextFinder("it.xml");
        List<String> result = finder.getString(exp);
        assertEquals(expResult, result.size());
    }

    @Test
    public void testGetStringMultiple() throws Exception {
        System.out.println("getString");
        String exp = "second";
        FileTextFinder finder = FileTextFinder.getFileTextFinder("it.xml");
        List<String> result = finder.getString(exp);
        assertTrue(result.size() > 1);
    }

    @Test(expected = StringNotFoundException.class)
    public void testGetStringException() throws Exception {
        System.out.println("getString");
        String exp = "third";
        FileTextFinder finder = FileTextFinder.getFileTextFinder("it.xml");
        List<String> result = finder.getString(exp);
    }

    /**
     * Test of cleanCache method, of class CacheFileTextFinder.
     */
    @Test
    public void testCleanCache() throws FileNotSetException, InvalidFileNameException, StringNotFoundException {
        System.out.println("cleanCache");
        FileTextFinder finder = FileTextFinder.getFileTextFinder("it.xml");
        finder.getString("first");
        finder.getString("second");
        FileTextFinder.setFileName("en.xml");
        int result = ((CacheFileTextFinder) finder).size();
        int expectedResult = 0;
        assertEquals(result, expectedResult);
    }

    /**
     * Test of size method, of class CacheFileTextFinder
     */
    @Test
    public void testSize() throws Exception {
        System.out.println("size");
        FileTextFinder finder = FileTextFinder.getFileTextFinder("it.xml");
        finder.getString("first");
        finder.getString("second");
        int result = ((CacheFileTextFinder) finder).size();
        int expectedResult = 2;
        assertEquals(result, expectedResult);
    }

    /**
     * Test of fullfillment in cache
     */
    public void testFill() throws Exception {
        System.out.println("Fill");
        FileTextFinder finder = FileTextFinder.getFileTextFinder("it.xml");
        for (int i = 0; i < 150; i++) {
            finder.getString("#" + i);
        }
        int result = ((CacheFileTextFinder) finder).size();
        int expectedResult = 100;
        assertEquals(result, expectedResult);
    }

}
