package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

public class Bomb extends Entity implements Runnable {
	public static final int BOMB_TIME = 2000;
	private int RANGE = 5;

	public Bomb(Position position, EntityManager manager) {
		super(position, manager);
		image = new ImageIcon("images/bomb-1602109_640.png").getImage();
		new Thread(this).start();
	}

	public int getRANGE() {
		return RANGE;
	}

	public void setRANGE(int rANGE) {
		RANGE = rANGE;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, position.getX(), position.getY(), WIDTH, HEIGHT, null);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(BOMB_TIME);
			int x = this.position.getX();
			int y = this.position.getY();
			List<Entity> listFlame = new ArrayList<>();
			for (int i = x - 50 * (RANGE / 2); i <= x + (RANGE / 2 )* 50; i += 50) {
				Position p= new Position(i, y);
				listFlame.add(new Flame(new Position(i, y), manager));
			}
			for (int i = y - 50 * (RANGE / 2); i <= y + 50 *( RANGE / 2); i += 50) {
				listFlame.add(new Flame(new Position(x, i), manager));
			}
			// add list flame vao sau khi bomb no
			Thread.sleep(1000);
			manager.addEntity(listFlame);
			
			manager.removeEntity(this);
			setChanged();
			notifyObservers();

			Thread.sleep(2000);
			// delete list flame
			manager.removeEntity(listFlame);
			// setChanged();
			// notifyObservers();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Bomb b = new Bomb(new Position(200, 200), new EntityManager());
		for (int i = b.getPosition().getX() - 50 * (b.RANGE / 2); i <= b.getPosition().getX()
				+ (b.RANGE / 2) * 50; i += 50) {
			System.out.println(i);
		}
		// System.out.println((int) (b.RANGE / 2));
		List<Bomb>lst= new ArrayList<>();
		lst.stream().forEach();
	}

}
