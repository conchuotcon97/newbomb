package bonus;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import model.EntityManager;
import model.MovealeObject;
import model.Player;
import model.Position;

public class MoreBombBonus extends BonusComponent{
	private MovealeObject player;
	

	public MoreBombBonus(Position po, EntityManager manager,MovealeObject player) {
		super(po,manager);
		this.player = player;
		image = new ImageIcon("image/bonus_bomb.png").getImage();
	}


	@Override
	public void bonnus() {
		if (player instanceof Player) {
			int moreBomb = ((Player) player).getMaxBomb();
			moreBomb += 1;
			((Player) player).setMaxBomb(moreBomb);
			System.out.println("More Bomb Bonus + " + moreBomb);
			System.out.println(((Player) player).getMaxBomb());
		}
		
	}




	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D )g;
		g2d.drawImage(image, this.getPosition().getX(), this.getPosition().getY(),WIDTH,HEIGHT, null);
		
	}
	


}
