/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import javax.swing.SwingUtilities;

/**
 *
 * @author Virginia Cavallaro
 * @author Davide Coppola
 */
 class RequestManager { //visibilità di package (default)

    private String info;
    private final GameFrame gameframe = GameFrame.getInstance();
    private boolean RESULT = true;
    protected static RequestGui rq;

    protected RequestManager() {

    }
    /**
     @param RESULT in this parameter the answer given by the user with no
     *time limit will be saved.
     */
    protected static void setRESULT(boolean RESULT) {
        rq.setValue(RESULT);
    }

    public void showRequest(String id, boolean show) {
        this.setInfo(id);
        //gameframe.RequestLabel.setText(Lang.getString());
        //dovrebbe restituirmi la stringa da inserire nella richiesta
    }

    private void setInfo(String id) {
        info = id;
    }

    public String getInfo() {
        return info;
    }
    /**
     * 
     * @param s is the string to show in the request
     * @param lock is an instance of RequestGui that is used by RequestManager to
     * put the right value of RESULT in the "setValue" method of RequestGui
     */
    public void showRequest(String s, RequestGui lock) {
        rq = lock;
//        SwingUtilities.invokeLater(() -> 
                gameframe.RequestLabel.setText(s);
//        SwingUtilities.invokeLater(() -> 
                gameframe.RequestDialog.setVisible(true);
        System.err.println("sono DENTRO REQUEST");
    }

}
