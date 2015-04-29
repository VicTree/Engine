package tileMap;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Background {

	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String name, double scale){
		
		try{
			image = ImageIO.read(getClass().getResourceAsStream(name));
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y){
		this.x = (x * moveScale) % GamePanel.Width;
		this.y = (y * moveScale) % GamePanel.Height;
		
	}
	
	public void setSpeed(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update(){
		x+=dx;
		y+=dy;
		
	}
	
	public void draw(Graphics2D g){
		//g.drawImage(image.getScaledInstance(GamePanel.Width, GamePanel.Height, Image.SCALE_SMOOTH), (int)x, (int) y, null);

		g.drawImage(image, (int)x, (int) y, GamePanel.Width, GamePanel.Height,  null);
		if(x<0){
			g.drawImage(image,(int)x + GamePanel.Width, (int)y, GamePanel.Width, GamePanel.Height, null);
			//g.drawImage(image.getScaledInstance(GamePanel.Width, GamePanel.Height, Image.SCALE_SMOOTH),(int)x + GamePanel.Width, (int)y, null);

		}
		if(x>0){
			g.drawImage(image, (int)x - GamePanel.Width, (int)y, GamePanel.Width, GamePanel.Height, null);
			//g.drawImage(image.getScaledInstance(GamePanel.Width, GamePanel.Height, Image.SCALE_SMOOTH),(int)x - GamePanel.Width, (int)y, null);

		}
	}
	
//	private BufferedImage resize(BufferedImage img, int newW, int newH) { 
//		BufferedImage resizedImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB); 
//		Graphics2D g = resizedImage.createGraphics();
//		g.drawImage(image, 0, 0, newW, newH, null);
//		g.dispose();
//		return resizedImage;
//	}  
	
}
