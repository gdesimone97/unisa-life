/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exam.question;

/**
 * Factory Method abstract class: specifies the methods to be implemented if questions are needed to be 
 * loaded in the memory of the program. The class has one abstract method getQuestions that changes its
 * implementation depending on
 * @author 1997g
 */
public abstract class QuestionFactory {
    private Materia materia;
    
    /**
    * The constructor initializes the QuestionFactory
    * @params matter is the specific matter where the set of questions is needed
    */
    public QuestionFactory(Materia materia) {
        this.materia = materia;
    }
    
    /** 
    * This abstract method changes its implementation depending on how questions are loaded
    * @return Questions an instance of the object Questions, prepared from the specific implementation of the factory
    */
    public abstract Questions getQuestions();
    
}
