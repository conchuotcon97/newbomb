package model;


public class PlayerMove implements MoveBehavior{
	private Player player;

	public PlayerMove(Player player) {
		this.player = player;
	}
	@Override
	public void move() {
		if (player.direction.equals(Direction.N) && player.canmove(new Position(player.position.getX(), player.position.getY() + player.speed)))   {
			player.position.increateY(player.speed);
		}
		if (player.direction.equals(Direction.S) && player.canmove(new Position(player.position.getX(), player.position.getY() - player.speed))  ) {
			player.position.decreateY(player.speed);
			
		}
		if (player.direction.equals(Direction.E) && player.canmove(new Position(player.position.getX() + player.speed, player.position.getY() ))  ) {
			player.position.increateX(player.speed);
		}
		if (player.direction.equals(Direction.W) && player.canmove(new Position(player.position.getX() - player.speed, player.position.getY() ))  ) {
			player.position.decreateX(player.speed);
		}
		
	}
//	public Position frontUp() {
////		player.position.decreateY(player.speed );
//		int newY = player.position.getY() - player.speed;
//		Position pos = new Position(player.getPosition().getX(), newY);
//		return pos;
//	}
//	public Position frontDown() {
////		player.position.increateY(player.speed);
//		int newY = player.position.getY() + player.speed;
//		Position pos = new Position(player.getPosition().getX(), newY);
//		return pos;
//	}
//	public Position frontLeft() {
////		player.position.increateX(player.speed);
//		int newX = player.position.getX() + player.speed;
//		Position pos = new Position(newX, this.player.getPosition().getY());
//		return pos;
//	}
//	public Position frontRight() {
//		int newX = player.position.getX() - player.speed;
//		Position pos = new Position(newX, this.player.getPosition().getY());
//		return pos;
//	}
	/**
	 * //		Rectangle rect = null;
//		switch (player.direction) {
//		case N:
//			rect = new Rectangle(player.position.getX(), player.position.getY() - player.speed, player.WIDTH, player.HEIGHT);
//
//			break;
//		case S:
//			rect = new Rectangle(player.position.getX(), player.position.getY() +player.speed, player.WIDTH, player.HEIGHT);
//
//			break;
//		case E:
//			rect = new Rectangle(player.position.getX() +  player.speed, player.position.getY(), player.WIDTH, player.HEIGHT);
//
//			break;
//		case W:
//			rect = new Rectangle(player.position.getX() -  player.speed, player.position.getY(), player.WIDTH, player.HEIGHT);
//
//			break;
//
//		default:
//			break;
//		}

	//	 dung do 
//		EntityManager manager = player.getManager();
//		List<Entity> list = manager.getBoundsList(player);
//		System.out.println(list.toString());
//		
//		Iterator<Entity> listInRectangle = list.iterator();
//		while (listInRectangle.hasNext()) {
//			Entity e = listInRectangle.next();
//			if (e instanceof Wall || e instanceof Brick || e instanceof Bomb) {
//				System.out.println("Truoc mat co fai la tuong ");
//				player.setSpeed(0);
//			}
//		}
//		System.out.println(player.getSpeed() + "   speed");
//		switch (player.direction) {
//		case N:
//			player.position.increateY(colli());
//			break;
//		case S:
//			player.position.decreateY(colli());
//			break;
//		case E:
//			player.position.increateX(colli());
//			break;
//		case W:
//			player.position.decreateX(colli());
//			break;
//		default:
//			break;
//		}
<<<<<<< HEAD
		switch (player.direction) {
		case N:
			player.position.increateY(player.speed);
//			down();
			break;
		case S:
			player.position.decreateY(player.speed);
//			up();
			break;
		case E:
			player.position.increateX(player.speed);
//			left();
			break;
		case W:
			player.position.decreateX(player.speed);
//			right();
			break;
		default:
			break;
		}
	}

	public Position frontUp() {
//		player.position.decreateY(player.speed );
		int newY = player.position.getY() - player.speed;
		Position pos = new Position(player.getPosition().getX(), newY);
		return pos;
	}
	public Position frontDown() {
//		player.position.increateY(player.speed);
		int newY = player.position.getY() + player.speed;
		Position pos = new Position(player.getPosition().getX(), newY);
		return pos;
	}
	public Position frontLeft() {
//		player.position.increateX(player.speed);
		int newX = player.position.getX() + player.speed;
		Position pos = new Position(newX, this.player.getPosition().getY());
		return pos;
	}
	public Position frontRight() {
		int newX = player.position.getX() - player.speed;
		Position pos = new Position(newX, this.player.getPosition().getY());
		return pos;
	}
=======
//		switch (player.direction ) {
//		case N:
//			player.position.increateY(player.speed);
////			down();
//			break;
//		case S:
//			player.position.decreateY(player.speed);
////			up();
//			break;
//		case E:
//			player.position.increateX(player.speed);
////			left();
//			break;
//		case W:
//			player.position.decreateX(player.speed);
////			right();
//			break;
//		default:
//			break;
//		}

	 * 
	 * 
	 * 
	 */
		
	}
	


