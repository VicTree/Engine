package tileMap;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Tile {

	private BufferedImage image;
	private int type;
	
	//tile types
	public static final int NORMAL = 0;
	public static final int BLOCKED= 1;
	
	public Tile(BufferedImage image, int type){
		this.image = resize(image,(image.getWidth() * GamePanel.Width)/320,(image.getHeight() * GamePanel.Height)/240); //this.image = image; 
		//this.image = (BufferedImage) image.getScaledInstance((image.getWidth() * GamePanel.Width)/320,(image.getHeight() * GamePanel.Height)/240, Image.SCALE_SMOOTH);
		this.type = type;
	}
	
	public BufferedImage getImage(){
		return image;
	} 
	
	public int getType(){
		return type;
	}
	
	private BufferedImage resize(BufferedImage img, int newW, int newH) { 
		BufferedImage resizedImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB); 
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, newW, newH, null);
		g.dispose();
		return resizedImage;
	}  
	
}
