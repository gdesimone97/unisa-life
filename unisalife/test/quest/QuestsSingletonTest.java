/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quest;

import exam.question.Materia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quests.ItemDef;
import quests.QuestsManagerSingleton;
import quests.quest.Quest;
import quests.quest.QuestsSingleton;

/**
 *
 * @author cmarino
 */
public class QuestsSingletonTest {
    
    public QuestsSingletonTest() {
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
    public void getQuestTest(){
        /*
        Map<EnumItem ,Boolean> itemMap = new HashMap<>();
        itemMap.put(EnumItem.MN1, true);
        SubjectEnum subj = SubjectEnum.MATH;
        Quest q1 = new Quest(SubjectEnum.MATH, itemMap);
        */
        QuestsManagerSingleton qms = QuestsManagerSingleton.getInstance();
        QuestsSingleton qs = QuestsSingleton.getInstance();
        Quest q = qs.getQuest().get(Materia.valueOf(Materia.fisica.toString()));
        assertEquals(q,qs.getQuest().get(Materia.valueOf(Materia.fisica.toString())));
        
    }


}
