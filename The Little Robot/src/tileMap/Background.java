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
		g.drawImage(resize(image, GamePanel.Width, GamePanel.Height), (int)x, (int) y, null);
		if(x<0){
			g.drawImage(resize(image, GamePanel.Width, GamePanel.Height),(int)x + GamePanel.Width, (int)y, null);
			
		}
		if(x>0){
			g.drawImage(resize(image, GamePanel.Width, GamePanel.Height),(int)x - GamePanel.Width, (int)y, null);
			
		}
	}
	
	private BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
}
