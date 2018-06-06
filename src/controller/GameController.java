package controller;

import java.awt.event.KeyEvent;

import model.Direction;
import model.EntityManager;
import view.MainView;

public class GameController {
	EntityManager entityManger;
	MainView view;
	
	public GameController() {
		entityManger = new EntityManager();
		view = new MainView(entityManger, this);
	}
	public void start(){
		entityManger.startMoveableObject();
	}
	public void stop() {
		entityManger.stopMoveableObject();
	}
	
	public void keyRelease(int keyCode){
		switch (keyCode) {
		case KeyEvent.VK_UP:
			entityManger.move(Direction.S);
			break;
		case KeyEvent.VK_LEFT:
			entityManger.move(Direction.W);
			break;
		case KeyEvent.VK_DOWN:
			entityManger.move(Direction.N);
			break;
		case KeyEvent.VK_RIGHT:
			entityManger.move(Direction.E);
			break;
		case KeyEvent.VK_SPACE:
			entityManger.putBoom();
			break;
		case KeyEvent.VK_W:
			entityManger.move2(Direction.S);
			break;
		case KeyEvent.VK_A:
			entityManger.move2(Direction.W);
			break;
		case KeyEvent.VK_S:
			entityManger.move2(Direction.N);
			break;
		case KeyEvent.VK_D:
			entityManger.move2(Direction.E);
			break;
		case KeyEvent.VK_SHIFT:
			entityManger.putBoom2();
			break;
		
			
		default:
			break;
		}
	}
		
}
