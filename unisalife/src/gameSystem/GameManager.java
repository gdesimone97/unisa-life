/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import character.StatusManager;
import database.DatabaseManager;
import exam.booklet.BookletSingleton;
import game.GameObjects.Camera;
import game.GameObjects.GameInventorySingleton;
import game.GameObjects.Player;
import game.Interfaces.Initializable.InitException;
import gameSystem.map.MapManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import language.FileTextManager;
import language.exceptions.LanguageSelectedNotAvailableException;
import quests.QuestsManagerSingleton;
import quests.quest.QuestsSingleton;
import saving.SaveManager;
import unisagui.GuiManager;

/**
 * This class contains: - the main() method, to start the Game; - the
 * initialization of all the managers of this game, like GameStateManager and
 * MapManager; - the camera and the player instance; This class is the only one
 * that manteins the Game reference and has the role of starting the game thread
 *
 * @author 1997g
 */
public class GameManager {

    private Game game;
    private static GameManager instance;

    private Player player;
    private Camera camera;

    private GameManager() {
        game = new Game();
    }

    public static GameManager getInstance() {
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
     * this method should be used instead of initGame(), and has the
     * functionality of loading the game as it was since the last save. It
     * starts the game
     */
    public void loadGame() {
        // call a loading method of all instances
        new Thread(() -> {
            try {
                Thread.sleep(100);
                
                // just set Loading state
                player = Player.getIstance();
                camera = new Camera(0, 0, player);
                GameStateManager.getInstance().init();
                SaveManager.getSaveManager().load();

                Thread.sleep(500);
                
                GameStateManager.getInstance().setState(PlayState.getInstance());
            } catch (Exception ex) {
            }
        }).start();
    }

    /**
     * this method have to be called the first time we want to initialize the
     * game and all the managers of it. It starts the game
     */
    public void initGame() {

        // just set Loading state
        player = Player.getIstance();
        camera = new Camera(0, 0, player);

        GameStateManager.getInstance().init();
    }

    /**
     * creates and runs the Game thread
     */
    public void startGame(int skin, String Name) {
        Thread t = new Thread(game);
        t.start();

        // init all managers
        new Thread(() -> {
            try {
                Thread.sleep(100);
                
                StatusManager.getInstance().init();
                Player.getIstance().initialize(skin, Name);
                MapManager.getInstance();
                BookletSingleton.getInstance();
                QuestsManagerSingleton.getInstance();
                QuestsSingleton.getInstance();
                GameInventorySingleton.getInstance();
                FileTextManager.getFileTextManager().setLanguage("eng");

                Thread.sleep(500);
                
                GameStateManager.getInstance().setState(PlayState.getInstance());
            } catch (InitException ex) {
            } catch (InterruptedException ex) {
            } catch (LanguageSelectedNotAvailableException ex) {
            }
        }).start();
    }

    /**
     * stops the game thread
     */
    public void stopGame() {
        // call an autosave method
        
        game.stopGame();
    }

    /**
     * 
     * @return an instance of the camera present in the game
     */
    public Camera getCamera() {
        return camera;
    }
    
    
    /** 
     * main method just calls a start method on the GuiManager
     * @param args 
     */
    public static void main(String[] args) {
        GuiManager.getInstance().startGame();
    }
    
    
}
