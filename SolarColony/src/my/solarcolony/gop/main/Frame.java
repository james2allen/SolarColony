package my.solarcolony.gop.main;

import javax.swing.SwingUtilities;

/**
 * Frame is the main class to run the game
 * 
 */

public class Frame {
	public static void main(String[] args) {
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