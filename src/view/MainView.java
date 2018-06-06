package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GameController;
import model.EntityManager;
import model.Monster;

public class MainView extends JFrame implements Observer{
	 EntityManager entityManger;
	 GameController gameController;
	JPanel mainContent;
	
	public MainView(EntityManager entityManager, GameController gameController) {
		super();
		this.entityManger = entityManager;
		this.gameController = gameController;
		((Observable) entityManager).addObserver(this);
		
		mainContent = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				entityManager.collisionable();
				if (entityManager.gameOver() == true) {
					gameController.stop();
					gameOver();
					System.exit(ABORT);
//					entityManger.getPlayer().setDie(false);
				}
				entityManager.bonus();
				entityManger.draw(g);
			}
		};
		
		mainContent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				gameController.keyRelease(e.getKeyCode());
				System.out.println(e.getKeyCode() + " key code");
			}
		});
		
		mainContent.setFocusable(true);
		mainContent.setBackground(Color.WHITE);
		getContentPane().add(mainContent, BorderLayout.CENTER);
		this.setSize(16*50, 11*50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		gameController.start();
		
	}
	
	
	
	public void initView(){
		
		
	}


	@Override
	public void update(Observable o, Object arg) {
		mainContent.repaint();
		
	}
	public void gameOver() {
			JOptionPane.showMessageDialog(mainContent, "GameOver");
//			entityManger.getPlayer().setDie(false);
			System.out.println("chet roi ko dc choi nua");
	}
}
