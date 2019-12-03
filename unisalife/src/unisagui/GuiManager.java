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
    
    public void showDialog(GameObject go, String s) throws NotInteractiveException {
        
        if(go instanceof Item)
            dialogmanager.showHint(s);
        else if(go instanceof Person)
            dialogmanager.showConversation(s);
        else
            throw new NotInteractiveException();
    }
    
    public void showExamDialog(Exam exam){
        exammanager.showExamDialog(exam);
    }
    
    public void showSettingsMenu(){
        settingsmenumanager.showSettingsMenu();
    }
    
    public void showMainMenu(){
        mainmenumanager.showMainMenu();
    }
    
    public void showInventoryDialog(){
        inventorymanager.showInventoryDialog();
    }
    
    public void showCareerDialog(){
        careermanager.showCareerDialog();
    }
    
    public void showQuestDialog(){
        questmanager.showQuestDialog();
    }
    
    public void showRequest(GameObject go){
        requestmanager.showRequest(go.getId());
    }
    
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
