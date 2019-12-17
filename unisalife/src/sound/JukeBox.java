/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
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
public class JukeBox implements Serializable{
    
    private static HashMap<String,Clip> sounds;
    private static HashMap<String,Clip> audios;
    private static int frame;
    private static boolean soundIsActive;
    private static boolean audioIsActive;
    private static final String pathFileSounds = "";
    private static final String pathFileAudios = "";
    
    public static void JukeBox() {
        try{
        readFile(pathFileSounds);
        readFile(pathFileAudios);
        }catch (Exception ex){
            System.out.println("Error loading track audio");
        }
        frame = 0;
        soundIsActive = true;
        audioIsActive = true;
        
    }
    
    /**
     * 
     * @param path
     * @param key
     * @param sound if true is a sound, if false is an audio
     */
    public static void load(String path, String key, boolean sound ) {
        HashMap<String,Clip> clips;
        if(sound)
            clips = sounds;
        else
            clips = audios;
        
        if (clips.get(key) != null) {
            return;
        }
        Clip clip;
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(
                            JukeBox.class.getResourceAsStream(path)));
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

    public boolean isSoundIsActive() {
        return soundIsActive;
    }

    public boolean isAudioIsActive() {
        return audioIsActive;
    }

    public void setSoundIsActive(boolean soundIsActive) {
        this.soundIsActive = soundIsActive;
    }

    public void setAudioIsActive(boolean audioIsActive) {
        this.audioIsActive = audioIsActive;
    }
    
    
    public static void play(String s, boolean sound) {
        play(s, frame, sound);
    }

    private static void play(String s, int i, boolean sound) {
        HashMap<String,Clip> clips;
        if(sound)
            clips = sounds;
        else
            clips = audios;
        
        Clip c = clips.get(s);
        if (c == null) {
            return;
        }
        if (c.isRunning()) {
            c.stop();
        }
        c.setFramePosition(i);
        while (!c.isRunning()) {
            c.start();
        }
    }

    public static void stop(String s, boolean sound) {
        
        HashMap<String,Clip> clips;
        if(sound)
            clips = sounds;
        else
            clips = audios;
        
        if (clips.get(s) == null) {
            return;
        }
        if (clips.get(s).isRunning()) {
            clips.get(s).stop();
        }
    }

    public static void loop(String s) {
            loop(s, frame, frame, audios.get(s).getFrameLength() - 1);
    }

    public static void loop(String s, int frame) {
            loop(s, frame, JukeBox.frame, audios.get(s).getFrameLength() - 1);        
    }

    public static void loop(String s, int start, int end) {
        loop(s, frame, start, end);
    }

    public static void loop(String s, int frame, int start, int end) {
        Clip c = audios.get(s);
        if (c == null) {
            return;
        }
        if (c.isRunning()) {
            c.stop();
        }
        c.setLoopPoints(start, end);
        c.setFramePosition(frame);
        c.loop(Clip.LOOP_CONTINUOUSLY);
    }


    public static void close(String s, boolean sound) {
        
        HashMap<String,Clip> clips;
        if(sound){
            stop(s,sound);
            sounds.get(s).close();
        }
        else{
            stop(s,sound);
            audios.get(s).close();
        }
    }

    public static void setVolume(String s, float f, boolean sound) {
        
        HashMap<String,Clip> clips;
        if(sound)
            clips = sounds;
        else
            clips = audios;
        
        Clip c = clips.get(s);
        if (c == null) {
            return;
        }
        FloatControl vol = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
        vol.setValue(f);
    }
    
    public void updateFile(String path, boolean sound) throws FileNotFoundException, IOException{
        
         try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(path))) {
            if(sound)
                return;
                //sounds.writeObject();
            else
                return;
                //audios.writeObject();
        }
    
    }
    
    public static void readFile(String path) throws Exception{
        
        String key;
        String value;
        boolean sound = true;
        
        Scanner sc = new Scanner(new BufferedReader(new FileReader(path))).useDelimiter("\\s*\n\\s*");
        try{
            while(sc.hasNext()){
                key=sc.next();
                value=sc.next();
                System.out.println("Inserito");
                load(value, key, sound);
            }
        }
        finally{
            sc.close();
        }
        
    }
}

