/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quests;

import database.DatabaseManager;
import database.FileNotSetException;
import database.NoQuestsException;
import database.ObjectNotFoundException;
import game.Interfaces.Initializable;
import gameSystem.EndGameState;
import gameSystem.GameStateManager;
import gameSystem.LoadingState;
import gameSystem.map.MapManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import quests.mediator.*;
import quests.quest.Quest;
import quests.quest.Quests;

/**
 * This class is used due to a necessity of manage the communication between the
 * booklet, the inventory and the quests
 *
 * @author liovi
 */
public class QuestsManager implements QuestMessages, Initializable {

    private static QuestsManager instance = null;
    private List<User> users;
    // Item : Subject associative collection
    private HashMap<String, String> item;
    private int currentLevel;

    private QuestsManager() {

    }

    /**
     *
     * @return an instance of QuestsManager
     */
    public static QuestsManager getInstance() {
        if (instance == null)
            synchronized (QuestsManager.class) {
            if (instance == null) {
                instance = new QuestsManager();
            }
        }
        return instance;
    }

    
    public void levelEnded(){
        this.currentLevel++;
        this.loadLevel();                
    }
    
    private void loadLevel() throws InitException{
        try {
            this.loadNewItems(DatabaseManager.getDatabaseManager().getQuestsFromLevel(currentLevel));
            MapManager.getInstance().setLevel(currentLevel);
        } catch (FileNotSetException ex) {
            throw new InitException("Error loading quests");
        } catch(NoQuestsException ex){
            GameStateManager.getInstance().setState(EndGameState.getInstance());
        }
    }
    
    public void loadNewItems(List<Quest> quests) {
        // prima volta:
        /*
        list<quest>, list<item> = dbms.getQuestsFromLevel(0);
        QuestsManager.getInstance().loadNewItems(list);
         */

        // lista fornita dal game manager, che ha già effettuato l'accesso al database
        /*  {esempio di comportamento del game manager}
        ....-> dopo che è stata fatta la finish
        DatabaseManagaer dbms = new...
        list<quest>, list<item> = dbms.getQuestsFromLevel(QuestsManager.getInstance().getCurrentLevel());
        QuestsManager.getInstance().loadNewItems(list);
        ..ora metto nella mappa tutti gli oggetti presenti nella lista
         */
        Quests.getInstance().loadNewQuests(quests);
        for (Quest q : quests) {
            String currentQuest = q.getSubject().toString();
            // prendere tutti gli elementi
            // elemento per elemento, aggiungerlo alla map associando la quest corretta
            for (String item : q.getItemList()) {
                this.item.put(item, currentQuest);
            }
        }
    }

    /**
     * This method is used to forward messages between the classes
     *
     * @param mess contains the message to forward
     * @param user is the user who sended the message
     */
    @Override
    public void sendMessage(Message mess, User user) {
        String receiver = item.get(mess.getId());
        Quest q = Quests.getInstance().getQuest(receiver);
        q.receive(mess);
    }

    /**
     * This method is used to register a new user to the list
     *
     * @param user reference to the user
     */
    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public void init() throws InitException {
        this.users = new ArrayList<>();
        this.item = new HashMap<>();
        this.currentLevel = 0;
        Quests.getInstance().init();
        this.loadLevel();        
    }

}
