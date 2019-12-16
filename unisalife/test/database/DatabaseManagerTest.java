/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.Populator;
import database.populator.exceptions.InvalidGameDataFormatException;
import exam.booklet.Subject;
import game.GameObjects.Destination;
import game.GameObjects.GameObject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quests.quest.Quest;
import saving.Saveable;

/**
 *
 * @author alfon
 */
public class DatabaseManagerTest {
    
    public DatabaseManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws FileNotSetException, IOException, FileNotFoundException, InvalidGameDataFormatException {
        DatabaseManager dmb = DatabaseManager.getDatabaseManager();
        Populator p = new Populator("..//unisalife/src/database/populator/data.txt");
        p.populate();
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
     * Test of getDatabaseManager method, of class DatabaseManager.
     */
    @Test
    public void testGetDatabaseManager() throws Exception {
        System.out.println("getDatabaseManager");
        DatabaseManager expResult = null;
        DatabaseManager result = DatabaseManager.getDatabaseManager();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getQuestsFromLevel method, of class DatabaseManager.
     */
    @Test
    public void testGetQuestsFromLevel() throws Exception {
        System.out.println("getQuestsFromLevel");
        int level = 1;
        DatabaseManager instance = DatabaseManager.getDatabaseManager();
        int expResult = 3;
        int result = instance.getQuestsFromLevel(level).size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getObjectsFromLevel method, of class DatabaseManager.
     */
    @Test
    public void testGetObjectsFromLevel() throws Exception {
        System.out.println("getObjectsFromLevel");
        int level = 0;
        DatabaseManager instance = null;
        HashMap<Destination, GameObject> expResult = null;
        HashMap<Destination, GameObject> result = instance.getObjectsFromLevel(level);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubjects method, of class DatabaseManager.
     */
    @Test
    public void testGetSubjects() {
        System.out.println("getSubjects");
        DatabaseManager instance = null;
        List<Subject> expResult = null;
        List<Subject> result = instance.getSubjects();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class DatabaseManager.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        List<Saveable> elems = null;
        DatabaseManager instance = null;
        instance.save(elems);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class DatabaseManager.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        DatabaseManager instance = null;
        List<Saveable> expResult = null;
        List<Saveable> result = instance.load();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSaved method, of class DatabaseManager.
     */
    @Test
    public void testIsSaved() {
        System.out.println("isSaved");
        DatabaseManager instance = null;
        boolean expResult = false;
        boolean result = instance.isSaved();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class DatabaseManager.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        DatabaseManager instance = null;
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
