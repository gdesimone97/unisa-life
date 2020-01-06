/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.populator.exceptions.InvalidArgumentListException;
import exam.booklet.Subject;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 *
 * @author cmarino
 */
public class SubjectFactory extends StorableCreator {

    /**
     *
     * @param s Consist in a string that should follow the pattern %subject that
     * specifies the name of the subject
     * @throws InvalidArgumentListException
     */
    @Override
    public Storable create(String s) throws InvalidArgumentListException {
        StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);
        try {
            Subject sub = new Subject(st.nextToken());
            return sub;
        } catch (NoSuchElementException e) {
            throw new InvalidArgumentListException();
        }
    }

}
