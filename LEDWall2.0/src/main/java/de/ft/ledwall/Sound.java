package de.ft.ledwall;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

public class Sound {
    public static Clip TetrisTheme;
    public static Clip RacingGameTheme;
    public static AudioInputStream ais;
    public static Clip loadtoClip(String path){
        AudioInputStream ais=null;
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        try {
            ais = AudioSystem.getAudioInputStream(new File(String.valueOf(FileSystems.getDefault().getPath("src","main","resources", "sounds",path))));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clip.open(ais);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clip;

    }
    public static void load(){
        TetrisTheme=loadtoClip("tetris.wav");
        RacingGameTheme=loadtoClip("RacingGameTheme.wav");
    }
}
