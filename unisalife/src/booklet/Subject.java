/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklet;
import java.io.Serializable;
import question.Materia;
/**
 *
 * @author liovi
 */
public class Subject{
    private int score;
    private boolean available; 

    public Subject() {
        this.score = 0;
        this.available = true;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public boolean isAvailable(){
        return this.available;
    }

    public int getScore() {
        return score;
    }
    
}
