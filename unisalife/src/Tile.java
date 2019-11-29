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

public class Tile {
	
	private BufferedImage image;
	private int type;
	
	// tile types
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	public Tile(BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}
	
	public BufferedImage getImage() { return image; }
	public int getType() { return type; }
	
}