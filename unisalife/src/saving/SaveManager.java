/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving;

import exam.booklet.BookletSingleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import language.TextManager;
/**
 *
 * @author 
 */
public class SaveManager {
    private static SaveManager instance = new SaveManager();
    private List<Saveable> saveableComponents = new ArrayList<>();
    private Map<String,Serializable> savingItems = new HashMap<>();
    private final String PATH = ""; // path per la cartella di salvataggio
    
    public SaveManager() {
        saveableComponents.add(BookletSingleton.getInstance());
    }
    
    public void save(){
        
    }
    
}
