/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sound;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
public class JukeBox {
    
    private static HashMap<String,Clip> clips;
    private static int frame;
    
    public static void JukeBox() {
        clips = new HashMap<>();
        frame = 0;
    }
    
    public static void load(String path, String key) {
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
    
    public static void play(String s) {
        play(s, frame);
    }

    private static void play(String s, int i) {
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

    public static void stop(String s) {
        if (clips.get(s) == null) {
            return;
        }
        if (clips.get(s).isRunning()) {
            clips.get(s).stop();
        }
    }

    public static void resume(String s) {
        if (clips.get(s).isRunning()) {
            return;
        }
        clips.get(s).start();
    }

    public static void resumeLoop(String s) {
        Clip c = clips.get(s);
        if (c == null) {
            return;
        }
        c.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void loop(String s) {
        loop(s, frame, frame, clips.get(s).getFrameLength() - 1);
    }

    public static void loop(String s, int frame) {
        loop(s, frame, JukeBox.frame, clips.get(s).getFrameLength() - 1);
    }

    public static void loop(String s, int start, int end) {
        loop(s, frame, start, end);
    }

    public static void loop(String s, int frame, int start, int end) {
        Clip c = clips.get(s);
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

    public static void setPosition(String s, int frame) {
        clips.get(s).setFramePosition(frame);
    }

    public static int getFrames(String s) {
        return clips.get(s).getFrameLength();
    }

    public static int getPosition(String s) {
        return clips.get(s).getFramePosition();
    }

    public static void close(String s) {
        stop(s);
        clips.get(s).close();
    }

    public static void setVolume(String s, float f) {
        Clip c = clips.get(s);
        if (c == null) {
            return;
        }
        FloatControl vol = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
        vol.setValue(f);
    }

    public static boolean isPlaying(String s) {
        Clip c = clips.get(s);
        if (c == null) {
            return false;
        }
        return c.isRunning();
    }

}

