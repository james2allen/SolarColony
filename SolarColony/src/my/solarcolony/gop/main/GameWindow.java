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
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Solar Colony");
		frame.setSize(720, 720);
		frame.setLocationRelativeTo(null);      //positions the frame from start up in middle
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		// adding a "new game" button
		JPanel panel1 = new JPanel();
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
		JPanel panel2 = new JPanel();
		frame.add(panel2);
		JButton button2 = new JButton("Load Game");
		panel2.add(button2);
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
		
		//adding a "quit" button
		JPanel panel3 = new JPanel();
		frame.add(panel3);
		JButton button3 = new JButton("Quit");
		panel3.add(button1);
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFrame frame = new JFrame();
				int result = JOptionPane.showConfirmDialog(frame,"Are you sure?");
				if(result == JOptionPane.YES_OPTION){
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		
		//set visibility of panels on frame
		frame.setVisible(true);
	}
}
