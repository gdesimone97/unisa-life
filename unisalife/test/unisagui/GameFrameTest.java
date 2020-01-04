/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import java.awt.event.KeyEvent;
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
     * Test of setCareer method, of class GameFrame.
     
    @Test
    public void testSetCareer() {
        System.out.println("setCareer");
        GameFrame instance = null;
        instance.setCareer();
        if(true)
            System.out.println(" TESTING SET CAREER  OK");
        else
            System.out.println("TESTING SET CAREER  FAILED");
        
    }
    */

    /**
     * Test of initializingTable method, of class GameFrame.
     
    @Test
    public void testInitializingTable() {
        System.out.println("initializingTable");
        GameFrame instance = null;
        instance.initializingTable();
        
    }
    */

    
    
    /**
     * Test of main method, of class GameFrame.
     
    @Test
    public void testMain() {
        String[] args = null;
        GameFrame.main(args);
        
    }
    * */
    

    
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
        JTextField inputTest;
        GameFrame frame= GameFrame.getInstance();
        JDialog temp=frame.KeyboardSettingsDialog;
     
        frame.setKeyBoard();
        sleep(1000);
        temp.setVisible(true);
        inputTest = (JTextField) TestUtils.getChildNamed(temp,"moveup");
        assertNotNull("Cant'access the JTextField component", inputTest);
        
        assertEquals(moveuptest,inputTest.getText()); 
        
    }
    
}
