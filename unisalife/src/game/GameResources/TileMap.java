package game.GameResources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author simon
 */
import database.Storable;
import game.GameObjects.Position;
import gameSystem.Game;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.imageio.ImageIO;
import org.dizitart.no2.objects.Id;

/**
 * This method is used to read a Tiled Map from file and generate the relative
 * image
 * 
 * @author simon
 */
public class TileMap implements Serializable, Storable {

    @Id
    private int id;

    // position
    private int x;
    private int y;
    private int xdest;
    private int ydest;
    private int speed;
    private boolean moving;
    private Position initialPosition;

    // bounds
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    // map
    private int[][][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int layers;
    private int width;
    private int height;
    private String miniMapPath;

    // tileset
    private BufferedImage tileset;
    private int numTilesOriz;
    private int numTilesVert;
    private Tile[][] tiles;

    // drawing
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    private TileMap(){
        
    }
    
    /**
     * This method initializes a Tile Map with the passed parameters
     * 
     * @param id an int that represents the id of the map
     * @param w an int that represents the width of the map
     * @param h an int that represents the height of the map
     * @param tileset a String that represents the path of the tileset (.png)
     * @param map a String that represents the path of the map file (.map)
     * @param initialPosition a Position that represents the initial position of the player
     * @param miniMapPath a String that represents the path of the mini map (.png)
     */
    public TileMap(int id, int w, int h, String tileset, String map, Position initialPosition, String miniMapPath) {
        this.tileSize = Game.DIMENSIONSPRITE;
        numRowsToDraw = /*Game.WIDTHMAP / tileSize + 2;*/ w; //31
        numColsToDraw = /*Game.HEIGHTMAP / tileSize + 2;*/ h;  //31
        speed = 4;
        this.id = id;
        this.initialPosition = initialPosition;
        loadTiles(tileset);
        loadMap(map);
        this.miniMapPath = miniMapPath;
    }
    
    /**
     * Gets the path of the mini map
     * @return a String that represent the path of the mini map
     */
    public String getMiniMapPath(){
        return this.miniMapPath;
    }

    /**
     * Gets the id of the map
     * @return an int that represents the id of the map
     */
    public int getId(){
        return this.id;
    }

    private void loadTiles(String s) {
        
        try {
            tileset = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            numTilesOriz = tileset.getWidth() / tileSize;
            numTilesVert = tileset.getHeight() / tileSize;
            //numTilesVert = tileset.getHeight() / tileSize
            tiles = new Tile[numTilesVert][numTilesOriz];

            BufferedImage subimage;
            for (int col = 0; col < numTilesOriz; col++) {

                for (int row = 0; row < numTilesVert; row++) {
                    subimage = tileset.getSubimage(
                            col * tileSize,
                            row * tileSize,
                            tileSize,
                            tileSize
                    );
                    tiles[row][col] = new Tile(subimage);
                    // MatrixToTiles.put(new Tuple(row,col),)

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadMap(String s) {

        try {
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(in)
            );
            
            layers = Integer.parseInt(br.readLine());
            numCols = Integer.parseInt(br.readLine()); 
            numRows = Integer.parseInt(br.readLine());
            
            map = new int[layers][numRows][numCols]; 
            width = numCols * tileSize;
            height = numRows * tileSize;
            
            String delims = "\\s+";
            for(int l=0; l<layers;l++){    
                for (int row = 0 ; row < numRows; row++){
                    String line = br.readLine();
                    String[] tokens = line.split(delims);
                    for (int col = 0; col < numCols; col++) {
                        map[l][row][col] = Integer.parseInt(tokens[col]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets the width of the map
     * @return an int that represent the width of the map
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the map
     * @return an int that represent the height of the map
     */
    public int getHeight() {
        return height;
    }

    /**
     * Renderize the map. The map allows to use more than one layer.
     * When a tile is renderized the lower layer pixels are overwritten by
     * the pixels of the upper layer
     * 
     * @param g used to store renderized objects
     */
    public void render(Graphics2D g) {
        
        for (int l = 0; l<layers; l++){
            for (int row = 0; row < numRowsToDraw; row++) {
                if (row >= numRows) {
                    break;
                }
                for (int col = 0; col < numColsToDraw; col++) {
                    if (col >= numCols) {
                        break;
                    }
                    if (map[l][row][col] == 0) {
                        continue;
                    }
                    int rc = map[l][row][col];
                    if(rc != -1){
                        int r = rc / numTilesOriz;
                        int c = rc % numTilesOriz;
                        g.drawImage(
                                tiles[r][c].getImage(),
                                x + col * tileSize,
                                y + row * tileSize,
                                null
                        );   
                    }
                }
            }
        }
    }

    
    @Override
    public String getIndex() {
        return String.valueOf(this.id);
    }

    /**
     * Gets the initial position of the player
     * @return a Position that represents the initial position of the player
     */
    public Position getInitialPosition() {
        return initialPosition;
    }
    
    

}
