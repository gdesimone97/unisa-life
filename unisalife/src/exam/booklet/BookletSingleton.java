/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exam.booklet;
import java.io.Serializable;
import java.util.HashMap;
import quests.QuestsManagerSingleton;
import quests.mediator.*;
import quests.quest.QuestsSingleton;
import saving.Saveable;
/**
 * This class is used due to the necessity of have a booklet for our
 * character.
 * The class is constructed following the Sigleton Pattern because 
 * is required that only one instance of it will be created 
 * and is also Serializable due to the necessity of implement a 
 * load/save mechanism for the game.
 * 
 * 
 * @author liovi
 */

public class BookletSingleton extends User implements Serializable, Saveable{
    
    private static BookletSingleton instance = null;
    private HashMap<String,Subject> booklet;
    
    private BookletSingleton(){
        super();
        super.name = "booklet";
        super.mediator = QuestsManagerSingleton.getInstance();
        mediator.addUser(this);

        this.booklet = new HashMap<>();
        }
    

    /**
     * This method allows to view the score of a exam given the subject
     * 
     * @param subject
     * @return the score of the exam
     */
    public int getScore(Subject subject) {
        return booklet.get(subject.getInfo()).getScore();
    }
    
    /**
     * True means not already passed
     * False means already passed
     * 
     * @param subject
     * @return boolean that indicates if the exam is passed or not
     */
    public boolean getAvailablity(Subject subject){
        return booklet.get(subject.getInfo()).isAvailable();
    }
    
    /**
     * This method allows to set a score to the exam and make it no longer
     * available
     * 
     * @param subject indicates the subject of the exam
     * @param score indicate the score reached at the exam
     */
    public void setScore(Subject subject, int score){
        subject.setScore(score);
        subject.setAvailable(false);
        
        QuestsSingleton.getInstance().getQuest(subject.getInfo()).finish();
    }
    
    /**
     * If an instance of the class BookletSingleton is not instanciated
     * create a reference for it and returns it. At every request after
     * the first instantiation returns the same instance.
     * 
     * @return instance of the class BookletSingleton
     */
    public static BookletSingleton getInstance(){
        if (instance == null)
            synchronized (BookletSingleton.class){
                if(instance == null)
                    instance = new BookletSingleton();
            }
        return instance;
    }

    /**
     * This method is used to save the booklet
     * @return a Serializable object that represents the booklet state
     */
    @Override
    public Serializable save() {
        return this.booklet;
    }

    /**
     * This method is used to load the booklet 
     * @param obj is a Serializable. Downcast is necessary before load the obj
     */
    @Override
    public void load(Serializable obj) {
        this.booklet = (HashMap<String,Subject>) obj;
    }

    /**
     * This method is used to send a message to the Mediator who forwards it
     * to the receiver
     * @param mess is the message that the class would send
     */
    @Override
    public void send(Message mess) {
        mediator.sendMessage(mess, this);
    }

    /**
     * This method can be used to receive a message 
     * @param mess is the message that the class can receive
     */
    @Override
    public void receive(Message mess) {}
    
}
