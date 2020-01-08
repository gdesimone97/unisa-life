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
import gameSystem.keySettings.SettingsManager;
import gameSystem.map.MapManager;
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
import quests.QuestsManager;
import quests.quest.Quests;

/**
 * This class provides methods to allow the saving of game's components
 *
 * @author Giuseppe De Simone
 * @author Simone Serritiello
 */
public class SaveManager {

    private static final SaveManager instance = new SaveManager();
    private List<Saveable> saveableComponents = new ArrayList<>();
    private Map<String, Serializable> savingItems = new HashMap<>();
    private final String PATH = "../save/save.game";
    private final String PATH_LANG = "../save/conf.game";
    private final String PATH_KEYS = "../save/keys.game";

    /**
     * Returns the instance of this class
     *
     * @return the SaveManager incance
     */
    public synchronized static SaveManager getSaveManager() {
        return instance;
    }

    /**
     * Builds a SaveManager. Only the class' children can access to this method
     */
    protected SaveManager() {
        saveableComponents.add(GameInventory.getInstance());
        saveableComponents.add(Booklet.getInstance());
        saveableComponents.add(Player.getIstance());
        saveableComponents.add(StatusManager.getInstance());
        saveableComponents.add(MapManager.getInstance());
        saveableComponents.add(Quests.getInstance());
    }

    /**
     * Checks if there is already a previous save
     *
     * @return true if there is a previous save otherwise false
     */
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

    /**
     * This method performs the saving of keys' settings on file
     *
     * @throws SavingException if somethings has gone wrong
     */
    public void saveKeys() throws SavingException {
        Saveable settings = SettingsManager.getSettingsManager();
        try (FileOutputStream fileout = new FileOutputStream(new File(PATH_KEYS));
                ObjectOutputStream out = new ObjectOutputStream(fileout)) {
            out.writeObject(settings.save());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new SavingException("Keyboard keys non salvate");
        }
    }

    /**
     * Loads the keys' settings by file
     *
     * @throws LoadingException if somethig has gone wrong
     */
    public void loadKeys() throws LoadingException {
        Saveable settings = SettingsManager.getSettingsManager();
        try (FileInputStream filein = new FileInputStream(new File(PATH_KEYS));
                ObjectInputStream in = new ObjectInputStream(filein)) {
            Serializable obj = (Serializable) in.readObject();
            settings.load(obj);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new LoadingException("Keyboard keys non caricate");
        }
    }

    /**
     * This method performs the saving of lang set on file
     *
     * @throws SavingException if somethings has gone wrong
     */
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

    /**
     * Loads the language set by file
     *
     * @return the string containing the language set
     * @throws LoadingException if somethig has gone wrong
     */
    protected String loadLang() throws LoadingException {
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

    /**
     * This method performs the saving of game's components
     *
     * @throws SavingException if something has gone wrong
     */
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

    /**
     * Loads game's component's
     *
     * @throws LoadingException if somethig has gone wrong
     */
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
