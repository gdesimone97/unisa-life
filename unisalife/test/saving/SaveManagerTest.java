/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import saving.exceptions.LoadingException;

/**
 *
 * @author Giuseppe De Simone
 */
public class SaveManagerTest {

    public SaveManagerTest() {
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
     * Test of getSaveManager method, of class SaveManager.
     */
    @Test
    public void testGetSaveManager() {
        System.out.println("getSaveManager");
        SaveManager expResult = null;
        SaveManager result = SaveManager.getSaveManager();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of isSaveSomething method, of class SaveManager.
     */
    @Test
    public void testIsSaveSomething() {
        System.out.println("isSaveSomething - c'è file");
        SaveManager instance = SaveManager.getSaveManager();
        boolean expResult = true;
        boolean result = instance.isSaveSomething();
        assertEquals(expResult, result);
    }

    /**
     * Test of isSaveSomething method, of class SaveManager.
     */
    @Test
    public void testIsSaveSomethingNoFIle() {
        System.out.println("isSaveSomething - non c'è file");
        SaveManager instance = SaveManager.getSaveManager();
        boolean expResult = false;
        boolean result = instance.isSaveSomething();
        assertEquals(expResult, result);
    }

    /**
     * Test of load method, of class SaveManager.
     */
    @Test(expected = LoadingException.class)
    public void testLoadEmptyFile() throws Exception {
        System.out.println("load - emty file");
        SaveManager instance = SaveManager.getSaveManager();
        instance.load();
    }

}
