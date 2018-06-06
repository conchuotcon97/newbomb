package model;

public class MonsterMove implements MoveBehavior {
	private Monster monster;

	public MonsterMove(Monster monster) {
		this.monster = monster;
	}

	@Override
	public void move() {
//		switch (monster.direction) {
//		case N:
//			monster.position.decreateY(monster.speed);
//			break;
//		case S:
//			monster.position.increateY(monster.speed);
//			break;
//		case E:
//			monster.position.increateX(monster.speed);
//			break;
//		case W:
//			monster.position.decreateX(monster.speed);
//			break;
//		default:
//			break;
//		}
		if (monster.direction.equals(Direction.N) && monster.monstercanmove(new Position(monster.position.getX(), monster.position.getY() + monster.speed)))   {
			monster.position.increateY(monster.speed);
		}
		if (monster.direction.equals(Direction.S) && monster.monstercanmove(new Position(monster.position.getX(), monster.position.getY() - monster.speed))  ) {
			monster.position.decreateY(monster.speed);
			
		}
		if (monster.direction.equals(Direction.E) && monster.monstercanmove(new Position(monster.position.getX() + monster.speed, monster.position.getY() ))  ) {
			monster.position.increateX(monster.speed);
		}
		if (monster.direction.equals(Direction.W) && monster.monstercanmove(new Position(monster.position.getX() - monster.speed, monster.position.getY() ))  ) {
			monster.position.decreateX(monster.speed);
		}
	
	}

}
