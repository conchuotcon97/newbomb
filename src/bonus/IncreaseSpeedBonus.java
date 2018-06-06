package bonus;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import model.EntityManager;
import model.MovealeObject;
import model.Player;
import model.Position;

public class IncreaseSpeedBonus extends BonusComponent{
	private MovealeObject player;
	
	
	public IncreaseSpeedBonus(Position po, EntityManager manager, MovealeObject player) {
		super(po,manager);
		this.player = player;
		image = new ImageIcon("image/bonus_speed.png").getImage();
	}


	@Override
	public void bonnus() {
		if (player instanceof Player) {
			int speed = ((Player) player).getSpeed();
			speed += 50;
			((Player) player).setSpeed(speed);		}
	}




	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g ;
		g2d.drawImage(image, this.getPosition().getX(), this.getPosition().getY(), WIDTH, HEIGHT, null);
		
	}


}
