package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;

import bonus.IncreaseSpeedBonus;
import bonus.MoreBombBonus;

public class Monster extends MovealeObject implements Runnable, Observer {
	private Thread thread;
	private boolean isDie;
	private int maxBomb;
	private int numberOfBombWasCreated;

	public Monster(Position position, EntityManager manager, Direction direction, int speed, int maxBomb,
			int numberOfBombWasCreated, boolean isDie) {
		super(position, manager, direction, speed);
		this.maxBomb = maxBomb;
		this.numberOfBombWasCreated = numberOfBombWasCreated;
		this.setDie(false);
		image = new ImageIcon("images/monster.png").getImage();
		setMoveBehavior(new MonsterMove(this));
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, position.getX(), position.getY(), WIDTH, HEIGHT, null);
	}

	// @Override
	// public void move() {
	// switch (this.direction) {
	// case N:
	// this.position.decreateY(this.speed);
	// break;
	// case S:
	// this.position.increateY(this.speed);
	// break;
	// case E:
	// this.position.increateX(this.speed);
	// break;
	// case W:
	// this.position.decreateX(this.speed);
	// break;
	// default:
	// break;
	// }
	// }

	@Override
	public void startMove() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void stopMove() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}

	public boolean perpendicular(Position pos1, Position pos2) {
		return (pos1.getX() == pos2.getX()) || (pos1.getY() == pos2.getY());
	}

	// Returns true if the position is in range of any bomb's explosion
	private boolean inExplosionRange() {
		for (Entity entity : manager.getList()) {
			if (entity instanceof Flame) {
				return position.getX() == entity.getPosition().getX() && position.getY() == entity.getPosition().getY();
			}
			if (entity instanceof Bomb) {
				Bomb bomb = (Bomb) entity;
				if (perpendicular(this.getPosition(), bomb.getPosition())
						&& Math.abs(position.getX() - bomb.getPosition().getX()) < 50 * bomb.getRANGE()
						|| Math.abs(position.getY() - bomb.getPosition().getY()) < 50 * bomb.getRANGE()) {
					return true;
				}
			}
		}
		return false;
	}

	// Returns true if a bomb at position bomb_pos with range range will hit
	// position pos
	public boolean inBombRange(Position pos, Position bomb_pos, int range) {
		return perpendicular(pos, bomb_pos)
				&& (range * range) >= getDistanceSquared(pos.getX(), pos.getY(), bomb_pos.getX(), bomb_pos.getY());
	}

	// Returns the distance between two points squared
	private int getDistanceSquared(int x1, int y1, int x2, int y2) {
		return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
	}

	// Returns the position of the closest bomb which can damage the player at
	// player
	public Position getClosestBombInRange() {

		int min_distance_squared = 11 * 16 * 50 * 50;
		int distance_squared;
		int x;
		int y;
		if (this.getPosition().getX() < 11 * 50 / 2) {
			x = 11 * 50 - 50;
		} else {
			x = 0;
		}
		if (this.getPosition().getY() < 16 * 50 / 2) {
			y = 16 * 50 - 50;
		} else {
			y = 0;
		}
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 16; j++) {
				Position curPos = new Position(i * 50, j * 50);
				if (manager.getEntityFromPosition(curPos) == null && inBombRange(this.getPosition(), curPos, 3)) {
					distance_squared = getDistanceSquared(this.getPosition().getX(), this.getPosition().getY(),
							curPos.getX(), curPos.getY());
					if (distance_squared < min_distance_squared) {
						min_distance_squared = distance_squared;
						x = curPos.getX();
						y = curPos.getY();
					}
				}
			}
		}
		return new Position(x, y);

	}

	// // Returns the position of the closest powerup to the player at player
	// Position getClosestPowerup(Player player) {
	// int min_distance_squared = 11 * 16*50*50;
	// int distance_squared;
	// int x;
	// int y;
	// if (player.getPosition().getX() < 11*50 / 2) {
	// x = 11*50 - 50;
	// } else {
	// x = 0;
	// }
	// if (player.getPosition().getY() < 16*50 / 2) {
	// y = 16*50-50;
	// } else {
	// y = 0;
	// }
	// for (int i = 0; i < 11*50; i++) {
	// for (int j = 0; j < 16*50; j++) {
	// if (items_grid[i][j] == ITEM_SPEED_UP || items_grid[i][j] == ITEM_RANGE_UP ||
	// items_grid[i][j] == ITEM_BOMBS_UP) {
	// distance_squared = getDistanceSquared(player.getPosition().getX(),
	// player.getPosition().getY(), i*50, j*50);
	// if (distance_squared < min_distance_squared) {
	// min_distance_squared = distance_squared;
	// x = i*50;
	// y = j*50;
	// }
	// }
	// }
	// }
	// return new Position(x, y);
	// }

	// Returns the direction the ai player should move in to approach a powerup or
	// player
	public Direction getApproachMove(Position pos) {
		if (pos.getX() - this.getPosition().getX() > 0) {
			if (monstercanmove(pos)) {
				return Direction.E;
			}
		} else if (pos.getX() - this.getPosition().getX() < 0) {
			if (monstercanmove(pos)) {
				return this.getDirection();
			}
		}
		if (pos.getY() - this.getPosition().getY() < 0) {
			if (monstercanmove(pos)) {
				return Direction.N;
			}
		} else if (pos.getY() - this.getPosition().getY() > 0) {
			if (monstercanmove(pos)) {
				return Direction.S;
			}
		}
		return this.getDirection();

	}

	public boolean checkUp(Position pos) {
		if (manager.getEntityFromPosition(new Position(pos.getX(), pos.getY() - 50)) == null)
			return true;
		return false;
	}

	public boolean checkDown(Position pos) {
		if (manager.getEntityFromPosition(new Position(pos.getX(), pos.getY() + 50)) == null)
			return true;
		return false;

	}

	public boolean checkLeft(Position pos) {
		if (manager.getEntityFromPosition(new Position(pos.getX() - 50, pos.getY())) == null)
			return true;
		return false;

	}

	public boolean checkRight(Position pos) {
		if (manager.getEntityFromPosition(new Position(pos.getX() + 50, pos.getY())) == null)
			return true;
		return false;

	}

	public Direction avoidBombMove() {
		Position pos = getClosestBombInRange();
		if (pos.getX() == this.getPosition().getX() && pos.getY() == this.getPosition().getY()) {
			if (checkRight(this.getPosition())) {
				return Direction.E;
			}
			if (checkLeft(this.getPosition())) {
				return Direction.W;
			}
			if (checkUp(this.getPosition())) {
				return Direction.N;
			}
			if (checkDown(this.getPosition())) {
				return Direction.S;
			}
		} else if (pos.getY() == this.getPosition().getY()) {
			if (checkUp(this.getPosition())) {
				return Direction.N;
			}
			if (checkDown(this.getPosition())) {
				return Direction.S;
			}
			if (pos.getX() - this.getPosition().getX() > 0) {
				if (checkLeft(this.getPosition())) {
					return Direction.W;
				}
			}
			if (pos.getX() - this.getPosition().getX() < 0) {
				if (checkRight(this.getPosition())) {
					return Direction.E;
				}
			}
		} else if (pos.getX() == this.getPosition().getX()) {
			if (checkRight(this.getPosition())) {
				return Direction.E;
			}
			if (checkLeft(this.getPosition())) {
				return Direction.W;
			}
			if (pos.getY() - this.getPosition().getY() > 0) {
				if (checkUp(this.getPosition())) {
					return Direction.N;
				}
			}
			if (pos.getY() - this.getPosition().getY() < 0) {
				if (checkDown(this.getPosition())) {
					return Direction.S;
				}
			}
		}
		return this.direction;
	}

	public String getPlan() {
		// If the ai player is moving from one tile to another its direction won't
		// change (i.e. only changes movement when on a tile)
		// if (!isAligned(player->screen_position.x, player->screen_position.y)) {
		// return IDLE;
		// }

		// If the ai player is in a bomb's range it will try to avoid the bomb
		if (inExplosionRange()) {
			return "AVOID_BOMB";
		}

		// If the ai player is closer to a player/powerup it will approach the
		Position playerPos = manager.getPlayer().getPosition();
		Position closestPowerup = getClosestPowerup();
		if (getDistanceSquared(this.position.getX(), this.position.getY(), closestPowerup.getX(),
				closestPowerup.getY()) < getDistanceSquared(this.position.getX(), this.position.getY(),
						playerPos.getX(), playerPos.getY())) {
			return "GET_POWERUP";
		} else {
			return "APPROACH_PLAYER";
		}

	}

	private Position getClosestPowerup() {
		int min_distance_squared = 11 * 16 * 50 * 50;
		int distance_squared;
		int x;
		int y;
		if (this.getPosition().getX() < 11 * 50 / 2) {
			x = 11 * 50 - 50;
		} else {
			x = 0;
		}
		if (this.getPosition().getY() < 16 * 50 / 2) {
			y = 16 * 50 - 50;
		} else {
			y = 0;
		}
		for (int i = 0; i < 11 * 50; i++) {
			for (int j = 0; j < 16 * 50; j++) {
				Entity en = manager.getEntityFromPosition(new Position(i * 50, j * 50));
				if (en instanceof IncreaseSpeedBonus || en instanceof MoreBombBonus) {
					distance_squared = getDistanceSquared(this.position.getX(), this.position.getY(), i * 50, j * 50);
					if (distance_squared < min_distance_squared) {
						min_distance_squared = distance_squared;
						x = i * 50;
						y = j * 50;
					}
				}
			}
		}
		return new Position(x, y);
	}

	// Returns true if a block adjacent to pos is breakable
	public boolean breakableAdjacent(Position pos) {
		for (int i = -1; i < 2; i++) {
			if (pos.getX() + i * speed >= 0 && pos.getX() + i * speed < 11 * 50) {
				Entity en = manager.getEntityFromPosition(new Position(pos.getX() + i * speed, pos.getY()));
				if (en instanceof Brick) {
					return true;
				}

			}
		}
		for (int j = -1; j < 2; j++) {
			if (pos.getX() + j * 50 >= 0 && pos.getY() + j * speed < 16 * 50) {
				Entity en = manager.getEntityFromPosition(new Position(pos.getX(), pos.getY() + j * speed));
				if (en instanceof Brick) {
					return true;
				}
			}
		}
		return false;
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

	public Direction get_ai_dir() {
		Direction dir = Direction.MiD;
		switch (getPlan()) {
		case "GET_POWERUP": {
			// dir = getApproachMove(getClosestPowerup());
			Position target = getClosestPowerup();
			List<Direction> lst = getPathDir(target);
			dir = lst.get(0);
			lst.remove(0);
			if (lst.isEmpty()) {
				manager.removeEntity(manager.getEntityFromPosition(target));
			}
			// this.putBoom();
			// dropBombIfBreakable(manager.getPlayer());
			break;
		}
		case "APPROACH_PLAYER": {
			Position target = manager.getPlayer().getPosition();
			List<Direction> lst = getPathDir(target);
			dir = lst.get(0);
			lst.remove(0);
			if(lst.size()<=2) {
			// dir = getApproachMove(pos);
			 this.putBoom();
			}
			// if (inBombRange(pos, player->tile_position, player->bomb.range)){
			// player->plant_bomb = true;
			// }
			break;
		}
		case "AVOID_BOMB": {
			 dir = avoidBombMove();
			break;
		}
		}
		return dir;
	}

	public List<Direction> getPathDir(Position pos) {
		List<Direction> result = new ArrayList<>();
		AStarAlgorithm aStar = new AStarAlgorithm(manager.theBrickArr(), this.getPosition().getY() / 50,
				this.getPosition().getX() / 50, pos.getY() / 50, pos.getX() / 50);
		List<Node> lst = aStar.findPath();
		for (int i = 0; i < lst.size() - 1; i++) {
			int j = i + 1;
			if (lst.get(i).getRow() - lst.get(j).getRow() < 0 && lst.get(i).getCol() - lst.get(j).getCol() == 0) {
				result.add(Direction.N);
			}
			if (lst.get(i).getRow() - lst.get(j).getRow() > 0 && lst.get(i).getCol() - lst.get(j).getCol() == 0) {
				result.add(Direction.S);
			}
			if (lst.get(i).getRow() - lst.get(j).getRow() == 0 && lst.get(i).getCol() - lst.get(j).getCol() > 0) {
				result.add(Direction.W);
			}
			if (lst.get(i).getRow() - lst.get(j).getRow() == 0 && lst.get(i).getCol() - lst.get(j).getCol() < 0) {
				result.add(Direction.E);
			}
		}

		return result;
	}

	@Override
	public void run() {
		Random r = new Random();

		// int times = 0;
		while (true) {
			move();
			setDirection(get_ai_dir());
			manager.notifyChanged();
			// times++;
			// if (times == 20) {
			// this.setSpeed(this.speed * 2);
			// times = 0;
			// }
			try {
				Thread.sleep(1 / 1000000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void bonus() {
		// TODO Auto-generated method stub

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

}
