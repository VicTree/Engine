package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import main.GamePanel;
import tileMap.Background;


public class MenuState extends GameState {
	
	//background of menu screen
	private Background background;
	
	//options on menu screen
	private String[] menuOptions = {"Start", "Quit"};
	int currentChoice = 0;
	
	//font
	private Color titleColor;
	private Font titleFont;
	private Font optionsFont;
	
	
	public MenuState(Manager manager){
		this.manager = manager;
		
		try{
			background = new Background("/Backgrounds/testBackgroung.png", 1);
			background.setSpeed(-.1, 0.0);
			
			titleColor = (Color.MAGENTA);
			titleFont = new Font("Roboto", Font.PLAIN, GamePanel.Width/10);
			
			optionsFont = new Font("Calibri", Font.PLAIN, GamePanel.Width/20);
		
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void init(){}
	public void update(){
		background.update();
		
	}
	public void draw(Graphics2D g){
		background.draw(g); //draws background
		
		//draws title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("The Little Robot", GamePanel.Width/10, GamePanel.Height/3);
		
		//draws choices
		g.setFont(optionsFont);
		for(int i = 0; i < menuOptions.length; i ++){
			if (i == currentChoice)
				g.setColor(Color.CYAN);
			else
				g.setColor(Color.BLACK);
		
		g.drawString(menuOptions[i], GamePanel.Width/3, GamePanel.Height/2 + (i *GamePanel.Height/5));
		}
		
	}
	
	private void select(){
		switch(currentChoice){
		case 0: manager.setState(Manager.LEVEL1STATE);   //start level 1
				break; 
		case 1: System.exit(0); //exit
				break; 
		}
	}
	
	
	public void keyPressed(int k){
		switch(k){
		case KeyEvent.VK_ENTER: select();
								break;
		case KeyEvent.VK_UP:	currentChoice --;
							 	if(currentChoice == -1) currentChoice = menuOptions.length-1;
							 	break;
		case KeyEvent.VK_DOWN: 	currentChoice++;
								if(currentChoice == menuOptions.length) currentChoice = 0;
								break;
		}
		
	}
	public void keyReleased(int k){}
}
