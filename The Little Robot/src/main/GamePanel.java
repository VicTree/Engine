package main;

import gameState.Manager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	//screen dimensions
	public static int Width = 1920;
	public static int Height = 1080;
	
	//game thread
	private Thread gameThread;
	private boolean isRunning;
	private int FPS = 60;
	private long targetTime = 1000/FPS;
	
	//image to draw on
	private BufferedImage image;
	private Graphics2D g;
	
	//gameState Manager
	private Manager manager;
	
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(Width, Height));
		setFocusable(true);
		requestFocus();
	
	}
	
	//Just notifies that the JPanel is loaded and runnning
	public void addNotify(){
		super.addNotify();
		if (gameThread == null){
			gameThread = new Thread(this);
			addKeyListener(this);
			gameThread.start();
		}
	}
	
	private void init(){
		image = new BufferedImage(Width, Height,
				BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		isRunning = true;
		
		manager = new Manager();
	}
	
	//game loop
	@Override
	public void run(){
		init();
		long start;
		long elapsed;
		long wait;
		
		
		while (isRunning){
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if (wait < 0)
				wait = 5;
			
			try{
				Thread.sleep(wait);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void update(){
		manager.update();
	}
	
	private void draw(){
		manager.draw(g);
	}
	
	private void drawToScreen(){
	
		Graphics g2 = getGraphics();
		g2.drawImage(resize(image, getWidth(), getHeight()), 0, 0, null);
		g2.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		manager.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		manager.keyReleased(e.getKeyCode());

	}
	
	private BufferedImage resize(BufferedImage img, int newW, int newH) { 
//	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
//	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
//
//	    Graphics2D g2d = dimg.createGraphics();
//	    g2d.drawImage(tmp, 0, 0, null);
//	    g2d.dispose();
//
//	    return dimg;
		BufferedImage resizedImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB); 
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, newW, newH, null);
		g.dispose();
		return resizedImage;
	}  

	
	
}
