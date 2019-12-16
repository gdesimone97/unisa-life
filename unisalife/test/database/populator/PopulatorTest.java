/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author cmarino
 */
public class PopulatorTest {
    
    public PopulatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of populate method, of class Populator.
     */
    @Test
    public void testPopulateScript() throws Exception {
        System.out.println("populate");
        Populator p = new Populator("data.txt");
        p.populate();
    }
    
}
