/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import game.GameResources.Animation;
import game.Interfaces.Interactable;
import gameSystem.Game;

import game.Interfaces.Tickable;
import static gameSystem.Game.DIMENSIONSPRITE;
import gameSystem.map.MapManager;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import saving.Saveable;
import saving.exceptions.LoadingException;

/**
 * this class represents the player of the game
 * @author simon
 */
public class Player extends GameObject implements Tickable, Saveable {

    private static final int DELAY = 8;
    private int velX = 0;

    /**
     * y velocity of player
     */
    protected int velY = 0;

    /**
     * y velocity of the player
     */
    protected Animation upWalk;

    /**
     * animation of the player walking up
     */
    protected Animation downWalk;

    /**
     *animation of the player walking down
     */
    protected Animation leftWalk;

    /**
     * animation of the player walking left
     */
    protected Animation rightWalk;

    /**
     * animation of the player walking rigth
     */
    protected BufferedImage facingDownImage;

    /**
     * image of the player looking down
     */
    protected BufferedImage facingLeftImage;

    /**
     *image of the player looking left
     */
    protected BufferedImage facingRightImage;

    /**
     *image of the player looking right
     */
    protected BufferedImage facingUpImage;

    /**
     * image of the player looking up
     */
    protected FaceState face = DownFaceState.getInstance();
    private static Player uniqueIstance = null;
    private boolean nextMove = true;

    /**
     * name of the player
     */
    protected String nameOfPlayer = null;
    private int delta = 0;
    private int skin;

    private Player() {
        super(new Position(0, 0));
    }

    /**
     * set the face state of the player
     * @param s new state of the player
     */
    public void setState(FaceState s) {
        this.face = s;
    }

    /**
     *
     * @return actual face state of the player
     */
    public FaceState getFace() {
        return face;
    }
    
    
    private void changeFaceSet(BufferedImage up, BufferedImage left, BufferedImage down, BufferedImage right) {
        facingLeftImage = left;
        facingRightImage = right;
        facingUpImage = up;
        facingDownImage = down;
    }

    private void changeAnimationSet(Animation up, Animation left, Animation down, Animation right) {
        upWalk = up;
        downWalk = down;
        leftWalk = left;
        rightWalk = right;
    }

