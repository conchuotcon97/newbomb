package model;

import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import bonus.BonusComponent;

public class Player extends MovealeObject implements Observer {
	private boolean isDie  ;
	private int maxBomb;
	private int numberOfBombWasCreated;

	public Player(Position position, EntityManager manager, Direction direction, int speed, int maxBomb,
			int numberOfBombWasCreated, boolean isDie) {
		super(position, manager, direction, speed);
		this.maxBomb = maxBomb;
		this.numberOfBombWasCreated = numberOfBombWasCreated;
		this.image = new ImageIcon("images/playerUp.png").getImage();
		this.setDie(false);
		setMoveBehavior(new PlayerMove(this));
	}
	public Player() {

	}
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		switch (this.direction) {
		case N:
			g2.drawImage(new ImageIcon("images/player.png").getImage(), position.getX(), position.getY(), WIDTH,
					HEIGHT, null);
			break;
		case S:
			g2.drawImage(image, position.getX(), position.getY(), WIDTH, HEIGHT, null);
			break;
		case E:
			g2.drawImage(new ImageIcon("images/playerRight.png").getImage(), position.getX(), position.getY(), WIDTH,
					HEIGHT, null);
			break;
		case W:
			g2.drawImage(new ImageIcon("images/playerLeft.png").getImage(), position.getX(), position.getY(), WIDTH,
					HEIGHT, null);
			break;
		default:
			break;
		}
	}

//	@Override
//	public void move() {
//		
//		
//		Rectangle rect = null;
//		switch (this.direction) {
//		case N:
//			rect = new Rectangle(this.position.getX(), this.position.getY() - this.speed, WIDTH, HEIGHT);
//
//			break;
//		case S:
//			rect = new Rectangle(this.position.getX(), this.position.getY() +this.speed, WIDTH, HEIGHT);
//
//			break;
//		case E:
//			rect = new Rectangle(this.position.getX() +  this.speed, this.position.getY(), WIDTH, HEIGHT);
//
//			break;
//		case W:
//			rect = new Rectangle(this.position.getX() -  this.speed, this.position.getY(), WIDTH, HEIGHT);
//
//			break;
//
//		default:
//			break;
//		}
//		List<Entity> list = manager.getEntityFromBound(rect);
//		System.out.println(list.toString());
//		
//		Iterator<Entity> listInRectangle = manager.getEntityFromBound(rect).iterator();
//		while (listInRectangle.hasNext()) {
//			Entity e = listInRectangle.next();
//			if (e instanceof Wall || e instanceof Brick || e instanceof Bomb) {
//					return;
//			}
//		}
//
//		switch (this.direction) {
//		case N:
//			this.position.increateY(this.speed);
//			break;
//		case S:
//			this.position.decreateY(this.speed);
//			break;
//		case E:
//			this.position.increateX(this.speed);
//			break;
//		case W:
//			this.position.decreateX(this.speed);
//			break;
//		default:
//			break;
//		}
//	}

	@Override
	public void startMove() {

	}

	@Override
	public void stopMove() {
		speed = 0;

	}

	public void putBoom() {
		if (numberOfBombWasCreated < maxBomb) {
			Bomb bomb = new Bomb(new Position(this.position.getX(), this.position.getY()), manager);
			manager.addEntity(bomb);

			((Observable) bomb).addObserver(this);
			numberOfBombWasCreated++;
			System.out.println(maxBomb + " Player Bomb current ");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		numberOfBombWasCreated--;
	}
	public boolean canmove(Position p){
//		boolean canmove = true;
//		Rectangle bounds = new Rectangle(p.getX(), p.getY(), WIDTH, HEIGHT);
		Entity en = manager.getEntityFromPosition(p);
		if(en instanceof Wall || en instanceof Brick || en instanceof Bomb)
			return false;
		return true;
		
	}
	public boolean playerCollision() {
		Iterator<Entity> it = manager.getBoundsList(this).iterator();
		boolean canmove = true;
		while (it.hasNext()) {
			Entity en = it.next();
			
			if (en instanceof Monster) {
				setDie(isDie);
				canmove = false;
//				this.setPosition(new Position(50, 50));
//				return true;
			}
			if (en instanceof Flame) {
				setDie(isDie);
				canmove = false;
//				this.setPosition(new Position(50, 50));
//				return true;
			}
			if ( en instanceof Wall || en instanceof Brick || en instanceof Bomb) {
				List<Entity> list = manager.getBoundsList(this);
				canmove = false;
				System.out.println(list.toString() );
				System.out.println(en  + " entity ");
			}
	}
		return canmove;
	}
	@Override
	public void bonus() {
		Iterator<Entity> it = manager.getBoundsList(this).iterator();
		while (it.hasNext()) {
			Entity en = it.next();
			if (en instanceof BonusComponent) {
				BonusComponent bonus = (BonusComponent) en;
				bonus.bonnus();
				manager.removeEntity(en);
			}
		}
	}

	public boolean isDie() {
		return isDie;
	}

	public void setDie(boolean isDie) {
		this.isDie = isDie;
	}
	public int getMaxBomb() {
		return maxBomb;
	}
	public void setMaxBomb(int maxBomb) {
		this.maxBomb = maxBomb;
	}
	public int getNumberOfBombWasCreated() {
		return numberOfBombWasCreated;
	}
	public void setNumberOfBombWasCreated(int numberOfBombWasCreated) {
		this.numberOfBombWasCreated = numberOfBombWasCreated;
	}

	

	


	

//	public List<Entity> getCollision() {
//		List<Entity> listCollision = new ArrayList<>();
//
//		Iterator<Entity> it = manager.getBoundsList(this).iterator();
//		while (it.hasNext()) {
//			Entity en = it.next();
//			if (en instanceof Wall) {
//				
//			}
//			
//			
//		}
//		
//		
//		
//		return listCollision;
//	}
	
	
	
}
