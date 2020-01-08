/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import exam.booklet.Subject;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import quests.quest.Quest;
import quests.quest.QuestNotValidException;

/**
 * Storable Creator for Quest
 * @author cmarino
 */
public class QuestFactory extends StorableCreator {

    /**
     *
     * @param s The string describing the arguments list that should follow the
     * pattern %level%subject where level is an integer that idenitifies the
     * related level/semester of the quest; subject is a String that identifies
     * the Subject object that needs to be loaded from the DB.
     *
     * @throws InvalidArgumentListException
     */
    @Override
    public Storable create(String s) throws InvalidArgumentListException {

        StringTokenizer st = new StringTokenizer(s, StorableCreator.DELIMETER);

        try {

            //Retrieve subject from the DB itself
            Quest q = new Quest(Integer.parseInt(st.nextToken()), new Subject(st.nextToken()));

            while (st.hasMoreTokens()) {
                q.putItem(st.nextToken());
            }

            return q;

        } catch (NoSuchElementException | NumberFormatException | QuestNotValidException e) {
            throw new InvalidArgumentListException();
        }
    }

}
