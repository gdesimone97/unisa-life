/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.question;

/**
 * Interface of the Collection of questions
 * Every implementation of this class must implement the iterator() method
 * @author 1997g
 */
public interface QuestionsCollection {

    /**
     *
     * @return
     */
    public QuestionsIterator iterator();
}
