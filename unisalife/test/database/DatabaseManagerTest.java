/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import exam.booklet.Subject;
import game.GameObjects.Position;
import game.GameObjects.Renderable;
import game.GameResources.Map;
import gameSystem.map.MapManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quests.QuestsManager;
import quests.quest.Quest;

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
        MapManager.getInstance().init();
        QuestsManager.getInstance().init();
        
    }

    @AfterClass
    public static void tearDownClass() throws FileNotSetException {
        DatabaseManager.getDatabaseManager().close();

    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() throws FileNotSetException {
        DatabaseManager.getDatabaseManager().close();

    }

    
    @Test
    public void testGetDatabaseManager() throws Exception {
        System.out.println("getDatabaseManager");
        DatabaseManager expResult = null;
        DatabaseManager result = DatabaseManager.getDatabaseManager();
        assertNotEquals(expResult, result);
    }

     
    //@Test
    public void testGetQuestsFromLevel() throws Exception {
        System.out.println("getQuestsFromLevel");
        int level = 0;
        DatabaseManager instance = DatabaseManager.getDatabaseManager();
        int expResult = 1;
        List<Quest> q = instance.getQuestsFromLevel(level);
        int result = q.size();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetObjectsFromLevel() throws Exception {
        System.out.println("getObjectsFromLevel");
        int level = 0;
        DatabaseManager instance = DatabaseManager.getDatabaseManager();
        int expResult = 3; // two object and a professor
        ConcurrentHashMap<Position, Renderable> objectsFromLevel = instance.getObjectsFromLevel(level)[0];
        assertEquals(expResult, objectsFromLevel.size());
    }

    @Test
    public void testGetSubjects() throws FileNotSetException {
        System.out.println("getSubjects");
        DatabaseManager instance = DatabaseManager.getDatabaseManager();
        int expResult = 10;
        List<Subject> resultlist = instance.getSubjects();
        int result = resultlist.size();
        assertEquals(expResult, result);
    }


    @Test
    public void testGetMaps() {
        try {
            System.out.println("getMaps");
            DatabaseManager instance = DatabaseManager.getDatabaseManager();
            int expRes = 4;
            Map[] maps = instance.getMaps();
            int res = maps.length;
            assertEquals(expRes, res);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
