/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;

import gameSystem.Game;
import gameSystem.GameManager;
import javax.swing.SwingUtilities;

/**
 * This class allows to interact with the GUI of UnisaLife
 *
 * @author Virginia Cavallaro
 * @author Davide Coppola
 */
public class GuiManager {

    private final HUDManager hudmanager;
    private final InventoryManager inventorymanager;
    private final CareerManager careermanager;
    private final RequestManager requestmanager;
    private final DialogManager dialogmanager;
    private final MainMenuManager mainmenumanager;
    private final SettingsMenuManager settingsmenumanager;
    private final ExamManager exammanager;
    private final QuestManager questmanager;
    private final GameFrame gameframe; //potrebbe essere evitato
    private static GuiManager instance;
    
    
    private GuiManager() {

        hudmanager = new HUDManager();
        inventorymanager = new InventoryManager();
        careermanager = new CareerManager();
        requestmanager = new RequestManager();
        dialogmanager = new DialogManager();
        mainmenumanager = new MainMenuManager();
        settingsmenumanager = new SettingsMenuManager();
        exammanager = ExamManager.getInstance();
        questmanager = new QuestManager();
        gameframe = GameFrame.getInstance();

    }

    /**
     * Singleton Pattern static factory method that ensure to have a single
     * instance of the class with synchronized it's ok in case of multithreading
     * application
     *
     * @return the current instance of the class if exists, otherwise it creates
     * and returns a new one.
     */
    public static synchronized GuiManager getInstance() {
        if (instance == null) {
            instance = new GuiManager();

        }
        return instance;
    }

    /**
     * This function sets the dimension, position of a Game object, then add it
     * to a JFrame
     *
     */
    public void startGame() {
        GameManager gm = GameManager.getInstance();
        Game game = gm.getGame();
        
        SwingUtilities.invokeLater(() -> game.setMaximumSize(new java.awt.Dimension((int) Game.WIDTHSCREEN, (int) Game.HEIGHTSCREEN)));
        SwingUtilities.invokeLater(() -> game.setMinimumSize(new java.awt.Dimension((int) Game.WIDTHSCREEN, (int) Game.HEIGHTSCREEN)));
        SwingUtilities.invokeLater(() -> game.setPreferredSize(new java.awt.Dimension((int) Game.WIDTHSCREEN, (int) Game.HEIGHTSCREEN)));
        SwingUtilities.invokeLater(() -> game.setSize(new java.awt.Dimension((int) Game.WIDTHSCREEN, (int) Game.HEIGHTSCREEN)));
        SwingUtilities.invokeLater(() -> game.setBounds(50, 75, Game.WIDTHSCREEN, Game.HEIGHTSCREEN));
        SwingUtilities.invokeLater(() -> gameframe.dispose());
        SwingUtilities.invokeLater(() -> gameframe.add(game));
        SwingUtilities.invokeLater(() -> gameframe.pack());
        gameframe.setVisible(true);
        SwingUtilities.invokeLater(() -> game.setVisible(true));
        
    }

    /**
     * When called the Conversation Area appears  on the screen
     * @param name is the name of the person who is talking
     * @param s is the text in the text area
     * @throws unisagui.DialogManager.DialogAlreadyOpenedException if Dialog is already shown 
     * 
     */
    public void showDialog(String name,String s) throws DialogManager.DialogAlreadyOpenedException {
        dialogmanager.showDialog(name,s,null);
    }
    
    public void showDialog(String name, String conversation, RequestGui request) throws DialogManager.DialogAlreadyOpenedException{
         dialogmanager.showDialog(name, conversation, request);
    }
    /**
     * When called the Conversation Area disappears
     */
    public void hideDialog(){
        dialogmanager.hideDialog();
    }
    /**
     * When called the Hint Area appears on the screen
     * @param s is the text of the hint
     * @throws unisagui.DialogManager.HintAlreadyOpenedException if Hint is already shown
     */
    public void showHint(String s) throws DialogManager.HintAlreadyOpenedException{
        dialogmanager.showHint(s);
    }
    /**
     * When called the Hint Area disappears
     */
    public void hideHint(){
        dialogmanager.hideHint();
    }
    
    public boolean isHintAvailable(){
        return dialogmanager.isHintAvailable();
    }

    public boolean isDialogAvailable() {
        return dialogmanager.isDialogAvailable();
    }
    
