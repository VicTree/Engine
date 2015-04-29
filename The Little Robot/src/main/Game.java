package main;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args){
		
		JFrame frame = new JFrame("The Little Robot");
		frame.setContentPane(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.pack();
		frame.setVisible(true);
		
	}
	
}
