package com.mygdx.solarcolony.desktop;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.solarcolony.playstate.Game;
import com.badlogic.gdx.InputProcessor;


/**
 * GameWindow sets up the main game frames
 */
public class GameWindow extends JFrame{
	private JPanel panel1 = new JPanel();  // main panel of the main menu
	private JFrame frame = new JFrame("Solar Colony");
	private JFrame frame2 = new JFrame("Solar Colony");
	private Music effects = new Music();
	private LwjglApplicationConfiguration cfg = 
			new LwjglApplicationConfiguration();
	
	public GameWindow() throws UnsupportedAudioFileException, LineUnavailableException, FileNotFoundException{
		panel1.setLayout(null);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);      //positions the frame from start up in middle
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		effects.backgroundMusic();

		// adding a "new game" button
		frame.add(panel1);
		JButton button1 = new JButton("New Game");
		button1.setBounds(290, 240, 200, 80);
		panel1.add(button1);
		button1.addActionListener(new ActionListener(){

			// New game button redirects to the Game Settings
			public void actionPerformed(ActionEvent e){
				frame.setVisible(false);                         // hide frame 1 for focus on game settings
				frame2.setVisible(true);
				frame2.setLocationRelativeTo(null);
				frame2.setSize(800,600);
				frame2.setLocationRelativeTo(null);
				frame2.setResizable(false);
				JLabel label1 = new JLabel ("Game Settings");
				JPanel panel = new JPanel();

				// add start game button to start the game play
				JButton game = new JButton("Start Game");
				game.addActionListener(new ActionListener(){
					// start game button will redirect to game screen
					public void actionPerformed(ActionEvent e){
						// Hide Frames for gameplay
						frame.setVisible(false);
						frame2.setVisible(false);

						// actual game
						cfg.title = Game.TITLE;
						cfg.width = Game.V_WIDTH;
						cfg.height = Game.V_HEIGHT;
						cfg.resizable = false;

						new LwjglApplication(new Game(), cfg);


						// ADD PAUSE SCREEN
						// ADD FINISH SCREEN
					}
				});

				// add back button to go back to main screen
				JButton back = new JButton("Back");
				back.addActionListener(new ActionListener(){
					// New game button redirects to the Game Settings
					public void actionPerformed(ActionEvent e){

						// change visibility of the panels
						frame.setVisible(true);
						frame2.setVisible(false);
					}
				});

				// adding buttons onto the panel to display
				frame2.add(panel);
				panel.add(label1);
				panel.add(game);
				panel.add(back);

			}
		});

		// adding a "load game" button
		frame.add(panel1);
		JButton button2 = new JButton("Load Game");
		button2.setBounds(290, 340, 200, 80);
		panel1.add(button2);
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame frame2 = new JFrame("Solar Colony");
				frame2.setVisible(true);
				frame2.setSize(800,600);
				JLabel label1 = new JLabel ("Loaded Game");
				JPanel panel = new JPanel();
				frame2.add(panel);
				panel.add(label1);
			}
		});

		//adding a "quit" button, prompting for user's confirmation then quit
		frame.add(panel1);
		JButton button3 = new JButton("Quit");
		button3.setBounds(290, 440, 200, 80);
		panel1.add(button3);
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame frame = new JFrame();
				int result = JOptionPane.showConfirmDialog(frame,"Are you sure?");
				if(result == JOptionPane.YES_OPTION){
					/* add a save feature before exit, then quit */
					effects.stopBMusic();
					System.exit(0);
				}
			}
		});

	}
}
