/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quests.ItemDef;
import quests.QuestsManagerSingleton;
import quests.mediator.Message;
import quests.quest.QuestsSingleton;

/**
 *
 * @author cmarino
 */
public class QuestManagerTest {
    
    public QuestManagerTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void forwardTest(){
        
    }
    
    @Test
    public void sendTest(){
        QuestsManagerSingleton qms = QuestsManagerSingleton.getInstance();
        QuestsSingleton q = QuestsSingleton.getInstance();
        qms.sendMessage(new Message(ItemDef.MN1.toString(), True ), q);
        assertEquals(q.getActiveQuests().size(), 1);
    }
    
    @Test
    public void receiveTest(){
        /*
            Not implemented yet.
        */
    }
}
