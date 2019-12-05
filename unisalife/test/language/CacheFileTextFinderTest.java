/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package language;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
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

    abstract class GenericAbstract implements Information {
    };

    class GenericClass extends GenericAbstract {

        private String info = "ID";
        private Boolean av = true;

        public GenericClass(String info) {
            this.info = info;
        }

        @Override
        public String getInfo() {
            return this.info;
        }

        @Override
        public Boolean isAvailable() {
            return this.av;
        }

        public void setAvailable(Boolean b) {
            this.av = b;
        }

    }

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
        System.out.println("getString with One String");
        GenericClass instance = new GenericClass("TestSingleInfo");
        FileTextFinder finder = FileTextFinder.getFileTextFinder("testfile.xml");
        List<String> resultList = finder.getString(instance);
        int expResult = 1;
        assertEquals(expResult, resultList.size());
    }

    /**
     * Test multiple String
     */
    @Test
    public void testGetStringMultiple() throws Exception {
        System.out.println("getString with multiple Strings");
        GenericClass instance = new GenericClass("TestMultipleInfo");
        FileTextFinder finder = FileTextFinder.getFileTextFinder("testfile.xml");
        List<String> resultList = finder.getString(instance);
        assertTrue(resultList.size()>1);
    }
    
    /**
     * Test String Exception
     */
    @Test(expected = TextFinderException.class)
    public void testGetStringException() throws Exception {
        System.out.println("getString Exception");
        GenericClass instance = new GenericClass("TestWrongInfo");

        FileTextFinder finder = FileTextFinder.getFileTextFinder("testfile.xml");
        List<String> result = finder.getString(instance);
    }

    /**
     * Test of cleanCache method, of class CacheFileTextFinder.
     */
    @Test
    public void testCleanCache() throws TextFinderException, FileNotSetException, InvalidFileNameException, StringNotFoundException {
        System.out.println("cleanCache");
        FileTextFinder finder = FileTextFinder.getFileTextFinder("testfile.xml");
        GenericClass instance = new GenericClass("TestInfo");
        List<String> resultList = finder.getString(instance);
        FileTextFinder.setFileName("replacement.xml");
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
        FileTextFinder finder = FileTextFinder.getFileTextFinder("testfile.xml");
        GenericClass instance = new GenericClass("TestInfo");
        List<String> resultList = finder.getString(instance);
        int result = ((CacheFileTextFinder) finder).size();
        int expectedResult = 1;
        assertEquals(result, expectedResult);
    }

    /**
     * Test of fullfillment in cache
     */
    /*
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
     */
    /**
     * Test Warning message
     */
    @Test
    public void testWarning() throws Exception {
        System.out.println("getString");
        GenericClass instance = new GenericClass("TestInfo");
        instance.setAvailable(Boolean.FALSE);
        FileTextFinder finder = FileTextFinder.getFileTextFinder("testfile.xml");
        List<String> resultList = finder.getString(instance);
        String expResult = "Operation not allowed";
        assertTrue(expResult.equals(resultList.get(0)));
    }
}
