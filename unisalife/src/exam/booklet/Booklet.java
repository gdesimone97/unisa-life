/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exam.booklet;
import database.DatabaseManager;
import database.FileNotSetException;
import game.Interfaces.Initializable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import quests.QuestsManager;
import quests.mediator.*;
import saving.Saveable;
import unisagui.GuiManager;

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

public class Booklet extends User implements Serializable,Saveable,Initializable {
    
    private static Booklet instance = null;
    private HashMap<String,Subject> booklet;
    
    private Booklet(){
        super();
        }
    

    /**
     * This method allows to view the score of a exam given the subject
     * 
     * @param subject is the subject to search
     * @return the score of the exam
     */
    public int getScore(Subject subject) {
        return booklet.get(subject.getInfo()).getScore();
    }
    
    /**
     * This method is used to iterate on the booklet
     * 
     * @return an HashSet of Subject that is an iterable data structure
     */
    public HashSet<Subject> iteratorBooklet() {
        HashSet<Subject> temp = new HashSet<>(booklet.values());
        return (HashSet<Subject>) temp;
    }

    /**
     * True means not already passed
     * False means already passed
     * 
     * @param subject is the subject to search
     * @return boolean that indicates if the exam is passed or not
     */
    public boolean getAvailablity(Subject subject){
        return booklet.get(subject.getInfo()).isAvailable();
    }
    
    /**
     * Used to search a Subject in the booklet
     * 
     * @param subject is the subject to search
     * @return the requested Subject
     */
    public Subject getSubject(String subject){
        return this.booklet.get(subject);
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
        booklet.put(subject.getInfo(), subject);
        this.send(new Message(subject.getInfo(),true));
        GuiManager.getInstance().updateCareer();
    }
    
    /**
     * If an instance of the class Booklet is not instanciated
     * create a reference for it and returns it. At every request after
     * the first instantiation returns the same instance.
     * 
     * @return instance of the class Booklet
     */
    public static Booklet getInstance(){
        if (instance == null)
            synchronized (Booklet.class){
                if(instance == null)
                    instance = new Booklet();
            }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Serializable save() {
        return this.booklet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void load(Serializable obj) {
        super.name = "booklet";
        super.mediator = QuestsManager.getInstance();
        mediator.addUser(this);
        this.booklet = (HashMap<String,Subject>) obj;
        
        GuiManager.getInstance().updateCareer();
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(Message mess) {
        mediator.sendMessage(mess, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receive(Message mess) {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        super.name = "booklet";
        super.mediator = QuestsManager.getInstance();
        mediator.addUser(this);
        this.booklet = new HashMap<>();
        try {
            List<Subject> subjects = DatabaseManager.getDatabaseManager().getSubjects();
            subjects.forEach((s) -> {
                this.booklet.put(s.getInfo(), s);
            });
            GuiManager.getInstance().updateCareer();
        } catch (FileNotSetException ex) {
            //ex.printStackTrace();
        }
    }
    
}
