package com.mygdx.solarcolony.desktop;

import javax.swing.SwingUtilities;
import com.badlogic.gdx.ApplicationAdapter;


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
	}
}
