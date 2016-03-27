package com.mygdx.solarcolony.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.solarcolony.playstate.Game;

import javax.swing.SwingUtilities;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputProcessor;


public class Main extends ApplicationAdapter{
	
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
                try {
                    GameWindow window = new GameWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }

			}
		});
				
		LwjglApplicationConfiguration cfg = 
				new LwjglApplicationConfiguration();
		
		cfg.title = Game.TITLE;
		cfg.width = Game.V_WIDTH;
		cfg.height = Game.V_HEIGHT;
		cfg.resizable = false;
		
		
		new LwjglApplication(new Game(), cfg);
		
		
	}
	
	
}
