package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class FenetrePartie extends JFrame{
	private static final long serialVersionUID = 2656325461540137440L;
	private Table table = new Table();

	public FenetrePartie(){
		super("Hanabi");
		this.setSize(1000, 600);
		this.setResizable(false);
		
		ImageIcon woodBG = new ImageIcon("ressources/wood.jpg");
		JLabel background=new JLabel(new ImageIcon(woodBG.getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH)));
			
		JLayeredPane lp = new JLayeredPane();
		lp.add(background, JLayeredPane.DEFAULT_LAYER);
		lp.add(this.table, JLayeredPane.PALETTE_LAYER);
		
		this.setContentPane(lp);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		new FenetrePartie();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.clearRect(0,0,this.getWidth(),this.getHeight());
		g.drawImage(new ImageIcon("ressources/wood.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		this.paintComponents(g);
	}
}
