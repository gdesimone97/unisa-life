/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Davide
 */
public class GameFrameTest {
    
    public GameFrameTest() {
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
     * Test of getInstance method, of class GameFrame.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        GameFrame expResult = null;
        GameFrame result = GameFrame.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCareer method, of class GameFrame.
     */
    @Test
    public void testSetCareer() {
        System.out.println("setCareer");
        GameFrame instance = null;
        instance.setCareer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializingTable method, of class GameFrame.
     */
    @Test
    public void testInitializingTable() {
        System.out.println("initializingTable");
        GameFrame instance = null;
        instance.initializingTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of settingLanguage method, of class GameFrame.
     */
    @Test
    public void testSettingLanguage() throws Exception {
        System.out.println("settingLanguage");
        String s = "";
        GameFrame instance = null;
        instance.settingLanguage(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class GameFrame.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        GameFrame.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
