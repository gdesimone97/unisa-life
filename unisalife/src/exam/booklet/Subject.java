/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.booklet;

import java.io.Serializable;
import language.Information;

/**
 * This class is used to give a structure for storing a score of an exam and its
 * availabilty
 *
 * @author liovi
 */
public class Subject implements Information, Serializable {

    private int score;
    private String subject;
    private boolean available;

    /**
     * Constructor that instantiates the object and sets it as available
     */
    public Subject(String subject) {
        this.subject = subject;
        this.score = 0;
        this.available = true;
    }

    /**
     * This method sets the score of corresponding exam
     *
     * @param score the score reached at the exam
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * This method is used to set an exam no longer available if its passed
     *
     * @param available boolean used to indicate availability
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * This method gives the state of the exam
     *
     * @return a boolean that indicates if the exam is or not is available
     */
    public boolean isAvailable() {
        return this.available;
    }

    /**
     * This method returns the corrispondig score of the exam
     *
     * @return an integer that indicates the reached score
     */
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return subject;
    }

    @Override
    public String getInfo() {
        return subject;
    }
}
