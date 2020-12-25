package de.ft.ledwall;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

public class Sound {
    public static Clip TetrisTheme=loadtoClip("tetris.wav");
    public static Clip RacingGameTheme=loadtoClip("RacingGameTheme.wav");
    public static Clip AlleJahreWieder=loadtoClip("allejahrewieder.wav");


    public static void play(String pfad)  {
        Clip clip = loadtoClip(pfad);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(6);

        clip.start();
    }

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

}
