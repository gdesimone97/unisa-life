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
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 *
 * @author simon
 */
public class TileMap implements Serializable{
	
	// position
	private int x;
	private int y;
	private int xdest;
	private int ydest;
	private int speed;
	private boolean moving;
	
	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	// tileset
        
	transient private BufferedImage tileset;
	private int numTilesOriz;
        private int numTilesVert;
        private Tile[][] tiles;
	
	// drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
 
    public TileMap(int tileSize,int w,int h) {
		this.tileSize = tileSize;
		numRowsToDraw = /*Game.WIDTHMAP / tileSize + 2;*/w; //31
		numColsToDraw = /*Game.HEIGHTMAP / tileSize + 2;*/h;  //31
		speed = 4;
	}
	

    public void loadTiles(String s) {
		//System.out.print(s);
		try {
        tileset = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			numTilesOriz = tileset.getWidth() / tileSize;
                        numTilesVert = tileset.getHeight()/tileSize;
                        //numTilesVert = tileset.getHeight() / tileSize
			tiles = new Tile[numTilesVert][numTilesOriz];
			
			BufferedImage subimage;
			for(int col = 0; col < numTilesOriz; col++)     
                        {
                            
                            for(int row = 0;row < numTilesVert;row++){
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
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
    
    public void loadMap(String s) {
		
		try {
			
			InputStream in = getClass().getResourceAsStream(s);
                        
			BufferedReader br = new BufferedReader(
						new InputStreamReader(in)
					);
			
			numCols = Integer.parseInt(br.readLine()); //29
			numRows = Integer.parseInt(br.readLine()); //29
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			//xmin = Game.WIDTH - width;
			//xmin = -width;
			//xmax = 0;
			//ymin = Game.HEIGHT - height;
			//ymin = -height;
			//ymax = 0;
			
			String delims = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

    
    public int getWidth() { return width; }

    
    public int getHeight() { return height; }


   
    
	public void render(Graphics2D g) {
		/*System.out.println("row off"+rowOffset);
                System.out.println("col off"+colOffset);
                System.out.println("righe"+numRowsToDraw);
                System.out.println("colonne"+numColsToDraw);*/
		for(int row = 0; row < numRowsToDraw; row++) {
		
			if(row >= numRows) break;
			
			for(int col = 0; col <  numColsToDraw; col++) {
				
				if(col >= numCols) break;
				if(map[row][col] == 0) continue;
				
				int rc = map[row][col];
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