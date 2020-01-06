/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameSystem;

import character.StatusManager;
import exam.booklet.Booklet;
import game.GameObjects.Camera;
import game.GameObjects.GameInventory;
import game.GameObjects.Player;
import game.Interfaces.Initializable.InitException;
import gameSystem.map.MapManager;
import javax.swing.JOptionPane;
import language.FileTextManager;
import quests.QuestsManager;
import quests.quest.Quests;
import saving.SaveManager;
import saving.exceptions.LoadingException;
import saving.exceptions.SavingException;
import sound.JukeBoxMusic;
import sound.JukeBoxSound;
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
        Thread t = new Thread(game);
        t.start();

        // call a loading method of all instances
        new Thread(() -> {
            try {
                Thread.sleep(100);

                player = Player.getIstance();
                camera = new Camera(0, 0, player);
                MapManager.getInstance().init();
                GameStateManager.getInstance().init();
                SaveManager.getSaveManager().load();
                Thread.sleep(500);

                GameStateManager.getInstance().setState(PlayState.getInstance());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(game, "System Error: " + ex.toString());
                System.exit(1);
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

        try {
            GameStateManager.getInstance().init();
        } catch (InitException ex) {
            JOptionPane.showMessageDialog(game, "System Error: " + ex.toString());
            ex.printStackTrace();
            System.exit(1);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(game, "Undefined Error. Contact support\n" + ex.toString());
            ex.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * creates and runs the Game thread
     */
    public void startGame(int skin, String Name) {
        Thread t = new Thread(game);
        t.start();

        // init all managers, at the end starts the playState
        new Thread(() -> {
            try {
                MapManager.getInstance().init();
                Player.getIstance().initialize(skin, Name, MapManager.getInstance().getMap().getInitialPosition());
                
                QuestsManager.getInstance().init();
                Quests.getInstance().init();
                Booklet.getInstance().init();
                GameInventory.getInstance().init();
                JukeBoxMusic.getInstance();
                JukeBoxSound.getInstance();

                //This is the last to be started
                StatusManager.getInstance().init();

                GameStateManager.getInstance().setState(PlayState.getInstance());
            } catch (InitException ex) {
                JOptionPane.showMessageDialog(game, "System Error: " + ex.toString());
                ex.printStackTrace();
                System.exit(1);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(game, "Undefined Error. Contact support\n" + ex.toString());
                ex.printStackTrace();
                System.exit(1);
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
     *
     * @param args
     */
    public static void main(String[] args) throws SavingException {
        try {
            FileTextManager.getFileTextManager().init();
            SaveManager.getSaveManager().loadKeys();
        } catch (InitException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (LoadingException loadEx) {
            System.out.println(loadEx.getMessage());
            loadEx.printStackTrace();
        }
        GuiManager.getInstance().startGame();
    }

    public boolean isRunning() {
        return game.isRunning();
    }

}
