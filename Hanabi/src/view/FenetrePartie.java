package view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.AdditionMainPleineException;
import model.EnleverCarteInexistanteException;
import model.Main;
import model.Partie;
import model.PiocheVideException;

public class FenetrePartie extends JFrame{
	private static final long serialVersionUID = 2656325461540137440L;
	private Table table;
	private Partie partie; 
	private int tableWidth = 692;
	private int tableHeight = 365;

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
	
	public void showHandCenterTop(Graphics g, Main main) throws EnleverCarteInexistanteException{
		int karteH = this.tableHeight/4;
		int karteW =(int)( (float)karteH*0.645);
		int startX = (this.getWidth() - this.tableWidth) /2 + this.tableWidth/2;
		if(main.getNbCartes()==5){
			startX -= 25;
		}
		int startY = (this.getHeight() - this.tableHeight) /2;
		for(int i=0;i<main.getNbCartes();i++){
			Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
			g.drawImage(karte, startX-karteW+i*25, startY-(karteH/3)*2, karteW, karteH, this);
		}
	}
	
	public void afficherLesJetons(Graphics g){
		Image jeton = new ImageIcon("ressources/Jeton_bleu_recto.png").getImage();
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth * 7)/10;
		int startY = (this.getHeight() - this.tableHeight) /2 + this.tableHeight/5;
		for(int i=0;i<this.partie.getJetonIndice();i++){
			g.drawImage(jeton, startX+(i%3)*25, startY+(i/3)*30, 20, 20, this);
		}
		jeton = new ImageIcon("ressources/Jeton_rouge_recto.png").getImage();
		int i;
		for(i=0;i<this.partie.getJetonEclair();i++){
			g.drawImage(jeton, startX+i*25, startY+3*30, 20, 20, this);
		}
		jeton = new ImageIcon("ressources/Jeton_rouge_verso.png").getImage();
		for(int j=i;j<3-this.partie.getJetonEclair();j++){
			g.drawImage(jeton, startX+j*25, startY+3*30, 20, 20, this);
		}
	}
	
	public void afficherLeDeck(Graphics g){
		Image deck = new ImageIcon("ressources/deck.png").getImage();
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth)/7;
		int startY = (this.getHeight() - this.tableHeight) /2 + this.tableHeight/5;
		g.drawImage(deck, startX, startY, 100, 150, this);
	}
	
	public void paint(Graphics g){
		//super.paint(g);
		g.clearRect(0,0,this.getWidth(),this.getHeight());
		//background
		g.drawImage(new ImageIcon("ressources/wood.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		//draw table
		this.table.paintTable(g, this);
		//draw hand
		try {
			showHandCenterTop(g,this.partie.getJoueurs()[1].getMain());
		} catch (EnleverCarteInexistanteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//draw hint chips
		afficherLesJetons(g);
		//draw deck
		afficherLeDeck(g);
	}
	
	public Partie getPartie() {
		return partie;
	}

	public int getTableWidth() {
		return tableWidth;
	}

	public int getTableHeight() {
		return tableHeight;
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
