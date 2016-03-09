package view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.AdditionMainPleineException;
import model.Partie;
import model.PiocheVideException;

public class FenetrePartie extends JFrame{
	private static final long serialVersionUID = 2656325461540137440L;
	private Table table;
	private Partie partie; 

	public FenetrePartie(Partie p){
		super("Hanabi");
		this.partie = p;
		this.setSize(1000, 600);
		this.setResizable(true);
		
		this.table = new Table();
		
		JPanel bg = new JPanel();/*{
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.clearRect(0,0,this.getWidth(),this.getHeight());
				g.drawImage(new ImageIcon("ressources/wood.jpg").getImage(), 0, 0, 1000, 600, this);
				
			}
		};*/
		bg.setLayout(new FlowLayout());
		bg.add(table);
		this.setContentPane(bg);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void afficherMain(Graphics g){
		int width = 692, height = 365;
		int karteH = height/4;
		int karteW =(int)( (float)karteH*0.645);
		int startX = (this.getWidth() - width) /2 + width/2;
		int startY = (this.getHeight() - height) /2;
		Image karte = new ImageIcon("ressources/karte.jpg").getImage();
		g.drawImage(karte, startX, startY-(karteH/3)*2, karteW, karteH, this);
	}
	
	public void paint(Graphics g){
		//super.paint(g);
		g.clearRect(0,0,this.getWidth(),this.getHeight());
		g.drawImage(new ImageIcon("ressources/wood.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		this.table.paintTable(g, this);
		afficherMain(g);
	}
	
	public Partie getPartie() {
		return partie;
	}

	/************************* MAIN *************************/
	public static void main(String[] args){
		Partie game = new Partie(2,8,false);
	    String[] noms = new String[2];
	    noms[0] = "Holmes";
	    noms[1] = "Watson";
	    try {
			game.initPartie(noms);
		} catch (AdditionMainPleineException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (PiocheVideException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    new FenetrePartie(game);
	}
}
