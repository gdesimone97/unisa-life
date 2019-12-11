/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quests.*;
import exam.booklet.Subject;
import exam.question.Materia;
import java.util.ArrayList;
import quests.mediator.Message;
import quests.quest.Quest;
import quests.quest.QuestsSingleton;
/**
 *
 * @author cmarino
 */
public class GameInventorySingletonTest {
    
    public GameInventorySingletonTest() {
    }
    
    static QuestsSingleton qs;
    static QuestsManagerSingleton qms;
    
    @BeforeClass
    public static void setUpClass() {
        
        qs = QuestsSingleton.getInstance();
        qms = QuestsManagerSingleton.getInstance();
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getInstance method, of class GameInventorySingleton.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        GameInventorySingleton result = GameInventorySingleton.getInstance();
        assertNotNull( result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of send method, of class GameInventorySingleton.
     */
    @Test
    public void testSend() {
        System.out.println("send");
        Quest q = qs.getQuest().get(Materia.matematica);
        qms.setItem(ItemDef.appuntidimatematica1.toString(), Materia.matematica);
        q.setItemsExam(ItemDef.appuntidimatematica1.toString());
        Message mess = new Message(ItemDef.appuntidimatematica1.toString(),true);
        GameInventorySingleton instance = GameInventorySingleton.getInstance();
        instance.send(mess);
        assertTrue(q.isAvailable());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of receive method, of class GameInventorySingleton.
     */
    
    
    
    @Test
    public void testReceive() {
        System.out.println("receive");
    }
    
    
    /**
     * Test of length method, of class GameInventorySingleton.
     */
    
    
    @Test
    public void testLength() {
        
        System.out.println("length");
        Quest q =qs.getQuest().get(Materia.matematica);
        GameInventorySingleton inv = GameInventorySingleton.getInstance();
        qms.setItem(ItemDef.appuntidimatematica1.toString(), Materia.matematica);
        q.setItemsExam(ItemDef.appuntidimatematica1.toString());
        qms.setItem(ItemDef.appuntidimatematica2.toString(), Materia.matematica);
        q.setItemsExam(ItemDef.appuntidimatematica2.toString());
        qms.setItem(ItemDef.calcolatrice.toString(), Materia.matematica);
        q.setItemsExam(ItemDef.calcolatrice.toString());

        int expResult = 3;
        int result = inv.length();
        assertEquals(expResult, result);
    }

    /**
     * Test of addItem method, of class GameInventorySingleton.
     */
    @Test
    public void testRemoveItem_int() {
        System.out.println("removeItem");
        int pos = 0;
        GameInventorySingleton inv = GameInventorySingleton.getInstance();
        Quest q = qs.getQuest().get(Materia.matematica);
        qms.setItem(ItemDef.appuntidimatematica1.toString(), Materia.matematica);
        q.setItemsExam(ItemDef.appuntidimatematica1.toString());
        ItemDef expResult = ItemDef.appuntidimatematica1; 
        inv.addItem(new Item(300,300,ObjectId.Item,"/Sprites/item.png","Sfera pokè","pok",ItemDef.appuntidimatematica1));
        Item result = inv.removeItem(pos);
        assertEquals(expResult, result.getID());
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testAddItem() throws InterruptedException {
        System.out.println("additem");
        GameInventorySingleton inv = GameInventorySingleton.getInstance();
        Quest q = qs.getQuest().get(Materia.matematica);
        qms.setItem(ItemDef.appuntidimatematica1.toString(), Materia.matematica);
        q.setItemsExam(ItemDef.appuntidimatematica1.toString());
        qms.setItem(ItemDef.appuntidimatematica2.toString(), Materia.matematica);
        q.setItemsExam(ItemDef.appuntidimatematica2.toString());
        qms.setItem(ItemDef.calcolatrice.toString(), Materia.matematica);
        q.setItemsExam(ItemDef.calcolatrice.toString());

        inv.addItem(new Item(300,300,ObjectId.Item,"/Sprites/item.png","Sfera pokè","pok",ItemDef.calcolatrice));
        Thread.sleep(1000);
        inv.addItem(new Item(300,300,ObjectId.Item,"/Sprites/item.png","Sfera pokè","pok",ItemDef.appuntidimatematica1));
        Thread.sleep(1000);
        inv.addItem(new Item(300,300,ObjectId.Item,"/Sprites/item.png","Sfera pokè","pok",ItemDef.appuntidimatematica2));
        Thread.sleep(1000);

        assertEquals(3,inv.length());
    }

    /**
     * Test of removeItem method, of class GameInventorySingleton.
     */
    
    
    
    
    /**
     * Test of removeItem method, of class GameInventorySingleton.
     */
    
    @Test
    public void testRemoveItem_Item() {
        System.out.println("removeItem");
        GameInventorySingleton inv = GameInventorySingleton.getInstance();
        Item i = new Item(300,300,ObjectId.Item,"/Sprites/item.png","Sfera pokè","B",ItemDef.appuntidimatematica1);
        Item expResult = i;
        Item result = inv.removeItem(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of search method, of class GameInventorySingleton.
     */
    
    @Test
    public void testSearch() {
        System.out.println("search");
        String s = "sfe";
        GameInventorySingleton inv = GameInventorySingleton.getInstance();
        int expResult = 3;
        int result = inv.search(s).size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of sortByTaken method, of class GameInventorySingleton.
     */
    
    @Test
    public void testSortByTaken() throws InterruptedException {
        System.out.println("sortByTaken");
        GameInventorySingleton inv = GameInventorySingleton.getInstance();
        inv.sortByTaken();
        Iterator<Item> it = inv.iterator();
        Item i1 = it.next();
        Item i2 = it.next();
        
        assertFalse(i1.getTaken().isAfter(i2.getTaken()));
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of sortByTitle method, of class GameInventorySingleton.
     */
    
    @Test
    public void testSortByTitle() throws InterruptedException {
        
        System.out.println("sortbytitle");
        GameInventorySingleton inv = GameInventorySingleton.getInstance();
        inv.sortByTitle();
        Iterator<Item> it = inv.iterator();
        Item i1 = it.next();
        Item i2 = it.next();
        assertTrue(i1.getTitle().compareTo(i2.getTitle())<=0);
    }

    /**
     * Test of iterator method, of class GameInventorySingleton.
     */
    
    @Test
    public void testIterator() {
        System.out.println("iterator");
        GameInventorySingleton instance = GameInventorySingleton.getInstance();
        Iterator result = instance.iterator();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of save method, of class GameInventorySingleton.
     */
    /*
    @Test
    public void testSave() {
        
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of load method, of class GameInventorySingleton.
     */
    /*
    @Test
    public void testLoad() {
        
        // TODO review the generated test code and remove the default call to fail.
    }
    */
}
