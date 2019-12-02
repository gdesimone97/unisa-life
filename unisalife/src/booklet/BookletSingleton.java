/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package booklet;
import question.Materia;
import java.io.Serializable;
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

public class BookletSingleton implements Serializable{
    
    private static final long serialVersionUID = -7604766932017737115L;
    private EnumMap<Materia,Subject> booklet;
    
    private BookletSingleton(){
        this.booklet = new EnumMap(Materia.class);
        for (Materia x : Materia.values()){
            booklet.put(x, new Subject());
        }
    }

    /**
     * This method allows to view the score of a exam given the subject
     * @param subject
     * @return the score of the exam
     */
    public int getScore(Materia subject) {
        return booklet.get(subject).getScore();
    }
    
    /**
     * This method allows to set a score to the exam and make it no longer
     * available
     * 
     * @param subject
     * @param score
     */
    public void setScore(Materia subject, int score){
        Subject newScore = booklet.get(subject);
        newScore.setScore(score);
        newScore.setAvailable(false);
        booklet.replace(subject, newScore);
    }

    /**
     *
     * @param booklet
     */
    public void setBooklet(EnumMap<Materia, Subject> booklet) {
        this.booklet = booklet;
    }
    
    private static class BookletSingletonHelper{
        private static final BookletSingleton instance = new BookletSingleton();
    }
    
    /**
     *
     * @return
     */
    public static BookletSingleton getInstance(){
        return BookletSingletonHelper.instance;
    }
    
    /**
     *
     * @return
     */
    protected Object readResolve() {
        return getInstance();
    }
}
