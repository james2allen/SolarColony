package com.mygdx.solarcolony.desktop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.*;

/*
 *  Music class contains the music that will be available within game play
 */
public class Music {
	 Clip clip;

	// plays the background Music
   public void backgroundMusic() throws FileNotFoundException {  
	   try {
		         File file = new File("desktop/src/background.wav");  		// Filename
		   		 String path = file.getAbsolutePath();			// Get absolute path of file
		   		 File sound = new File(path);
		         AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
		         clip = AudioSystem.getClip();
		   		 clip.open(audio);
		   		 FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		   		 volume.setValue(-20.0f);
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

	/*
		StopBMusic stops the background music from playing
	 */
   public void stopBMusic() {
	   try {
		   clip.stop();
	   } catch (NullPointerException n) {
		   n.printStackTrace();
	   }
   }
}
