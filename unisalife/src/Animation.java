/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;
import java.awt.Graphics;
/**
 *
 * @author simon
 */
public class Animation {
    private static final int speed=2;
    private int frames;
    private int index=0;
    private int count=0;
    private BufferedImage[] images;
    private BufferedImage currentImage;
    
    public Animation(BufferedImage... args){
        images=new BufferedImage[args.length];
        frames=args.length;
        for(int i=0;i<frames;i++){
            images[i]=args[i];
        }
    }
    
    public void runAnimation(){
        index++;
        if(index>speed){
            index=0;
            nextFrame();
        }
    }
    
    public void nextFrame(){
        for(int i=0;i<frames;i++){
            if(count==i)
                currentImage=images[i];
        }
        count++;
        if(count>frames)
            count=0;
    }
        
        public void drawAnimation(Graphics g,int x,int y){
            g.drawImage(currentImage, x, y, null);
        }
        
        public void drawAnimation(Graphics g,int x,int y,int scaleX,int scaleY){
            g.drawImage(currentImage, x, y, scaleX, scaleY, null);
        }
     
        
        
    }
    
    
