/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.booklet;

import database.Storable;
import java.io.Serializable;
import java.util.Objects;
import language.Information;

/**
 * This class is used to give a structure for storing a score of an exam and its
 * availabilty
 *
 * @author liovi
 */
public class Subject implements Information, Serializable, Comparable, Storable {

    private int score;
    private String subject;
    private boolean available;

    /**
     * Constructor that instantiates the object and sets it as available
     * @param subject 
     */
    public Subject(String subject) {
        this.subject = subject;
        this.score = 0;
        this.available = true;
    }

    private Subject() {

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
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.subject);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Subject other = (Subject) obj;
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        return true;
    }

    @Override
    public String getInfo() {
        return subject;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return 1;
        }
        if (!(o instanceof Subject)) {
            return 1;
        }
        return this.subject.compareTo(((Subject) o).toString());

    }

    
    @Override
    public String getIndex() {
        return this.subject;
    }
}
