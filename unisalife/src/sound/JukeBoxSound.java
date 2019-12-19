/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;

import java.io.BufferedInputStream;
import java.io.File;
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
 * This class manage all the sounds of the game
 */
public class JukeBoxSound implements JukeBox {

    private static HashMap<String, Clip> clips = new HashMap<>();
    private static int frame;
    private static boolean isActive;
    private final String pathFile = "./Resources/Sound/Sound.txt";
    private static JukeBoxSound instance;
    private double gain = 0.99;
    private float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);

    private JukeBoxSound() {
        try {
            readFile(this.pathFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        frame = 0;
        isActive = true;
    }

    /**
     *
     * Singleton Pattern static factory method that ensure to have a single
     * instance of the class with synchronized it's ok in case of multithreading
     * application
     *
     * @return the current instance of the class if exists, otherwise it creates
     * and returns a new one.
     */
    public static JukeBoxSound getInstance() {
        if (instance == null) {
            instance = new JukeBoxSound();
        }
        return instance;
    }

    /**
     *
     * @param s is a keyword that specified what type of sound do you want to
     * play this method play a clip (if it is different from null) or stops it
     * if it is already running
     */
    @Override
    public void play(String s) {
        if (!isActive) {
            return;
        }
        Clip c = clips.get(s);
        FloatControl vol = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
        vol.setValue(dB);
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

    /**
     *
     * @param s is a keyword that specified what type of sound do you want to
     * stop this method stop the sound corresponding to the keyword s
     */
    public void stop(String s) {
        if (clips.get(s) == null) {
            return;
        }
        if (clips.get(s).isRunning()) {
            clips.get(s).stop();
        }
    }

    /**
     *
     * @param pathFile is the directory that contains the sound file
     * @throws Exception if is impossible to read a file this method reads from
     * a file where to find all the sound
     */
    @Override
    public void readFile(String pathFile) throws Exception {
        String key;
        String value;
        boolean sound = true;

        File f = new File(pathFile);
        Scanner sc = new Scanner(f);

        try {

            while (sc.hasNext()) {
                key = sc.next();
                value = sc.next();
                load(value, key);

            }
        } finally {
            sc.close();
        }
    }

    /**
     *
     * @return if the sound is active (true) or not (false)
     */
    public static boolean isActive() {
        return isActive;
    }

    /**
     *
     * @param isActive set to true or false the sound
     */
    public static void setIsActive(boolean isActive) {
        JukeBoxSound.isActive = isActive;
    }

    /**
     * @param path is the path of a sound
     * @param key is keyword of that sound this method adds a new clip to the
     * sound HashMap
     */
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

    /**
     * @param s keyworf of music that you want to change
     * @param f volume this method allow other class to set volume
     */
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
