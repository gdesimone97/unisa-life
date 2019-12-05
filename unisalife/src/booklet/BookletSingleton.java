/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package booklet;
import question.Materia;
import java.util.EnumMap;

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

public class BookletSingleton {
    
    private static BookletSingleton instance = null;
    private EnumMap<Materia,Subject> booklet;
    
    private BookletSingleton(){
        this.booklet = new EnumMap(Materia.class);
        for (Materia x : Materia.values()){
            booklet.put(x, new Subject());
        }
    }

    /**
     * This method allows to view the score of a exam given the subject
     * 
     * @param subject
     * @return the score of the exam
     */
    public int getScore(Materia subject) {
        return booklet.get(subject).getScore();
    }
    
    /**
     * True means not already passed
     * False means already passed
     * 
     * @param subject
     * @return boolean that indicates if the exam is passed or not
     */
    public boolean getAvailablity(Materia subject){
        return booklet.get(subject).isAvailable();
    }
    
    /**
     * This method allows to set a score to the exam and make it no longer
     * available
     * 
     * @param subject indicates the subject of the exam
     * @param score indicate the score reached at the exam
     */
    public void setScore(Materia subject, int score){
        Subject newScore = booklet.get(subject);
        newScore.setScore(score);
        newScore.setAvailable(false);
        booklet.replace(subject, newScore);
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
    
}
