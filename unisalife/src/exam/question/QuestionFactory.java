/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.question;

import exam.booklet.Subject;

/**
 * Factory Method abstract class: specifies the methods to be implemented if questions are needed to be 
 * loaded in the memory of the program. The class has one abstract method getQuestions that changes its
 * implementation depending on
 * @author 1997g
 */
public abstract class QuestionFactory {
    private Subject subject;
    
    /**
    * The constructor initializes the QuestionFactory
     * @param subject the matter from which get the questions
    */
    public QuestionFactory(Subject subject) {
        this.subject = subject;
    }
    
    /** 
    * This abstract method changes its implementation depending on how questions are loaded
    * @return Questions an instance of the object Questions, prepared from the specific implementation of the factory
    */
    public abstract Questions getQuestions();

    /**
     * returns the subject
     * @return 
     */
    public Subject getSubject() {
        return subject;
    }
    
    
    
}
