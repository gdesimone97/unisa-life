/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import gameSystem.map.MapManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author simon
 */
public class PlayerTest {
    private static Player instance;
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = Player.getIstance();
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
     * Test of setState method, of class Player.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        FaceState s = LeftFaceState.getInstance();
        instance.setState(s);
        assertEquals(instance.face,s);
        
    }


    /**
     * Test of initialize method, of class Player.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        int skin = 3;
        String name = "test";
        Position initialPosition = new Position(30,50);
        instance.initialize(skin, name, initialPosition);
        assertEquals(instance.getX(),30);
        assertEquals(instance.getY(),50);
        assertEquals(instance.getSkin(),3);
        assertEquals(instance.getName(),"test");
    }

    /**
     * Test of getIstance method, of class Player.
     */
    @Test
    public void testGetIstance() {
        System.out.println("getIstance");
        Player result = Player.getIstance();
        assertNotNull(result);
        assertEquals(result,instance);
    }

    /**
     * Test of setX method, of class Player.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        int x = 4;
        instance.setX(x);
        assertEquals(instance.getX(),4);
    }

    /**
     * Test of setX method, of class Player.
     */
    @Test
    public void testTick() {
        System.out.println("tick");
        MapManager.getInstance().init();
        instance.initialize(0, "test", new Position(352,448));
        assertEquals(instance.getX(),352);
        assertEquals(instance.getY(),448);
        instance.tick();
        assertEquals(instance.getX(),352);
        assertEquals(instance.getY(),448);
        instance.setVelX(32);
        instance.tick();
        assertNotEquals(instance.getX(),352);   
    }

    
}
