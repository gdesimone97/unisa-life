/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests.quest;

import database.DatabaseManager;
import database.FileNotSetException;
import database.NoQuestsException;
import game.Interfaces.Initializable;
import gameSystem.EndGameState;
import gameSystem.GameStateManager;
import gameSystem.map.MapManager;
import saving.Saveable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import quests.QuestsManager;
import quests.mediator.Message;
import quests.mediator.User;
import unisagui.GuiManager;

/**
 *
 * @author liovi
 */
public class Quests extends User implements Saveable, Serializable, Initializable {

    private HashMap<String, Quest> quests;
    private HashMap<String, String> item;
    private int currentLevel;
    private static Quests instance = null;

    private Quests() {
        super();
    }

    /**
     * Gets an instance of the class
     * 
     * @return an instance of the class
     */
    public static Quests getInstance() {
        if (instance == null)
            synchronized (Quests.class) {
            if (instance == null) {
                instance = new Quests();
            }
        }
        return instance;
    }

    /**
     * Gets the requested Quest
     * 
     * @param s a String that indicates the required Quest
     * @return the required Quest if is present otherwise null
     */
    public Quest getQuest(String s) {
        return this.quests.get(s);
    }

    /**
     * Used to add the quests
     * 
     * @param q the Quest to add
     */
    public void putQuest(Quest q) {
        this.quests.put(q.getSubject().getInfo(), q);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Serializable save() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(quests);
        list.add(item);
        list.add(currentLevel);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(Serializable obj) {
        ArrayList<Object> list = (ArrayList<Object>) obj;
        quests = (HashMap<String, Quest>) list.get(0);
        item = (HashMap<String, String>) list.get(1);
        currentLevel = (int) list.get(2);
        if(currentLevel == 0)
            GuiManager.getInstance().setLevel("Tutorial");
        else
            GuiManager.getInstance().setLevel("Level " + String.valueOf(this.currentLevel));
        
        super.mediator = QuestsManager.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(Message mess) {
    }
    
    private void loadLevel() throws InitException{
        try {
            this.loadNewQuests(DatabaseManager.getDatabaseManager().getQuestsFromLevel(currentLevel));
            MapManager.getInstance().setLevel(currentLevel);
        } catch (FileNotSetException ex) {
            throw new InitException("Error loading quests");
        } catch(NoQuestsException ex){
            GameStateManager.getInstance().setState(EndGameState.getInstance());
        }
    }

    /**
     * This method is used to load new quests
     * 
     * @param quests the quests to load
     */
    public void loadNewQuests(List<Quest> quests) {

        this.quests = new HashMap<>();
        for (Quest quest : quests) {
            this.quests.put(quest.getSubject().getInfo(), quest);
            String currentQuest = quest.getSubject().getInfo();
            // prendere tutti gli elementi
            // elemento per elemento, aggiungerlo alla map associando la quest corretta
            for (String item : quest.getItemList()) {
                this.item.put(item, currentQuest);
            }
        }
    }
    
    /**
     * This method is used to change level
     */
    public void levelEnded(){
        this.currentLevel++;
        this.loadLevel();
        GuiManager.getInstance().setLevel("Level " + String.valueOf(this.currentLevel));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void receive(Message mess) {
        //Messaggio = stringa +  bool -> se bool è true --> ho fatto quest'esame
        String receiver = item.get(mess.getId());
        Quest qItem = this.getQuest(receiver);
        if(qItem!=null){
            qItem.receive(mess);
        }else{
            String quest = mess.getId();
            if (mess.getBool()) {
                quests.remove(quest);
            }
            if (quests.isEmpty()) {
                this.levelEnded();
            }
        }
    }
    
    /**
     * Gets the current level of the game
     * 
     * @return an int that represents the current level of the game
     */
    public int getCurrentLevel() {
        return this.currentLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        this.name = "Quests";
        this.currentLevel = 0;
        this.quests = new HashMap<>();
        this.item = new HashMap<>();
        this.loadLevel();  
    }

    /**
     * Gets all the active quests
     * 
     * @return an HashMap that represents all the active quests
     */
    public HashMap<String, Quest> getQuests() {
        return quests;
    }

}
