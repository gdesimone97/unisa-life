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
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 *
 * @author simon
 */
public class Tile implements Serializable, Externalizable {

    private BufferedImage image;

    

    /**
     *
     * @param image image of the tile
     */
    
    public Tile(BufferedImage image) {
            this.image = image;

    }
	
    /**
     *
     * @return image of the tile
     */
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        ImageIO.write(image, "png", (ObjectOutputStream) out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ObjectInputStream ins = (ObjectInputStream) in;
        this.image = ImageIO.read(ins);
    }

}
