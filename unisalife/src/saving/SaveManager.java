/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving;

import saving.exceptions.*;
import exam.booklet.BookletSingleton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import language.adapters.TextManagerAdapter;

/**
 *
 * @author
 */
public class SaveManager {

    private static SaveManager instance = new SaveManager();
    private List<Saveable> saveableComponents = new ArrayList<>();
    private Map<String, Serializable> savingItems = new HashMap<>();
    private final String PATH = "../save/save.game"; // path per la cartella di salvataggio

    public static SaveManager getSaveManager() {
        return instance;
    }

    private SaveManager() { // da completare quando abbiamo tutte le classi da salvare
        saveableComponents.add(BookletSingleton.getInstance());
        saveableComponents.add(TextManagerAdapter.getTextManagerAdpter());
    }

    public boolean isSaveSomething() {
        File f = new File(PATH);
        if (!f.exists() || f.getTotalSpace() == 0) {
            return false;
        }
        return true;
    }

    public void save() throws SavingException {
        for (Saveable sav : saveableComponents) {
            Serializable itemToSave = sav.save();
            savingItems.put(sav.getClass().getName(), itemToSave);
        }
        try (FileOutputStream fileout = new FileOutputStream(new File(PATH));
                ObjectOutputStream s = new ObjectOutputStream(fileout);) {
            s.writeObject(savingItems);

        } catch (IOException ex) {
            throw new SavingException();
        }
    }

    public void load() throws LoadingException {
        try (FileInputStream filein = new FileInputStream(new File(PATH));
                ObjectInputStream s = new ObjectInputStream(filein);
                ){
            Object obj = s.readObject();
            savingItems = (Map)obj;
            for(Serializable itemToLoad:savingItems.keySet()){

            }
        }
        catch(Exception ex){
            throw new LoadingException();
        }
    }
}
