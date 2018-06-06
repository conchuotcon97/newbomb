package model;

import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class Brick extends Entity {

		
	public Brick(Position position, EntityManager manager) {
		super(position, manager);
		image = new ImageIcon("images/wall1.jpg").getImage();
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, position.getX(), position.getY(), WIDTH, HEIGHT, null);
	}




}
