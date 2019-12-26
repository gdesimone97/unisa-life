/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving;

import character.StatusManager;
import saving.exceptions.*;
import exam.booklet.Booklet;
import game.GameObjects.GameInventory;
import game.GameObjects.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import quests.quest.Quests;

/**
 *
 * @author Giuseppe De Simone
 * @author Simone Serritiello
 */
public class SaveManager {

    private static final SaveManager instance = new SaveManager();
    private List<Saveable> saveableComponents = new ArrayList<>();
    private Map<String, Serializable> savingItems = new HashMap<>();
    private final String PATH = "../save/save.game"; // path per la cartella di salvataggio
    private final String PATH_LANG = "../save/conf.game";

    public synchronized static SaveManager getSaveManager() {
        return instance;
    }

    protected SaveManager() { // da completare quando abbiamo tutte le classi da salvare
        saveableComponents.add(Booklet.getInstance());
        saveableComponents.add(Player.getIstance());
        saveableComponents.add(StatusManager.getInstance());
        saveableComponents.add(GameInventory.getInstance());
        saveableComponents.add(Quests.getInstance());
    }

    public boolean isSaveSomething() {
        return isSaveSomething(PATH);
    }

    private boolean isSaveSomething(String path) {
        File f = new File(path);
        if (!f.exists()) {
            return false;
        }
        return true;
    }
    
    public void loadLang() throws LoadingException{
        getLang();
    }
    
    public void saveLang() throws SavingException {
        TextManagerAdapter textManager = TextManagerAdapter.getTextManagerAdpter();
        try (FileOutputStream fileout = new FileOutputStream(new File(PATH_LANG));
                ObjectOutputStream out = new ObjectOutputStream(fileout);) {
            String lang = textManager.getCurrentLanguage();
            out.writeUTF(lang);
        } catch (IOException ex) {
            throw new SavingException("Errore salvataggio lingua corrente");
        }

    }

    protected String getLang() throws LoadingException {
        if (!isSaveSomething(PATH_LANG)) {
            return "";
        }
        try (FileInputStream filein = new FileInputStream(new File(PATH_LANG));
                ObjectInputStream in = new ObjectInputStream(filein);) {
            String readLang = in.readUTF();
            return readLang;
        } catch (IOException ex) {
            throw new LoadingException("Errore caricamento lingua precedentemente salvata");
        }
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
            ex.printStackTrace();
            throw new SavingException();
        }
    }

    public void load() throws LoadingException {
        try (FileInputStream filein = new FileInputStream(new File(PATH));
                ObjectInputStream s = new ObjectInputStream(filein);) {
            Object obj = s.readObject();
            savingItems = (Map) obj;
            for (Saveable sav : saveableComponents) {
                Serializable item = savingItems.get(sav.getClass().getName());
                sav.load(item);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new LoadingException();
        }
    }
}
