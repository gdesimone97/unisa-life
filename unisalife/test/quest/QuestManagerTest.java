/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quest;

import exam.question.Materia;
import java.util.EnumMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quests.ItemDef;
import quests.QuestsManagerSingleton;
import quests.mediator.Message;
import quests.quest.Quest;
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
    public void sendTest(){
        QuestsManagerSingleton qms = QuestsManagerSingleton.getInstance();
        QuestsSingleton quests = QuestsSingleton.getInstance();
        String appunto = ItemDef.appuntidimatematica1.toString();
        EnumMap<Materia, Quest> test = quests.getQuest(); 
        qms.setItem(appunto, Materia.fisica);
        qms.sendMessage(new Message(ItemDef.appuntidimatematica1.toString(), true ), quests.getQuest().get(Materia.fisica));
        assertEquals(quests.getQuest().get(Materia.fisica).isAvailable(), true);
    }
    
}