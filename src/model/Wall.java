package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall extends Entity {

	public Wall(Position p,EntityManager en) {
		super(p,en);
		 image = new ImageIcon("images/brick.jpg").getImage();
		
	}
	@Override
	public String toString() {
		String s = "";
		s = "wall + " +this.getPosition().getX() + "  " + this.getPosition().getY();
		return s;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, position.getX(), position.getY(), WIDTH, HEIGHT, null);
		// doc east
//				g2.drawImage(image, 0,0,50, 40, null);
//				g2.drawImage(image, 0,30,50, 40, null);
//				g2.drawImage(image, 0,60,50, 40, null);
//				g2.drawImage(image, 0,90,50, 40, null);
//				g2.drawImage(image, 0,120,50, 40, null);
//				g2.drawImage(image, 0,150,50, 40, null);
//				g2.drawImage(image, 0,180,50, 40, null);
//				g2.drawImage(image, 0,210,50, 40, null);
//				g2.drawImage(image, 0,240,50, 40, null);
//				g2.drawImage(image, 0,270,50, 40, null);
//				g2.drawImage(image, 0,300,50, 40, null);
//				g2.drawImage(image, 0,330,50, 40, null);
//				g2.drawImage(image, 0,360,50, 40, null);
//				g2.drawImage(image, 0,390,50, 40, null);
//				g2.drawImage(image, 0,420,50, 40, null);
//				g2.drawImage(image, 0,450,50, 40, null);
//				g2.drawImage(image, 0,480,50, 40, null);
//				g2.drawImage(image, 0,510,50, 40, null);
////				g2.drawImage(img, 0,540,50, 40, null);
//				//hang ngang south
//				g2.drawImage(image, 46,0,50, 40, null);
//				g2.drawImage(image, 46*2,0,50, 40, null);
//				g2.drawImage(image, 46*3,0,50, 40, null);
//				g2.drawImage(image, 46*4,0,50, 40, null);
//				g2.drawImage(image, 46*5,0,50, 40, null);
//				g2.drawImage(image, 46*6,0,50, 40, null);
//				g2.drawImage(image, 46*7,0,50, 40, null);
//				g2.drawImage(image, 46*8,0,50, 40, null);
//				g2.drawImage(image, 46*9,0,50, 40, null);
//				g2.drawImage(image, 46*10,0,50, 40, null);
//				g2.drawImage(image, 46*11,0,50, 40, null);
//				g2.drawImage(image, 46*12,0,50, 40, null);
//				g2.drawImage(image, 46*13,0,50, 40, null);
//				g2.drawImage(image, 46*14,0,50, 40, null);
//				g2.drawImage(image, 46*15,0,50, 40, null);
//				g2.drawImage(image, 46*16,0,50, 40, null);
//				//hang ngang north
//				g2.drawImage(image, 46,510,50, 40, null);
//				g2.drawImage(image, 46*2,510,50, 40, null);
//				g2.drawImage(image, 46*3,510,50, 40, null);
//				g2.drawImage(image, 46*4,510,50, 40, null);
//				g2.drawImage(image, 46*5,510,50, 40, null);
//				g2.drawImage(image, 46*6,510,50, 40, null);
//				g2.drawImage(image, 46*7,510,50, 40, null);
//				g2.drawImage(image, 46*8,510,50, 40, null);
//				g2.drawImage(image, 46*9,510,50, 40, null);
//				g2.drawImage(image, 46*10,510,50, 40, null);
//				g2.drawImage(image, 46*11,510,50, 40, null);
//				g2.drawImage(image, 46*12,510,50, 40, null);
//				g2.drawImage(image, 46*13,510,50, 40, null);
//				g2.drawImage(image, 46*14,510,50, 40, null);
//				g2.drawImage(image, 46*15,510,50, 40, null);
//				g2.drawImage(image, 46*16,510,50, 40, null);
//				
//				//hang doc west
//				g2.drawImage(image, 46*16,30,50, 40, null);
//				g2.drawImage(image, 46*16,30*2,50, 40, null);
//				g2.drawImage(image, 46*16,30*3,50, 40, null);
//				g2.drawImage(image, 46*16,30*4,50, 40, null);
//				g2.drawImage(image, 46*16,30*5,50, 40, null);
//				g2.drawImage(image, 46*16,30*6,50, 40, null);
//				g2.drawImage(image, 46*16,30*7,50, 40, null);
//				g2.drawImage(image, 46*16,30*8,50, 40, null);
//				g2.drawImage(image, 46*16,30*9,50, 40, null);
//				g2.drawImage(image, 46*16,30*10,50, 40, null);
//				g2.drawImage(image, 46*16,30*11,50, 40, null);
//				g2.drawImage(image, 46*16,30*12,50, 40, null);
//				g2.drawImage(image, 46*16,30*13,50, 40, null);
//				g2.drawImage(image, 46*16,30*14,50, 40, null);
//				g2.drawImage(image, 46*16,30*15,50, 40, null);
//				g2.drawImage(image, 46*16,30*16,50, 40, null);
//				
//				//center
//				  // row 1
//				g2.drawImage(image, 150,120,50, 40, null);
//				g2.drawImage(image, 150,270,50, 40, null);
//					//row 3
//				g2.drawImage(image, 600,120,50, 40, null);
//				g2.drawImage(image, 600,270,50, 40, null);
//				// row 2
//				g2.drawImage(image, 370 ,120,50, 40, null);
//				g2.drawImage(image, 370 ,270,50, 40, null);
//				// row 4
//				g2.drawImage(image, 150,400,50, 40, null);
//				g2.drawImage(image, 370,400,50, 40, null);
//				g2.drawImage(image, 600,400,50, 40, null);

	}
}