    /**
     * this method initializes the player at the beginning of the game
     * @param skin integer that represents the skin selected in main menu
     * @param name name of the player
     * @param initialPosition initial position in the map
     */
    public void initialize(int skin, String name, Position initialPosition) {
        BufferedImage texturePlayer[][] = null;
        nameOfPlayer = name;
        int cols = 0;
        this.skin = skin;
        p.setX(initialPosition.getX());
        p.setY(initialPosition.getY());
        try {
            BufferedImage characterImage = ImageIO.read(
                    getClass().getResourceAsStream("/Sprites/sprite" + skin + ".png")
            );
            if (characterImage.getHeight() % DIMENSIONSPRITE != 0 || characterImage.getWidth() % 32 != 0) {
                System.exit(5);
            }
            cols = characterImage.getWidth() / DIMENSIONSPRITE;

            texturePlayer = new BufferedImage[4][cols];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < cols; j++) {
                    texturePlayer[i][j] = characterImage.getSubimage(j * DIMENSIONSPRITE, i * DIMENSIONSPRITE, DIMENSIONSPRITE, DIMENSIONSPRITE);
                }
            }
        } catch (Exception e) {
            System.exit(4);
        }
        changeFaceSet(texturePlayer[0][0], texturePlayer[1][0], texturePlayer[2][0], texturePlayer[3][0]);
        changeAnimationSet(new Animation(Arrays.copyOfRange(texturePlayer[0], 1, texturePlayer[0].length)),
                new Animation(Arrays.copyOfRange(texturePlayer[1], 1, texturePlayer[0].length)),
                new Animation(Arrays.copyOfRange(texturePlayer[2], 1, texturePlayer[0].length)),
                new Animation(Arrays.copyOfRange(texturePlayer[3], 1, texturePlayer[0].length)));
    }

    /**
     *
     * @return the instance of this singleton class
     */
    public static Player getIstance() {
        if (uniqueIstance == null) {
            uniqueIstance = new Player();
        }
        return uniqueIstance;
    }

    /**
     * setter method for x of the player
     * @param x 
     */
    public void setX(int x) {
        this.p.setX(x);
    }

    /**
     * setter method for y of the player
     * @param y
     */
    public void setY(int y) {
        this.p.setY(y);
    }

    /**
     * 
     * @return x of the player
     */
    public int getX() {
        return p.getX();
    }

    /**
     *
     * @return y of the player
     */
    public int getY() {
        return p.getY();
    }

    /**
     *
     * @return x velocity of player
     */
    public int getVelX() {
        return velX;
    }

    /**
     *
     * @return y velocity of player
     */
    public int getVelY() {
        return velY;
    }

    /**
     * setter method for x velocity of player
     * @param velX
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     * setter method for y velocity of player
     * @param velY
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }

    /**
     * this method is used to update the position of the player during the game
     */
    @Override
    public void tick(/*LinkedList<GameObject> objects*/) {
        nextMove = true;
        int x = p.getX();
        int y = p.getY();

        if (y % 32 == 0 && x % 32 == 0) {
            if (velX > 0) {
                face = RightFaceState.getInstance();
            } else if (velX < 0) {
                face = LeftFaceState.getInstance();
            } else if (velY > 0) {
                face = DownFaceState.getInstance();
            } else if (velY < 0) {
                face = UpFaceState.getInstance();
            }
            collisions(MapManager.getInstance().getMap().getObjectManager());
            if (velX != 0 && x + velX > 0 && x + velX < MapManager.getInstance().getMap().getWidthMap() - Game.DIMENSIONSPRITE && nextMove == true) {
                delta = velX / DELAY;
                p.setX(x + delta);
            } else {
                if (velY != 0 && y + velY > 0 && y + velY < MapManager.getInstance().getMap().getHeightMap() - Game.DIMENSIONSPRITE && nextMove == true) {
                    delta = velY / DELAY;
                    p.setY(y + delta);
                }
            }
        } else {
            if (x % 32 != 0) {
                p.setX(x + delta);
            }
            if (y % 32 != 0) {
                p.setY(y + delta);
            }
        }
        downWalk.runAnimation();
        leftWalk.runAnimation();
        rightWalk.runAnimation();
        upWalk.runAnimation();

    }

    /**
     * this method checks if player is able to move in a direction
     * @param ObjectsManager HashMap of the object in the map of the player
     */
    private void collisions(ObjectManager objMan) {
        GameObject g = objMan.getObjectInNextPosition(getScaledPosition());

        if (g != null && (g instanceof Teleport || g instanceof Bed)) {
            ((Interactable) g).interact();
            setVelX(0);
            setVelY(0);
        } else {
            g = objMan.getObjectInNextPosition(face.nextStep());
            if (g != null && g instanceof Coin) {
                ((Interactable) g).interact();
            } else if (g != null && !((g instanceof Teleport) || (g instanceof Bed))) {
                nextMove = false;
            }
        }
    }

    /**
     * this method render the image of the player
     * @param g graphics of the canvas
     */
    public void render(Graphics g) {
        if (velX > 0) {
            rightWalk.drawAnimation(g, p.getX(), p.getY(), width, height);
            return;
        }

        if (velX < 0) {
            leftWalk.drawAnimation(g, p.getX(), p.getY(), width, height);
            return;
        }
        if (velY > 0) {
            downWalk.drawAnimation(g, p.getX(), p.getY(), width, height);
            return;
        }

        if (velY < 0) {
            upWalk.drawAnimation(g, p.getX(), p.getY(), width, height);
            return;
        }
        face.drawFace(g);
    }

    private Position visualViewOfPlayer() {
        return face.visualViewOfPlayer();
    }

    /**
     * 
     * @return pixel width of player
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return pixel height of player
     */
    public int getHeight() {
        return height;
    }

    @Override
    public Serializable save() {
        ArrayList<Serializable> list = new ArrayList<>();
        list.add(getX());
        list.add(getY());
        list.add(getName());
        list.add(this.skin);
        return list;
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        List<Serializable> list = (List<Serializable>) obj;
        int skin = (int) list.get(3);
        Position p = new Position((int) list.get(0), (int) list.get(1));
        String name = (String) list.get(2);
        initialize(skin, name, p);
    }

    /**
     *
     * @return name of the player
     */
    public String getName() {
        return nameOfPlayer;
    }

    /**
     * setter method for name
     * @param name name of the player
     */
    public void setName(String name) {
        this.nameOfPlayer = name;
    }

    /**
     *
     * @return index of player in order to access to the Database
     */
    @Override
    public String getIndex() {
        return "Player";
    }

}
