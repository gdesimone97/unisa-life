/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.populator;


import database.Database;
import exam.booklet.Subject;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author cmarino
 */
public class PopulatorTest {
    
    public PopulatorTest() {
    }
    
    

    /**
     * Test of populate method, of class Populator.
     */
    @Test
    public void testPopulateScript() throws Exception {
        System.out.println("populate");
        Populator p = new Populator("..//unisalife/src/database/populator/data.txt");
        p.populate();
        System.out.println("Population finished");
        for(String o: Database.getInstance().getNitriteDatabase().listRepositories())
            System.out.println(o);
        
        assertTrue(Database.getInstance().getNitriteDatabase().hasRepository(Subject.class));

    }
    
    
    
}
