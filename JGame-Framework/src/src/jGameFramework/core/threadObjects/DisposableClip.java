package jGameFramework.core.threadObjects;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

/**
 * A class for clips. This ensures that operations are made only
 * if the requirements are met.
 *
 * It also has a dispose() method which returns true if the
 * clip is closed.
 *
 * @author Jérémie Beaudoin-Dion
 */
class DisposableClip {

    private Clip clip;
    private String id;

    /**
     * Creates a closed DisposableClip with the desired id
     */
    DisposableClip(String id){
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        this.id = id;
    }

    /**
     * Creates an open DisposableClip
     */
    DisposableClip(String id, AudioInputStream audioInputStream){
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        this.id = id;
        open(audioInputStream);
    }

    void play(int loopValue){
        if (clip.isOpen()){
            clip.setFramePosition(0);
            clip.loop(loopValue);
        }
    }

    void close(){
        stop();

        if (clip.isOpen()){
            clip.close();
        }
    }

    private void stop(){
        if (clip.isOpen() && clip.isRunning()){
            clip.stop();
        }
    }

    void open(AudioInputStream audioInputStream){
        if (!clip.isOpen()){
            try {
                clip.open(audioInputStream);
            } catch (LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Dispose of this clip if it is closed.
     */
    boolean dispose(){
        return !clip.isOpen();
    }

    boolean isRunning(){
        return clip.isRunning();
    }

    boolean isEqualTo(String value){
        return id.equals(value);
    }

}
