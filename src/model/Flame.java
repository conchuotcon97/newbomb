package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;

import view.MainView;

public class Flame extends Entity {
	public static final int FLAME_TIME = 100;
	MainView view;

	public Flame(Position position, EntityManager manager) {
		super(position, manager);
		image = new ImageIcon("images/flamee.png").getImage();
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, position.getX(), position.getY(), WIDTH, HEIGHT, null);

	}

	public List<Entity> getCollision() {
		List<Entity> listEntityConlision = new Vector<>();
		Iterator<Entity> it = manager.getBoundsList(this).iterator();
		while (it.hasNext()) {
			Entity en = it.next();

			if (en instanceof Brick) {
				listEntityConlision.add(en);
			}
			if (en instanceof Monster) {
				listEntityConlision.add(en);
			}
			if (en instanceof Wall) {
				listEntityConlision.add(this);
			}
			if(en instanceof Player){
				listEntityConlision.add(en);
				
			}
			
		}
		return listEntityConlision;
	}

}
