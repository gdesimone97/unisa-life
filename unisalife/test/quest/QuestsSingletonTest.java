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
        
        
        Quest q_phy = new Quest(Materia.matematica.toString());
        q_phy.setItemsExam(ItemDef.calcolatrice.toString());
        
        QuestsSingleton qs = QuestsSingleton.getInstance();
        Quest q = qs.getQuest().get(Materia.valueOf(Materia.fisica.toString()));
        assertEquals(q,q_phy);
        
    }

    @Test
    public void getActiveQuestsTest(){
        
        /*
        Map<EnumItem ,Boolean> itemMap1 = new HashMap<>();
        itemMap.put(EnumItem.MN1, true);
        SubjectEnum subj = SubjectEnum.MATH;
        Quest q = new Quest(SubjectEnum.MATH, itemMap1);
        
        Map<EnumItem ,Boolean> itemMap2 = new HashMap<>();
        itemMap.put(EnumItem.MN2, true);
        SubjectEnum subj = SubjectEnum.PHYS;
        Quest q2 = new Quest(SubjectEnum.PHYS, itemMap2);
        
        
        */
        
        QuestsSingleton qs = QuestsSingleton.getInstance();
        
        
        ArrayList<Quest> aqs = qs.getAvailableQuest(); 
        assertEquals(aqs.size(),1);
        Quest q = qs.getQuest().get(new Quest(Materia.fisica.toString()));
        q.finish();
        aqs = qs.getAvailableQuest();
        assertEquals(aqs.size(),0);
        
        
        
        
    }


}
