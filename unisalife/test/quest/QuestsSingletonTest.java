/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import quests.quest.Quest;

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
        
        
        Map<EnumItem ,Boolean> itemMap = new HashMap<>();
        itemMap.put(EnumItem.MN2, true);
        SubjectEnum subj = SubjectEnum.PHYS;
        Quest q2 = new Quest(SubjectEnum.PHYS, itemMap);
        
        
        QuestsSingleton qs = QuestsSingleton.getInstance();
        Quest q = qs.getQuest(SubjectEnum.PHYS.toString());
        assertEquals(q,q2);
        
    }

    
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
        
        
        List<Quest> aqs = qs.getActiveQuests(); 
        assertEquals(aqs.size(),3);
        q = qs.getQuest(SubjectEnum.MATH.toString());
        q.finish();
        aqs = qs.getActiveQuests();
        assertEquals(aqs.size(),2);
        
        
        
        
    }


}
