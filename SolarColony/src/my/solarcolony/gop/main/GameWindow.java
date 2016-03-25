package my.solarcolony.gop.main;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * GameWindow sets up the main game frames
 */
public class GameWindow extends JFrame {
	private JPanel panel1 = new JPanel();  // main panel of the main menu
	private JFrame frame = new JFrame("Solar Colony");
	private Music effects = new Music();
	
	public GameWindow() throws UnsupportedAudioFileException, LineUnavailableException{
		
		frame.setSize(720, 720);
		frame.setLocationRelativeTo(null);      //positions the frame from start up in middle
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		effects.backgroundMusic();
		
		// adding a "new game" button
		frame.add(panel1);
		JButton button1 = new JButton("New Game");
		panel1.add(button1);
		button1.addActionListener(new ActionListener(){
			
			// New game button redirects to the Game Settings
			public void actionPerformed(ActionEvent e){
				frame.setVisible(false);                         // hide frame 1 for focus on game settings
				JFrame frame2 = new JFrame("Solar Colony");
				frame2.setVisible(true);
				frame2.setLocationRelativeTo(null);
				frame2.setSize(720,720);
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
			
						// create game frame
						JFrame frame3 = new JFrame("Solar Colony");
						frame3.setVisible(true);
						frame3.setLocationRelativeTo(null);
						frame3.setSize(720,720);
						frame3.setLocationRelativeTo(null);
						frame3.setResizable(false);
						
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
		//button2.setBounds(561, 11, 40, 40);
		panel1.add(button2);
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame frame2 = new JFrame("Solar Colony");
				frame2.setVisible(true);
				frame2.setSize(720,720);
				JLabel label1 = new JLabel ("Loaded Game");
				JPanel panel = new JPanel();
				frame2.add(panel);
				panel.add(label1);
			}
		});
		
		//adding a "quit" button, prompting for user's confirmation then quit
		frame.add(panel1);
		JButton button3 = new JButton("Quit");
		panel1.add(button3);
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame frame = new JFrame();
				int result = JOptionPane.showConfirmDialog(frame,"Are you sure?");
				if(result == JOptionPane.YES_OPTION){
					/* add a save feature before exit, then quit */
					System.exit(0);
				}
			}
		});
		
	}
}
