package model;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Target extends Entity {
	public Target(Position p,EntityManager en) {
		super(p,en);
		 image = new ImageIcon("####").getImage();
		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
