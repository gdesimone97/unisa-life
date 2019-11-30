/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Questions;

/**
 *
 * @author 1997g
 */
public abstract class QuestionFactory {
    private Materia materia;
    
    public QuestionFactory(Materia materia) {
        this.materia = materia;
    }
    
    public abstract Questions getQuestions();
    public abstract Questions parseQuestions(String[] str);
    
}
