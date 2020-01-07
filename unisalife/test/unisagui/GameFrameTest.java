/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import gameSystem.GameManager;
import gameSystem.keySettings.SettingsManager;
import static java.lang.Thread.sleep;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.*;

/**
 *
 * @author Davide
 * @author Virginia
 * Test for some classes and component of the gui
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
        GameManager.getInstance().initGame();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class GameFrame.
     */
    @Test
    public void testGetInstance() {
        GameFrame expResult = null;
        GameFrame result = GameFrame.getInstance();
        
        assertNotEquals(expResult,result);
        
    }

    

    /**
     * Test of initializingTable method, of class GameFrame.
     
    @Test
    public void testInitializingTable() {
        System.out.println("initializingTable");
        GameFrame instance = null;
        instance.initializingTable();
        
    }
    */

    
    
    
     
    @Test
    public void testMain() {
        String[] args = null;
        GameFrame.main(args);
        
    }
    
    

    
    @Test
    public void testAvatarName() throws InterruptedException {
        GameFrame frame;
        JDialog temp;
        String result;
        JTextField inputTest;

        frame = GameFrame.getInstance();
        frame.setVisible(true);
        temp = frame.AvatarChooserDialog;
        temp.setVisible(true);

        inputTest = (JTextField) TestUtils.getChildNamed(temp, "avatar");
        assertNotNull("Cant'access the JTextField component", inputTest);

        inputTest.setText("davide");
        sleep(1000);
        inputTest.postActionEvent();
        sleep(1000);
    

        result = "davide";
        
        assertEquals(result,inputTest.getText());
    }
    
    @Test
    public void testnewGameButton() throws InterruptedException {
        GameFrame frame;
        JDialog temp;
        boolean result;
        JButton button;

        frame = GameFrame.getInstance();
        frame.setVisible(true);
        temp = frame.MainMenuDialog;
        temp.setVisible(true);

        button = (JButton) TestUtils.getChildNamed(temp, "new_game");
        assertNotNull("Cant'access the JButton component", button);

        button.setEnabled(false);
        sleep(1000);

        result = false;
        assertEquals(result,button.isEnabled());
    }
    
    @Test
    public void testKeyboard() throws Exception {
        
        String moveuptest= "W";
        String movedowntest= "A";
        String movelefttest= "S";
        String moverighttest= "D";
        String interacttest= "O";
        String pausetest= "P";
        String maptest= "Q";
        GameFrame frame= GameFrame.getInstance();
        
        JTextField inputTest;
        JDialog temp=frame.KeyboardSettingsDialog;
        temp.setVisible(true);
     
        sleep(1000);
        inputTest = (JTextField) TestUtils.getChildNamed(temp,"moveup");
        assertNotNull("Cant'access the JTextField component", inputTest);
        inputTest.setText("W");
        assertEquals(moveuptest,inputTest.getText()); 
        
        inputTest = (JTextField) TestUtils.getChildNamed(temp,"movedown");
        assertNotNull("Cant'access the JTextField component", inputTest);
        inputTest.setText("A");
        assertEquals(movedowntest,inputTest.getText()); 
        
        inputTest = (JTextField) TestUtils.getChildNamed(temp,"moveleft");
        assertNotNull("Cant'access the JTextField component", inputTest);
        inputTest.setText("S");
        assertEquals(movelefttest,inputTest.getText()); 
        
        inputTest = (JTextField) TestUtils.getChildNamed(temp,"moveright");
        assertNotNull("Cant'access the JTextField component", inputTest);
        inputTest.setText("D");
        assertEquals(moverighttest,inputTest.getText());
        
        inputTest = (JTextField) TestUtils.getChildNamed(temp,"interact");
        assertNotNull("Cant'access the JTextField component", inputTest);
        inputTest.setText("O");
        assertEquals(interacttest,inputTest.getText());
        
        inputTest = (JTextField) TestUtils.getChildNamed(temp,"pause");
        assertNotNull("Cant'access the JTextField component", inputTest);
        inputTest.setText("P");
        assertEquals(pausetest,inputTest.getText());
        
        inputTest = (JTextField) TestUtils.getChildNamed(temp,"map");
        assertNotNull("Cant'access the JTextField component", inputTest);
        inputTest.setText("Q");
        assertEquals(maptest,inputTest.getText());
        
    }
    
}
