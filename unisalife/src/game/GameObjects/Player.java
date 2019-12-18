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
import game.Interfaces.Renderable;
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
 *
 * @author simon
 */
public class Player extends GameObject implements Tickable, Renderable, Saveable {

    private static final int DELAY = 8;
    private int velX = 0;
    protected int velY = 0;
    protected Animation upWalk;
    protected Animation downWalk;
    protected Animation leftWalk;
    protected Animation rightWalk;
    protected BufferedImage facingDownImage;
    protected BufferedImage facingLeftImage;
    protected BufferedImage facingRightImage;
    protected BufferedImage facingUpImage;
    protected FaceState face;
    private static Player uniqueIstance = null;
    private boolean nextMove = true;

    private int delta = 0;


    /*public Player(float x,float y,SubjectEnum i){
     super(x,y,i);
     }*/
    private Player(Position p) {
        super(p);
        face = new DownFaceState(this);
        initialize(3, "Ciao");
    }

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
     * @param g
     * @return da 0 a 8 up da 9 a 17 left da 18 a 26 down da 27 a 35 right
     *
     *
     */
    private void initialize(int skin, String name) {
        BufferedImage texturePlayer[][] = null;
        int cols = 0;
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

    public static Player getIstance() {
        if (uniqueIstance == null) {
            uniqueIstance = new Player(new Position(32, 32));
        }
        return uniqueIstance;
    }

    public void setX(int x) {
        this.p.setX(x);
    }

    public void setY(int y) {
        this.p.setY(y);
    }

    /**
     *
     * @param a
     */
    /**
     *
     * @return
     */
    public int getX() {
        return p.getX();
    }

    public int getY() {
        return p.getY();
    }

    public int getVelX() {
        return velX;
    }

    /**
     *
     * @return
     */
    public int getVelY() {
        return velY;
    }

    /**
     *
     * @param velX
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     *
     * @param velY
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }

    @Override
    public void tick(/*LinkedList<GameObject> objects*/) {
        nextMove = true;
        int x = p.getX();
        int y = p.getY();

        if (y % 32 == 0 && x % 32 == 0) {
            if (velX > 0) {
                face = new RightFaceState(this);
            } else if (velX < 0) {
                face = new LeftFaceState(this);
            } else if (velY > 0) {
                face = new DownFaceState(this);
            } else if (velY < 0) {
                face = new UpFaceState(this);
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
        //collisions(game.getActualMap().getList());
        downWalk.runAnimation();
        leftWalk.runAnimation();
        rightWalk.runAnimation();
        upWalk.runAnimation();

    }

    /**
     *
     * @param ObjectsManager
     */
    private void collisions(ObjectManager objMan) {

        GameObject g = objMan.get(getScaledPosition());
        if (g != null && g instanceof Teleport) {
            ((Interactable)g).interact();
            setVelX(0);
            setVelY(0);
        } 
        else 
        {
            g = objMan.get(face.nextStep());
            if (g != null && !(g instanceof Teleport)) {
                nextMove = false;
            }
        }
    }

    /**
     *
     * @param g
     */
    @Override
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Serializable save() {
        ArrayList<Serializable> list = new ArrayList<>();
        list.add(getX());
        list.add(getY());
        return list;
    }

    @Override
    public void load(Serializable obj) throws LoadingException {
        List<Serializable> list = (List<Serializable>) obj;
        setX((int) list.get(0));
        setY((int) list.get(1));
    }

}
