/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quest;

import exam.question.Materia;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import quests.ItemDef;
import quests.mediator.Message;
import quests.quest.Quest;

/**
 *
 * @author cmarino
 */
public class QuestTest {
    
    public QuestTest() {
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /*
    @Test
    public void getIDTest(){
        Map<ItemDef ,Boolean> itemMap = new HashMap<>();
        Materia subj = Materia.matematica;
        Quest q = new Quest(subj.toString());
    }
    
    
    @Test
    public void toStringTest(){
        Map<ItemDef ,Boolean> itemMap = new HashMap<>();
        Materia subj = Materia.matematica;
        Quest q = new Quest(Materia.matematica.toString());
        String desc = q.toString();
        assertNotNull(desc);
        assertEquals(desc,"matematica");
    }
    */
    
    
    @Test 
    public void isAvailableTest(){
        
        Materia subj = Materia.matematica;
        Quest q = new Quest(subj.toString());
        q.setItemsExam(ItemDef.appuntidimatematica1.toString());
        assertFalse(q.isAvailable());
        q.receive(new Message(ItemDef.appuntidimatematica1.toString(),true));
        assertTrue(q.isAvailable());
    }
    
    @Test
    public void setAvailableTest(){
        Materia subj = Materia.matematica;
        Quest q = new Quest(subj.toString());
        q.setItemsExam(ItemDef.appuntidimatematica1.toString());
        q.setItemsExam(ItemDef.appuntidimatematica2.toString());
        assertFalse(q.isAvailable());
        q.send(new Message(ItemDef.appuntidimatematica1.toString(),true));
        q.send(new Message(ItemDef.appuntidimatematica2.toString(),true));
        assertTrue(q.isAvailable());
    }
    
    @Test
    public void isDoneTest(){

        Map<ItemDef ,Boolean> itemMap = new HashMap<>();
        Quest q = new Quest(Materia.matematica.toString());
        assertFalse(q.isDone());
        q.finish();
        assertTrue(q.isDone());

    }
    
    @Test
    public void finishTest(){
            Quest q = new Quest(Materia.matematica.toString());
            q.finish();
            assertTrue(q.isDone());
            
        
    }
}
