package tileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;



public class TileMap {

	//position
	private double x;
	private double y;
	
	
	//bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private double tween;
	
	//map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numColumns;
	private int width;
	private int height;
	
	//Tile set
	private BufferedImage tileSet;
	private int numberOfTilesAcross;
	private Tile[][] tiles;
	
	//drawing bounds
	private int rowOffset;
	private int columnOffset;
	private int numberOfRowsToDraw;
	private int numberOfColumnsToDraw;
	
	
	public TileMap(int tileSize){
		this.tileSize = tileSize;
		numberOfRowsToDraw = GamePanel.Width/tileSize +2;
		numberOfColumnsToDraw = GamePanel.Height/tileSize + 2;
		tween = .07;
	}
	
	public void loadTiles(String name){
		try{
			tileSet = ImageIO.read(getClass().getResourceAsStream(name));
			numberOfTilesAcross = tileSet.getWidth()/tileSize;
			tiles = new Tile[2][numberOfTilesAcross];
			
			BufferedImage subImage;
			for (int column = 0; column < numberOfTilesAcross; column++){
				subImage = tileSet.getSubimage(column*tileSize, 0, tileSize, tileSize);
				
				tiles[0][column] = new Tile(subImage, Tile.NORMAL);
				
				subImage = tileSet.getSubimage(column * tileSize, tileSize, tileSize, tileSize);
				tiles[1][column] = new Tile(subImage,Tile.BLOCKED);
				
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadMap(String name){
		
		try{
			InputStream input = getClass().getResourceAsStream(name);
			BufferedReader buf = new BufferedReader(new InputStreamReader(input));
			
			numColumns = Integer.parseInt(buf.readLine());
			numRows = Integer.parseInt(buf.readLine());
			map = new int[numRows][numColumns];
			width = numColumns * tileSize;
			height = numRows * tileSize;
			
			String delimator = "\\s+";
			for (int row = 0; row <numRows; row ++){
				String line = buf.readLine();
				String[] tokens = line.split(delimator);
				for (int col = 0; col < numColumns; col++){
					map[row][col] = Integer.parseInt(tokens[col]);
				}
				
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public int getTileSize(){
		return tileSize;
	}
	
	public int getX(){
		return (int)x;
	}
	
	public int getY(){
		return (int)y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getType(int row, int col){
		int rc = map[row][col];
		int r = rc/numberOfTilesAcross;
		int c = rc % numberOfTilesAcross;
		return tiles[r][c].getType();
	}
	
	public void setPosition(double x, double y){
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;
		
		fixBounds();
		
		columnOffset = (int)-this.x/tileSize;
		rowOffset = (int)-this.y/tileSize;
	}
	
	private void fixBounds(){
		if(x<xmin) x = xmin;
		if(x>xmin) x = xmax;
		if(y<ymin) y = ymin;
		if(y>ymax) y = ymax;
	}
	
	public void draw(Graphics2D g){
		for (int row = rowOffset; row< rowOffset + numberOfRowsToDraw; row++){
			
			if(row >= numRows) break;
			
			for (int col = columnOffset; col <columnOffset + numberOfColumnsToDraw; col++){
				
				if(col >= numColumns) break;
				
				if(map[row][col] == 0) continue;
				
				int rc = map[row][col];
				int r = rc/numberOfTilesAcross;
				int c = rc % numberOfTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(), (int)x + col * (tileSize * GamePanel.Width)/320, (int)y + row * (tileSize* GamePanel.Height)/240,(tileSize * GamePanel.Width)/320,(tileSize* GamePanel.Height)/240, null); //remove *GamePanel.Height)/240 and such
				
			}
		}
	}
	
	
	
	
}
