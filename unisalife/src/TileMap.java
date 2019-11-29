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
import javax.imageio.ImageIO;
import java.util.HashMap;


public class TileMap {
	
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
        
	private BufferedImage tileset;
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
                        //numTilesVert = tileset.getHeight() / tileSize;
                        //System.out.println("width:"+tileset.getWidth());
                        //System.out.println("height:"+tileset.getHeight());
                        //System.out.print("numtiles:"+numTilesOriz);
			tiles = new Tile[2][numTilesOriz];
			
			BufferedImage subimage;
			for(int col = 0; col < numTilesOriz; col++)     
                        {
				subimage = tileset.getSubimage(
							col * tileSize,
							0,
							tileSize,
							tileSize
						);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
                               // MatrixToTiles.put(new Tuple(row,col),)
                                
				subimage = tileset.getSubimage(
							col * tileSize,
							tileSize,
							tileSize,
							tileSize
						);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
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
	
	public int getTileSize() { return tileSize; }
	public int getx() { return x; }
	public int gety() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getNumRows() { return numRows; }
	public int getNumCols() { return numCols; }
	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesOriz;
		int c = rc % numTilesOriz;
		return tiles[r][c].getType();
	}
	public int getIndex(int row, int col) {
		return map[row][col];
	}
	public boolean isMoving() { return moving; }
	
	public void setTile(int row, int col, int index) {
		map[row][col] = index;
	}
	public void replace(int i1, int i2) {
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				if(map[row][col] == i1) map[row][col] = i2;
			}
		}
	}
	
	public void setPosition(int x, int y) {
		xdest = x;
		ydest = y;
	}
	public void setPositionImmediately(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void fixBounds() {
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}
	
	public void update() {
		if(x < xdest) {
			x += speed;
			if(x > xdest) {
				x = xdest;
			}
		}
		if(x > xdest) {
			x -= speed;
			if(x < xdest) {
				x = xdest;
			}
		}
		if(y < ydest) {
			y += speed;
			if(y > ydest) {
				y = ydest;
			}
		}
		if(y > ydest) {
			y -= speed;
			if(y < ydest) {
				y = ydest;
			}
		}
		
		fixBounds();
		
		colOffset = -this.x / tileSize;
		rowOffset = -this.y / tileSize;
		
		if(x != xdest || y != ydest) moving = true;
		else moving = false;
		
	}
	
	public void render(Graphics2D g) {
		/*System.out.println("row off"+rowOffset);
                System.out.println("col off"+colOffset);
                System.out.println("righe"+numRowsToDraw);
                System.out.println("colonne"+numColsToDraw);*/
		for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
		
			if(row >= numRows) break;
			
			for(int col = colOffset; col < colOffset + numColsToDraw; col++) {
				
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