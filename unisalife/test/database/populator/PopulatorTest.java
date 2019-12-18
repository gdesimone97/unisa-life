/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator;

import database.DatabaseManager;
import database.Database;
import database.FileNotSetException;
import exam.booklet.Subject;
import game.GameObjects.Item;
import game.GameObjects.Professor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.dizitart.no2.Nitrite;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import quests.quest.Quest;
import org.junit.Before;
/**
 *
 * @author cmarino
 */
public class PopulatorTest {
    
    public PopulatorTest() {
    }
    
    
    @Before
    public void setupClass() throws Exception {
        DatabaseManager.getDatabaseManager().getDatabase().clear();
        DatabaseManager.getDatabaseManager().getDatabase().close();
    }

    
    
    /**
     * Test of populate method, of class Populator.
     */
    @Test
    public void testPopulateScript() throws Exception {
        
        
            System.out.println("populate");
            Populator p = new Populator("..//unisalife/src/database/populator/data.txt");
            p.populate();
            Nitrite ndb = Database.getInstance().getNitriteDatabase();
            List<Class> classList = Arrays.asList(new Class[]{Subject.class, Quest.class, Professor.class, Item.class});
            
            for( Class c : classList )
                assertTrue(ndb.hasRepository(c));
        
        
     
 
    }
    
    
    
}
