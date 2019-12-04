/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisagui;


/**
 *
 * @author virgi
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
    private static GuiManager instance = null;

    private GuiManager() {
        
        hudmanager = new HUDManager();
        inventorymanager = new InventoryManager();
        careermanager = new CareerManager();
        requestmanager = new RequestManager();
        dialogmanager = new DialogManager();
        mainmenumanager = new MainMenuManager();
        settingsmenumanager = new SettingsMenuManager();
        exammanager = new ExamManager();
        questmanager = new QuestManager();
        
    }
    
    /**
     * Singleton Pattern
     * static factory method that ensure to have a single instance of the class
     * with synchronized it's ok in case of multithreading application 
     * @return the current instance of the class if exists, otherwise it creates and returns a new one.
     */
    
    public static synchronized GuiManager getInstance(){
        if (instance == null){
            instance = new GuiManager();
        }
        return instance;
    }
    /**
     * 
     * @param go is the GameObject  that start the Dialog
     * @param s  is a String to show in the Dialog
     * @param show  boolean that says if the Dialog must be open or closed
     * @throws NotInteractiveException 
     * This method use instanceof for understand if  show Hint dialog or Conversation  
     */
    public void showDialog(GameObject go, String s, boolean show) throws NotInteractiveException {
        
        if(go instanceof Item)
            dialogmanager.showHint(s,show);
        else if(go instanceof Person)
            dialogmanager.showConversation(s,show);
        else
            throw new NotInteractiveException();
    }
    /**
     * 
     * @param exam
     * @param show boolean that says if the Exam dialog must be open or closed
     * 
     */
    public void showExamDialog(Exam exam, boolean show){
        exammanager.showExamDialog(exam,show);
    }
    /**
     * 
     * @param show boolean that says if the Menu must be open or closed
     */
    public void showSettingsMenu(boolean show){
        settingsmenumanager.showSettingsMenu(show);
    }
    
    public void showMainMenu(boolean show){
        mainmenumanager.showMainMenu(show);
    }
    
    public void showInventoryDialog(boolean show){
        inventorymanager.showInventoryDialog(show);
    }
    
    public void showCareerDialog(boolean show){
        careermanager.showCareerDialog(show);
    }
    
    public void showQuestDialog(boolean show){
        questmanager.showQuestDialog(show);
    }
    /**
     * 
     * @param go
     * @param show 
     *  GameObject id is used for undetstand what type of request will be visible
     */
    public void showRequest(GameObject go, boolean show){
        requestmanager.showRequest(go.getId(),show);
    }
    /**
     * 
     * @param quest
     * @param presence for add or remove a quest 
     */
    public void updateQuestDialog(Quest quest, boolean presence){
        questmanager.updateQuestDialog(quest, presence);
    }
    
    public void updateInventoryDialog(Item item, boolean presence){
        inventorymanager.updateInventoryDialog(item, presence);
    }
    
    public void updateCareerDialog(Exam exam){
        careermanager.updateCareerDialog(exam);
    }
    
    public void updateStressBar(int stress){
        hudmanager.updateStressBar(stress);
    }
    
    public void updateEnergyBar(int energy){
        hudmanager.updateEnergyBar(energy);
    }
    
    public void updateHungerBar(int hunger){
        hudmanager.updateHungerBar(hunger);
    }
    
    public void updateMoney(int money){
        hudmanager.updateMoney(money);
    }
}
