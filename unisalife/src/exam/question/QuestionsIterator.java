/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.question;

/**
 * Interface of the Iterator of Questions
 * In this interface there are 2 methods, hasNext() and next() that have to be completed
 * in every implementation of this iterator
 * @author 1997g
 */
public interface QuestionsIterator {
    public boolean hasNext();
    public Question next();
}
