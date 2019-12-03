/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import javax.swing.SwingUtilities;

/**
 *
 * @author virgi
 */
public class ExamManager {
    
    private GameFrame gameframe = GameFrame.getInstance();
    
    protected ExamManager(){
        
    }
    /**
     * 
     * @param exam 
     */
    public void showExamDialog(Exam exam){
        SwingUtilities.invokeLater(() -> gameframe.ExamDialog.setVisible(true));
    }
    
}
