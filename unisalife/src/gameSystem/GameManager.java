/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import character.StatusManager;
import exam.booklet.BookletSingleton;
import game.GameObjects.Camera;
import game.GameObjects.GameInventorySingleton;
import game.GameObjects.Player;
import language.FileTextManager;
import quests.QuestsManagerSingleton;
import quests.quest.QuestsSingleton;
import unisagui.GuiManager;

/**
 * This class contains: 
 * - the main() method, to start the Game;
 * - the initialization of all the managers of this game, like GameStateManager and MapManager;
 * - the camera and the player instance;
 * This class is the only one that manteins the Game reference and has the role of starting
 * the game thread
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
    
    /**
     * 
     * @return the instance of the game
     */
    public Game getGame() {
        return game;
    }
    
    /**
     * this method should be used instead of initGame(), and has the functionality
     * of loading the game as it was since the last save. It starts the game
     */
    public void loadGame() {
        //SaveManager.load();
        
        //startGame();
    }
    
    /**
     * this method have to be called the first time we want to initialize the game and
     * all the managers of it. It starts the game
     */
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
        StatusManager.getInstance();
        
        try{
        FileTextManager fileManager = FileTextManager.getFileTextManager();
        fileManager.setLanguage("eng");
        } catch (Exception ex){
            ex.printStackTrace();
        }
        
        // ecc...
        
        //startGame();
    }
    
    /**
     * creates and runs the Game thread
     */
    public void startGame() {
        Thread t = new Thread(game);
        t.start();
    }
    
    /**
     * stops the game thread
     */
    private void stopGame() {
        game.stopGame();
    }

    /**
     * 
     * @return an instance of the camera present in the game
     */
    public Camera getCamera() {
        return camera;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        GuiManager.getInstance().startGame();
    }
    
    
}