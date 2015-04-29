package gameState;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import tileMap.Background;
import tileMap.TileMap;

public class Level1State extends GameState{

	private TileMap tileMap;
	private Background background;
	
	public Level1State(Manager manager){
		this.manager = manager;
		init();
	}
	
	public void init(){
		tileMap = new TileMap(30);
		tileMap.loadTiles("/TileSets/grasstileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0,0);
		
		background = new Background("/Backgrounds/grassbg1.gif", .1);
	}
	public void update(){}
	public void draw(Graphics2D g){
		
		//erases previous screen
		background.draw(g);
		
		
		//draws map
		tileMap.draw(g);
		
	}
	public void keyPressed(int k) {}
	public void keyReleased(int k) {}
}
