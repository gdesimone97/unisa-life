/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidGameDataFormatException;
import game.GameResources.Map;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class DatabaseManagerTest {

    public DatabaseManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws FileNotSetException, IOException, FileNotFoundException, InvalidGameDataFormatException {
        DatabaseManager dmb = DatabaseManager.getDatabaseManager();
        Populator p = new Populator("..//unisalife/data.txt");
        p.populate();
    }

    @AfterClass
    public static void tearDownClass() throws FileNotSetException {
        DatabaseManager.getDatabaseManager().getDatabase().clear();
        DatabaseManager.getDatabaseManager().close();

    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() throws FileNotSetException {
        DatabaseManager.getDatabaseManager().getDatabase().clear();
        DatabaseManager.getDatabaseManager().close();

    }

    /*
    @Test
    public void testGetDatabaseManager() throws Exception {
        System.out.println("getDatabaseManager");
        DatabaseManager expResult = null;
        DatabaseManager result = DatabaseManager.getDatabaseManager();
        assertNotEquals(expResult, result);
    }


    @Test
    public void testGetQuestsFromLevel() throws Exception {
        System.out.println("getQuestsFromLevel");
        int level = 1;
        DatabaseManager instance = DatabaseManager.getDatabaseManager();
        int expResult = 3;
        int result = instance.getQuestsFromLevel(level).size();
        assertEquals(expResult, result);
    }


    @Test
    public void testGetObjectsFromLevel() throws Exception {
        System.out.println("getObjectsFromLevel");
        int level = 0;
        DatabaseManager instance = DatabaseManager.getDatabaseManager();
        int expResult = 9;
        int result = instance.getObjectsFromLevel(level).size();
        assertEquals(expResult, result);
    }


    @Test
    public void testGetSubjects() throws FileNotSetException {
        System.out.println("getSubjects");
        DatabaseManager instance = DatabaseManager.getDatabaseManager();
        int expResult = 3;
        int result = instance.getSubjects().size();
        assertEquals(expResult, result);
    }
    

    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Item x = new Item();
        Item y = new Item();
        List<Saveable> elems = new ArrayList<>();
        elems.add(x);
        elems.add(y);
        DatabaseManager instance = DatabaseManager.getDatabaseManager();
        instance.save(elems);
        testLoad();
        testIsSaved();
    }


    //@Test
    public void testLoad() throws FileNotSetException {
        System.out.println("load");
        int expResult = 1;
        DatabaseManager instance = DatabaseManager.getDatabaseManager();
        int result = instance.load().size();
        assertEquals(expResult, result);
    }


    //@Test
    public void testIsSaved() throws FileNotSetException {
        System.out.println("isSaved");
        DatabaseManager instance = DatabaseManager.getDatabaseManager();
        boolean expResult = true;
        boolean result = instance.isSaved();
        assertEquals(expResult, result);
    }

    //@Test
    public void testClose() {
        System.out.println("close");
        DatabaseManager instance = null;
        instance.close();
    }
     */
    @Test
    public void testGetMaps() {
        try {
            System.out.println("getMaps");
            DatabaseManager instance = DatabaseManager.getDatabaseManager();
            int expRes = 1;
            Map[] maps = instance.getMaps();
            int res = maps.length;
            assertEquals(expRes, res);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
