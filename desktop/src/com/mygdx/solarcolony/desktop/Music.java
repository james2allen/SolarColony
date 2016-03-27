package com.mygdx.solarcolony.desktop;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/*
 *  Music class contains the music that will be available within game play
 */
public class Music {
	 Clip clip;
	
	// plays the background Music
   public void backgroundMusic() {
	   try {
		         AudioInputStream audio = AudioSystem.getAudioInputStream(new File("background.wav"));
		         clip = AudioSystem.getClip();
		         clip.open(audio);
		         clip.start();
		       }
		       catch(UnsupportedAudioFileException e) {
		           e.printStackTrace();
		       }
		       catch(IOException i) {
		    	   i.printStackTrace();
		       }
		       catch(LineUnavailableException a) {
		    	   a.printStackTrace();
		       }
		   }
   
   public void stopBMusic(){
	   clip.stop();
   }
}