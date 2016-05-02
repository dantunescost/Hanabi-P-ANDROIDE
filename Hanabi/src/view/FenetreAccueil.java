package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.AccueilListener;

public class FenetreAccueil extends JFrame {
	private static final long serialVersionUID = 2959729736391290595L;
	public static String R = System.getProperty("user.dir");
	public Menu menu = new Menu();
	
	public FenetreAccueil(){
		super("Bienvenue au jeu Hanabi");
		this.setSize(800, 520);
		this.setMinimumSize(new Dimension(800,520));
		this.setResizable(false);
		
		if(System.getProperty("os.name").equals("Mac OS X")){
			R += "/Hanabi";
		}
		R += "/ressources/";

		this.addMouseListener(new AccueilListener(this));
		
		this.menu.addListeners(this);
		this.setJMenuBar(menu);
		menu.savePartie.setEnabled(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void paint(Graphics g){
		g.clearRect(0,0,this.getWidth(),this.getHeight());
		this.paintComponents(g);
		System.out.println(R+"fireworks.png");
		g.drawImage(new ImageIcon(R+"fireworks.png").getImage(), 0, 20, this.getWidth(), this.getHeight()-20, this);
		g.drawImage(new ImageIcon(R+"upmc.png").getImage(), this.getWidth()-150, this.getHeight()-50, 150, 50, this);
		g.drawImage(new ImageIcon(R+"nouvellePartie.png").getImage(), this.getWidth()/2-203, (this.getHeight()/4)*3, 193, 40, this);
		g.drawImage(new ImageIcon(R+"chargerPartie.png").getImage(), this.getWidth()/2+10, (this.getHeight()/4)*3, 184, 40, this);
	}
	
	public static void main(String[] args){
		new FenetreAccueil();
	}
}
