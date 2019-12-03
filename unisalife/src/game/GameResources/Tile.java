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
import java.awt.image.BufferedImage;

/**
 *
 * @author simon
 */
public class Tile {
	
	private BufferedImage image;
	/*private int type;
	
	// tile types
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	*/

    /**
     *
     * @param image
     */
    
	public Tile(BufferedImage image) {
		this.image = image;
		
	}
	
    /**
     *
     * @return
     */
    public BufferedImage getImage() { return image; }
	
	
}