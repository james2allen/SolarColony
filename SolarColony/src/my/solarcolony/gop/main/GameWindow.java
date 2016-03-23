package my.solarcolony.gop.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * GameWindow sets up the main game window
 */
public class GameWindow extends JFrame {
	private JPanel panel1 = new JPanel();  // main panel of the main menu
	
	public GameWindow(){
		
		JFrame frame = new JFrame("Solar Colony");
		frame.setSize(720, 720);
		frame.setLocationRelativeTo(null);      //positions the frame from start up in middle
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		// adding a "new game" button
		frame.add(panel1);
		JButton button1 = new JButton("New Game");
		panel1.add(button1);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame frame2 = new JFrame("Solar Colony");
				frame2.setVisible(true);
				frame2.setSize(720,720);
				JLabel label1 = new JLabel ("Game Settings");
				JPanel panel = new JPanel();
				frame2.add(panel);
				panel.add(label1);
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
		
		//set visibility of panels on frame
		frame.setVisible(true);
	}
}
