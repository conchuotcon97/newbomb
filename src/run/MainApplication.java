package run;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import controller.GameController;

public class MainApplication extends JFrame {
	public MainApplication() {
		
		JButton newgame = new JButton("");
		newgame.setIcon(new ImageIcon("images/playGamebtn.png"));
		
		newgame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController con = new GameController();
				setVisible(false);
			}
		});
			
		newgame.setBounds(320, 150, 125, 42);
		background background = new background();
		background.setBounds(0, 0, 500, 500);
		JLayeredPane pane = new JLayeredPane();
				pane.add(newgame,2,0);
				pane.add(background,1,0);
				
		getContentPane().add(pane);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	class background extends JLayeredPane{
		
		
		Image background = new ImageIcon("images/open.png").getImage();
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(background, 0, 0, 500, 500, null);
		}
	}
	public static void main(String[] args) {
		MainApplication view = new MainApplication();
	}
}
