/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import exam.booklet.BookletSingleton;
import game.GameObjects.Camera;
import game.GameObjects.GameInventorySingleton;
import game.GameObjects.Player;
import language.FileTextManager;
import quests.QuestsManagerSingleton;
import quests.quest.QuestsSingleton;
import unisagui.GuiManager;

/**
 *
 * @author 1997g
 */
public class GameManager {
    private Game game;
    private static GameManager instance;
    
    private GameStateManager gsm;
//    private MapManager mm;
    private Player player;
    private BookletSingleton booklet;
    private Camera camera;
    
    private GameManager() {
        game = new Game();
    }
    
    public static GameManager getInstance(){
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    
    public Game getGame() {
        return game;
    }
    
    public void loadGame() {
        //SaveManager.load();
        
        startGame();
    }
    
    public void initGame() {
        player = Player.getIstance();
        camera = new Camera(0, 0, player);
        
        gsm = GameStateManager.getInstance();
        gsm.setState(PlayState.getInstance());
//        mm = MapManager.getInstance();
        booklet = BookletSingleton.getInstance();
        
        QuestsManagerSingleton.getInstance();
        QuestsSingleton.getInstance();
        BookletSingleton.getInstance();
        GameInventorySingleton.getInstance();
        
        try{
        FileTextManager fileManager = FileTextManager.getFileTextManager();
        fileManager.setLanguage("eng");
        } catch (Exception ex){
            ex.printStackTrace();
        }
        
        // ecc...
        
        startGame();
    }
    
    private void startGame() {
        Thread t = new Thread(game);
        t.start();
    }

    public Camera getCamera() {
        return camera;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        GuiManager.getInstance().startGame();
    }
    
    
}
