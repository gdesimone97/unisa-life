/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Davide e Virginia
 */
public class JukeBoxSound implements  JukeBox{
    
    private static HashMap<String, Clip> clips;
    private static int frame;
    private static boolean isActive;
    private final String pathFile = "/Sound/Sound.txt";
    private static JukeBoxSound instance;
    
    
    private void JukeBoxSound() {
        try{
            readFile(pathFile);
        }catch (Exception ex){
            System.out.println("Error loading track audio");
        }
        frame = 0;
        isActive = true;
    }
    
    public static JukeBoxSound getInstance(){
        if( instance ==null)
            instance= new JukeBoxSound();
        return instance;
    }
    
     @Override
    public void play(String s) {
        Clip c = clips.get(s);
        if (c == null) {
            return;
        }
        if (c.isRunning()) {
            c.stop();
        }
        c.setFramePosition(frame);
        while (!c.isRunning()) {
            c.start();
        }
    }

    @Override
    public void stop(String s) {
        if (clips.get(s) == null) {
            return;
        }
        if (clips.get(s).isRunning()) {
            clips.get(s).stop();
        }
    }

    @Override
    public void readFile(String path) throws Exception {
        String key;
        String value;
        boolean sound = true;

        Scanner sc = new Scanner(new BufferedReader(new FileReader(path))).useDelimiter("\\s*\n\\s*");
        try {
            while (sc.hasNext()) {
                key = sc.next();
                value = sc.next();
                System.out.println("Inserito");
                load(value, key);
            }
        } finally {
            sc.close();
        }
    }

    @Override
    public void load(String path, String key) {
        Clip clip;
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(
                    JukeBoxSound.class.getResourceAsStream(path)));
            AudioFormat baseFormat = ais.getFormat(); //obtain audio format
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16, // sample size in bit
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
            clips.put(key, clip);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setVolume(String s, float f) {
        Clip c = clips.get(s);
        if (c == null) {
            return;
        }
        FloatControl vol = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
        vol.setValue(f);
    }
}

