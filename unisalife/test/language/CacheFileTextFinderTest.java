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
    
    private String fileName = "..//test/testfile.xml";
    private String repFileName = "..//test/replacement.xml";
    
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
    public void setUp() throws TextFinderException, FileNotSetException, InvalidFileNameException {

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
        GenericClass instance = new GenericClass("TestSingleInfo");
        FileTextFinder finder = FileTextFinder.getFileTextFinder(fileName);
        List<String> resultList = finder.getString(instance);
        int expResult = 1;
        assertEquals(expResult, resultList.size());
    }

    @Test
    public void testGetStringMultiple() throws Exception {
        System.out.println("getString");
        GenericClass instance = new GenericClass("TestMultipleInfo");
        FileTextFinder finder = FileTextFinder.getFileTextFinder(fileName);
        List<String> resultList = finder.getString(instance);
        assertTrue(resultList.size()>1);
    }

    @Test(expected = TextFinderException.class)
    public void testGetStringException() throws Exception {
        System.out.println("getString");
        GenericClass instance = new GenericClass("TestWrongInfo");

        FileTextFinder finder = FileTextFinder.getFileTextFinder(fileName);
        List<String> result = finder.getString(instance);
    }

    /**
     * Test of cleanCache method, of class CacheFileTextFinder.
     */
    @Test
    public void testCleanCache() throws TextFinderException, FileNotSetException, InvalidFileNameException, StringNotFoundException {
        System.out.println("cleanCache");
        FileTextFinder finder = FileTextFinder.getFileTextFinder(fileName);
        GenericClass instance = new GenericClass("TestInfo");
        List<String> resultList = finder.getString(instance);
        FileTextFinder.setFileName(repFileName);
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
        FileTextFinder finder = FileTextFinder.getFileTextFinder(fileName);
        GenericClass instance = new GenericClass("TestInfo");
        List<String> resultList = finder.getString(instance);
        int result = ((CacheFileTextFinder) finder).size();
        int expectedResult = 1;
        assertEquals(result, expectedResult);
    }


}
