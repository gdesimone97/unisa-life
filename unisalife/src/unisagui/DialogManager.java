/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Virginia Cavallaro
 * @author Davide Coppola
 */
public class DialogManager {
    
    private final GameFrame gameframe;
    private TitledBorder title;
    
    protected DialogManager(){
        gameframe = GameFrame.getInstance();
    }
    /**
     * show an hint in a small text area that can be closed using a button
     * @param hint
     * 
     */
    public void showHint(String hint){
        SwingUtilities.invokeLater(() -> gameframe.HintDialog.setAlwaysOnTop(true));
        SwingUtilities.invokeLater(() -> gameframe.HintTextArea.setText(hint));
        SwingUtilities.invokeLater(() -> gameframe.HintScrollPane.setVisible(true));
        SwingUtilities.invokeLater(() -> gameframe.HintDialog.setVisible(true));
        SwingUtilities.invokeLater(() -> gameframe.HintDialog.setFocusable(true));
    }
    
    public void hideHint(){
        SwingUtilities.invokeLater(() -> gameframe.HintTextArea.setText(""));
        SwingUtilities.invokeLater(() -> gameframe.HintScrollPane.setVisible(false));
        SwingUtilities.invokeLater(() -> gameframe.HintDialog.setVisible(false));
        SwingUtilities.invokeLater(() -> gameframe.HintDialog.setFocusable(false));
    }
    /**
     * show a little conversation 
     * @param name is the name of the person who is talking
     * @param conversation
     
     */
    public void showDialog(String name, String conversation){
        title = BorderFactory.createTitledBorder(name);
        SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setAlwaysOnTop(true));
        SwingUtilities.invokeLater(() -> gameframe.ConversationTextArea.setBorder(title));
        SwingUtilities.invokeLater(() -> gameframe.ConversationTextArea.setText(conversation));
        SwingUtilities.invokeLater(() -> gameframe.ConversationScrollPane.setVisible(true));
        SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setVisible(true));
        SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setFocusable(true));
    }
    
    public void hideDialog(){
        title = BorderFactory.createTitledBorder("");
        SwingUtilities.invokeLater(() -> gameframe.ConversationTextArea.setBorder(title));
        SwingUtilities.invokeLater(() -> gameframe.ConversationTextArea.setText(""));
        SwingUtilities.invokeLater(() -> gameframe.ConversationScrollPane.setVisible(false));
        SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setVisible(false));
        SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setFocusable(false));
    }
}
