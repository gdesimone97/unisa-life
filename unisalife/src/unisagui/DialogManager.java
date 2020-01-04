/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import gameSystem.GameStateManager;
import gameSystem.PauseState;
import gameSystem.PlayState;
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
    private boolean DialogAvailable = true;
    private boolean HintAvailable = true;
    private RequestGui request = null;
    
    protected DialogManager(){
        gameframe = GameFrame.getInstance();
    }
    /**
     * show an hint in a small text area that can be closed using a button
     * @param hint
     * 
     */
    
    
    public void showHint(String hint) throws HintAlreadyOpenedException {
        System.out.println("unisagui.DialogManager.showHint()");
        if (!HintAvailable)
            throw new HintAlreadyOpenedException();
        else{
            if(gameframe.isShowing()){
                SwingUtilities.invokeLater(() -> gameframe.HintDialog.setAlwaysOnTop(true));
                SwingUtilities.invokeLater(() -> gameframe.HintTextArea.setText(hint));
                SwingUtilities.invokeLater(() -> gameframe.HintScrollPane.setVisible(true));
                SwingUtilities.invokeLater(() -> gameframe.HintDialog.setVisible(true));
                SwingUtilities.invokeLater(() -> gameframe.HintDialog.setFocusable(true));
                HintAvailable = false;
                GameStateManager.getInstance().setState(PauseState.getInstance());
            }
        }
    }
    
    public boolean isHintAvailable(){
        return HintAvailable;
    }

    public boolean isDialogAvailable() {
        return DialogAvailable;
    }
    
    public void hideHint(){
        SwingUtilities.invokeLater(() -> gameframe.HintTextArea.setText(""));
        SwingUtilities.invokeLater(() -> gameframe.HintScrollPane.setVisible(false));
        SwingUtilities.invokeLater(() -> gameframe.HintDialog.setVisible(false));
        SwingUtilities.invokeLater(() -> gameframe.HintDialog.setFocusable(false));
        GameStateManager.getInstance().setState(PlayState.getInstance());
        HintAvailable = true;
    }
    /**
     * show a little conversation 
     * @param name is the name of the person who is talking
     * @param conversation
     * @param request
     
     */

    
        public void showDialog(String name, String conversation, RequestGui request) throws DialogAlreadyOpenedException{
            System.out.println("unisagui.DialogManager.showDialog()");
        if (!DialogAvailable)
            throw new DialogAlreadyOpenedException();
        else{
            if(gameframe.isShowing()){
                this.request = request;
                title = BorderFactory.createTitledBorder(name);
                SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setAlwaysOnTop(true));
                SwingUtilities.invokeLater(() -> gameframe.ConversationTextArea.setBorder(title));
                SwingUtilities.invokeLater(() -> gameframe.ConversationTextArea.setText(conversation));
                SwingUtilities.invokeLater(() -> gameframe.ConversationScrollPane.setVisible(true));
                SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setVisible(true));
                SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setFocusable(true));
                DialogAvailable = false;
                GameStateManager.getInstance().setState(PauseState.getInstance());
            }
        }
    }
    
    public void hideDialog(){
        
        title = BorderFactory.createTitledBorder("");
        SwingUtilities.invokeLater(() -> gameframe.ConversationTextArea.setBorder(title));
        SwingUtilities.invokeLater(() -> gameframe.ConversationTextArea.setText(""));
        SwingUtilities.invokeLater(() -> gameframe.ConversationScrollPane.setVisible(false));
        SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setVisible(false));
        SwingUtilities.invokeLater(() -> gameframe.ConvDialog.setFocusable(false));
        DialogAvailable = true;
        GameStateManager.getInstance().setState(PlayState.getInstance());
        if(request!=null)
            request.setValue(true);
        request = null;
    }

    public static class DialogAlreadyOpenedException extends Exception {

        public DialogAlreadyOpenedException() {
        }
    }

    public static class HintAlreadyOpenedException extends Exception {

        public HintAlreadyOpenedException() {
        }
    }
}
