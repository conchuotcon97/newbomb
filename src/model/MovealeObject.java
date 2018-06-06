package model;

import bonus.BonusComponent;

public abstract class MovealeObject extends Entity {
	protected Direction direction;
	protected int speed;
	protected MoveBehavior moveBehavior;
	protected BonusComponent bonus;

	public MovealeObject(Position position, EntityManager manager, Direction direction, int speed) {
		super(position, manager);
		this.direction = direction;
		this.speed = speed;
		image = null;
		
	}
	public MovealeObject() {
		// TODO Auto-generated constructor stub
	}
	public abstract void bonus() ;
		
	

	public Direction getDirection() {
		return direction;
	}
	
	public void setMoveBehavior(MoveBehavior move) {
		this.moveBehavior = move;
	}
	public  void move(){
		moveBehavior.move();
	}
	public boolean monstercanmove(Position p){
		Entity en = manager.getEntityFromPosition(p);
		if(en instanceof Wall || en instanceof Brick || en instanceof Bomb)
			return false;
		return true;
		
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public abstract void startMove();

	public abstract void stopMove();

}