    /**
     *
     * @param examName Subject of the exam
     * @param question one of the question for this subject
     * @param answer1 first answer
     * @param answer2 second answer
     * @param answer3 third answer
     * @param answer4 fourth answer
     * @param time the number of second that the user has to answer the question
     * @param lock is an instance of ResultGui that is used by ExamManager to
     * put the right value of RESULT in the "setValue" method of ResultGui
     * @param level actual level of question
     * @param numLevel max level of question for this exam
     *
     * This method requires  all the methods useful for updating the interface relative
     * to the exam
     */
     public void showExamDialog(String examName, String question, String answer1, String answer2, String answer3,
                                String answer4, int time, ResultGui lock,int level,int numLevel) {
        exammanager.showExamDialog(examName, question, answer1, answer2, answer3, answer4, time, lock,level,numLevel);
    }
    /**
     * This method requires close the ExamDialog
     */
    public void closeExamDialog() {
        exammanager.closeExamDialog();
    }
    /**
     * 
     * @param correctness arrive from thred exam and says if the user has given the right answer
     * @param confirm  is an instance of RequestGui
     * This method calls methods useful for dynamic illumination of responses
     */
    public void isCorrect(boolean correctness,RequestGui confirm){
        exammanager.isCorrect(correctness ,confirm);
        
    }

    /**
     * When called the exam question is written on the Exam Dialog
     *
     * @param question is the question that has to be written
     */
    public void setExamQuestion(String question) {
        exammanager.setExamQuestion(question);
    }

    /**
     * When called one of the exam question is written in the Exam Dialog
     *
     * @param answer is the answer that has to be written
     * @param postion is the position of the answer in the exam dialog, position
     * <=4
     */
    public void setExamAnswer(String answer, int postion) throws Exception {
        exammanager.setExamAnswer(answer, postion);
    }

    /**
     * When called a JDialog with the Settings Menu appears or disappears on the
     * screen
     *
     * @param show if true the JDialog appears, if false disappears
     */
    public void showSettingsMenu(boolean show) {
        settingsmenumanager.showSettingsMenu(show);
    }

    /**
     * When called a JDialog with the Main Menu appears or disappears on the
     * screen
     *
     * @param show if true the JDialog appears, if false disappears
     */
    public void showMainMenu(boolean show) {
        mainmenumanager.showMainMenu(show);
    }

    /**
     * When called a JDialog with the Inventory appears on the screen
     */
    public void showInventoryDialog() {
        inventorymanager.showInventoryDialog();
    }
    

    /**
     * When called a JDialog with the Career appears or disappears on the screen
     *
     * @param show if true the JDialog appears, if false disappears
     */
    public void showCareerDialog(boolean show) {
        careermanager.showCareerDialog(show);
    }
    
    /**
     * this method update the Booklet of the gui
     */
    public void updateCareer(){
         gameframe.setCareer();
        
    }

    /**
     * When called a JDialog with the Career appears or disappears on the screen
     *
     */
    public void showQuestDialog() {
        questmanager.showQuestDialog();
    }
    
    public void hideQuestDialog() {
        questmanager.hideQuestDialog();
    }
    
    public void showDescription(int x){
        questmanager.showDescription(x);
    }
    
     public void hideDescription(){
        questmanager.hideDescription();
    }
    

    /**
     * When called a JDialog with the Request appears or disappears on the
     * screen
     *
     * @param s
     * @param lock
     */
    public void showRequest(String s, RequestGui lock) {
        requestmanager.showRequest(s, lock);
    }

    /**
     * When called the JDialog with the quests is update with the new quest or
     * the quest is deleted.
     *
     */
    public void updateQuestDialog(){
        questmanager.updateQuestDialog();
    }
    
    /**
     * When called the Inventory Diaog is updated
     */
    public void updateInventoryDialog(){
        inventorymanager.updateInventoryDialog();
    }
 /*
    public void updateInventoryDialog(Item item, int position, boolean presence){
        inventorymanager.updateInventoryDialog(item, presence);
    }
     */
    /**
     * When called the JDialog with the Career is update on the Exam. It will
     * change the vote and the state of the Exam
     *
     * @param exam is the exam to update
     */
    /*public void updateCareerDialog(Exam exam){
        careermanager.updateCareerDialog(exam);
    }*/
    /**
     * When called the stress bar is updated
     *
     * @param stress is the new stress value
     */
    public void updateStressBar(int stress) {
        hudmanager.updateStressBar(stress);
    }

    /**
     * When called the energy bar is updated
     *
     * @param energy is the new energy value
     */
    public void updateEnergyBar(int energy) {
        hudmanager.updateEnergyBar(energy);
    }

    /**
     * When called the hunger bar is updated
     *
     * @param hunger is the new hunger value
     */
    public void updateHungerBar(int hunger) {
        hudmanager.updateHungerBar(hunger);
    }

    /**
     * When called the money value is updated
     *
     * @param money is the new monet value
     */
    public void updateMoney(int money) {
        hudmanager.updateMoney(money);
    }

}
