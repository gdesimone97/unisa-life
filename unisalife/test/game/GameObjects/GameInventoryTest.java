/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import database.DatabaseManager;
import database.Populator;
import gameSystem.GameManager;
import gameSystem.map.MapManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quests.QuestsManager;
import quests.quest.Quests;

/**
 *
 * @author chsmr
 */
public class GameInventoryTest {
    
    private GameInventory instance;
    
    public GameInventoryTest() {
        
        instance = GameInventory.getInstance();
        instance.init();
        
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        
        DatabaseManager.getDatabaseManager().clearDatabase();
        Populator.main(null);
        GameManager gm = GameManager.getInstance();
        Quests qs = Quests.getInstance();
        QuestsManager qm = QuestsManager.getInstance();
        MapManager mm = MapManager.getInstance();
        mm.init();
        gm.initGame();
        qs.init();
        qm.init();
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
     * Test of getInstance method, of class GameInventory.
     */
    @Test
    public void testGetInstance() {
        
        System.out.println("getInstance");
        GameInventory notExpResult = null;
        GameInventory result = GameInventory.getInstance();
        assertNotEquals(notExpResult, result);
    
    }

    /**
     * Test of addItem method, of class GameInventory.
     */
    
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        
        Item i1 = new Item(new Position(32,32),"...","calcolatrice");
        
        
        int notExpResult = -1;
        int result = instance.addItem(i1);
        assertNotEquals(result,notExpResult);
        testRemoveItem_int();
    }

    /**
     * Test of removeItem method, of class GameInventory.
     */
    
    public void testRemoveItem_int() {
        
        System.out.println("removeItem");
        int pos = 0;
        Item result = instance.removeItem(0);
        assertNotEquals(result, null);
        testLength();
        
    }

    /**
     * Test of length method, of class GameInventory.
     */
    public void testLength() {
        System.out.println("length");
        instance.addItem(new Item(new Position(0,0),"...","calcolatrice"));
        instance.addItem(new Item(new Position(32,32),"...","appunti"));
        int expResult = 2;
        int result = instance.length();
        assertEquals(expResult, result);
        testRemoveItem_Item();
    }  
    
    /**
     * Test of removeItem(item) method, of class GameInventory.
     */
    public void testRemoveItem_Item() {
        
        System.out.println("removeItem");
        Item i = new Item(new Position(1,1),"...","appunti");
        Item expResult = i;
        Item result = instance.removeItem(i);
        assertEquals(expResult, result);
        testSearch();
    }
    
    /**
     * Test of length method, of class GameInventory.
     */
    


    /**
     * Test of addItem method, of class GameInventory.
     */
   

    /**
     * Test of search method, of class GameInventory.
     */
    
    public void testSearch() {
        System.out.println("search");
        instance.addItem(new Item(new Position(0,0),"...","libro"));
        instance.addItem(new Item(new Position(32,32),"...","libretto"));
        instance.addItem(new Item(new Position(32,32),"...","libia"));
        String s = "libr";
        int expResult = 2;
        int result = instance.search(s).size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        testSortByTaken();
    }

    /**
     * Test of sortByTaken method, of class GameInventory.
     */
    
    public void testSortByTaken() {
        System.out.println("sortByTaken");
        
        instance.sortByTaken();
        boolean flag = true;
        // TODO review the generated test code and remove the default call to fail.
        List<Item> list = new ArrayList<>();
        for( Item i : instance )
            list.add(i);
        
        for( int i=0; i<list.size()-1; i++){
            
            Item i1 = list.get(i);
            Item i2 = list.get(i+1);
            System.out.println(i1.getTaken());
            System.out.println(i2.getTaken());
            if( i1.getTaken().compareTo(i2.getTaken()) < 0){
                flag = false;
                break;
            }
        }
        
        assertTrue(flag);
        testSortByTitle();
    }

    /**
     * Test of sortByTitle method, of class GameInventory.
     */
    
    public void testSortByTitle() {
        instance.sortByTitle();
        boolean flag = true;
        // TODO review the generated test code and remove the default call to fail.
        List<Item> list = new ArrayList<>();
        for( Item i : instance )
            list.add(i);
        
        for( int i=0; i<list.size()-1; i++){
            
            Item i1 = list.get(i);
            Item i2 = list.get(i+1);
            
            System.out.println("sbt : " + i1.toString());
            System.out.println(" SBT : " + i2.toString());
            
            if( i1.toString().compareTo(i2.toString()) > 0){
                flag = false;
                break;
            }
        }
        
        assertTrue(flag);
        testIterator();
    }

    /**
     * Test of iterator method, of class GameInventory.
     */
    
    public void testIterator() {
        System.out.println("iterator");
        Iterator notExpResult = null;
        Iterator result = instance.iterator();
        assertNotEquals(notExpResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of save method, of class GameInventory.
     */
    /*
    public void testSave() {
        System.out.println("save");
        GameInventory instance = null;
        Serializable expResult = null;
        Serializable result = instance.save();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class GameInventory.
     */
    /*
    public void testLoad() {
        System.out.println("load");
        Serializable obj = null;
        GameInventory instance = null;
        instance.load(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of init method, of class GameInventory.
     */
    /*
    public void testInit() {
        System.out.println("init");
        GameInventory instance = null;
        instance.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
