package singleton;


import javax.swing.ImageIcon;

public class Image {
	private static Image ImageSingleton = new Image();
	private static ImageIcon wallImg;
	private static ImageIcon bombImg;
	private static ImageIcon playerLeft;
	private static ImageIcon playerRight;
	private static ImageIcon playerUp;
	private static ImageIcon playerDown;
	private static ImageIcon brickImg;
	private static ImageIcon monsterImg;
	private static ImageIcon bomberman;
	private  Image() {
		wallImg = new ImageIcon("images/brick.jpg");
		bombImg = new ImageIcon("images/bomb-1602109_640.png");
		monsterImg = new ImageIcon("images/monster.png");
		brickImg = new ImageIcon("images/wall1.jpg");
		playerLeft = new ImageIcon("images/playerLeft.png");
		playerRight = new ImageIcon("images/playerRight.png");
		playerUp = new ImageIcon("images/playerUp.png");
		playerDown = new ImageIcon("images/player.png");
		bomberman = new ImageIcon("images/player.png");
		
	}
}
