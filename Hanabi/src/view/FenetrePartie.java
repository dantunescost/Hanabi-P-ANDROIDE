package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.AdditionMainPleineException;
import model.Carte;
import model.Couleur.CardColor;
import model.DummyJoueurIA;
import model.EnleverCarteInexistanteException;
import model.Joueur;
import model.JoueurHumain;
import model.Partie;
import model.PiocheVideException;

public class FenetrePartie extends JFrame{
	private static final long serialVersionUID = 2656325461540137440L;
	private Table table;
	private Partie partie; 
	protected int tableWidth = 800;
	protected int tableHeight = 400;
	public static String R = System.getProperty("user.dir")+"/Hanabi/ressources/";

	public FenetrePartie(Partie p){
		super("Hanabi");
		this.partie = p;
		this.setSize(1000, 600);
		this.setMinimumSize(new Dimension(1000,650));
		this.setResizable(true);
		this.table = new Table();
		
		JPanel bg = new JPanel();
		bg.setLayout(new FlowLayout());
		bg.add(table);
		this.setContentPane(bg);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void afficherLesJetons(Graphics g){
		Image jeton = new ImageIcon("ressources/Jeton_bleu_recto.png").getImage();
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth * 75)/100;
		int startY = (this.getHeight() - this.tableHeight) /2 + this.tableHeight/4;
		//affichage des jetons d'indice
		for(int i=0;i<this.partie.getJetonIndice();i++){
			g.drawImage(jeton, startX+(i%3)*25, startY+(i/3)*30, 20, 20, this);
		}
		jeton = new ImageIcon("ressources/Jeton_rouge_recto.png").getImage();
		int i;
		//affichage des jetons eclair "utilises"
		for(i=0;i<this.partie.getJetonEclair();i++){
			g.drawImage(jeton, startX+i*25, startY+3*30, 20, 20, this);
		}
		jeton = new ImageIcon("ressources/Jeton_rouge_verso.png").getImage();
		//affichage des jetons eclair "non-utilises", marge d'erreur
		for(int j=i;j<3-this.partie.getJetonEclair();j++){
			g.drawImage(jeton, startX+j*25, startY+3*30, 20, 20, this);
		}
	}
	
	public void afficherLeDeck(Graphics g){
		Image deck = new ImageIcon("ressources/deck.png").getImage();
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth)/8;
		int startY = (this.getHeight() - this.tableHeight) /2 + this.tableHeight/5;
		g.drawImage(deck, startX, startY, 100, 150, this);
		Font police = new Font("Times",Font.PLAIN,40);
		g.setColor(Color.white);
		g.setFont(police);
		g.drawString(Integer.toString(this.partie.getPioche().size()),startX+20, startY+90);
	}
	
	public void afficherBoutonsIndices(Graphics g){
		g.setColor(Color.red);
		g.fillOval(20, this.getHeight()-50, 30, 30);
		g.setColor(Color.blue);
		g.fillOval(65, this.getHeight()-50, 30, 30);
		g.setColor(Color.green);
		g.fillOval(110, this.getHeight()-50, 30, 30);
		g.setColor(Color.yellow);
		g.fillOval(155, this.getHeight()-50, 30, 30);
		g.setColor(Color.white);
		g.fillOval(200, this.getHeight()-50, 30, 30);
		if(this.partie.isMulticolor()){
			g.setColor(Color.MAGENTA);
			g.fillOval(245, this.getHeight()-75, 30, 30);
			g.setColor(Color.black);
			g.drawArc(245, this.getHeight()-75, 30, 30,0,360);
		}
		for(int i=1;i<=5;i++){
			g.setColor(Color.white);
			g.fillOval(20+(i-1)*45, this.getHeight()-100, 30, 30);
			g.setColor(Color.black);
			Font police = new Font("Arial",Font.PLAIN,20);
			g.setFont(police);
			g.drawString(Integer.toString(i), 30+(i-1)*45, 22+this.getHeight()-100);
		}
		g.setColor(Color.black);
		for(int i=0;i<5;i++){
			g.drawArc(20+i*45, this.getHeight()-100, 30, 30, 0, 360);
			g.drawArc(20+i*45, this.getHeight()-50, 30, 30, 0, 360);
		}
	}
	
	public void afficherBoutonsJouerCoup(Graphics g){
		int startX = (this.getWidth()/2)-205;
		int startY = (this.getHeight())-80;
		g.drawImage(new ImageIcon("ressources/indice.png").getImage(), startX, startY, 99, 40, this);
		g.drawImage(new ImageIcon("ressources/jouer.png").getImage(), startX+109, startY, 151, 40, this);
		g.drawImage(new ImageIcon("ressources/defausser.png").getImage(), startX+270, startY, 140, 40, this);
	}
	
	public void paint(Graphics g){

		//super.paint(g);
		g.clearRect(0,0,this.getWidth(),this.getHeight());
		//background
		g.drawImage(new ImageIcon(R+"wood.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		//draw table
		this.table.paintTable(g, this);
		//draw hand
		AfficherMains a = new AfficherMains(this);
		try {
			a.afficherMain(g);
		} catch (EnleverCarteInexistanteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//draw hint chips
		afficherLesJetons(g);
		//draw deck
		afficherLeDeck(g);
		//draw hint buttons
		afficherBoutonsIndices(g);
		//draw cards already played
		table.afficherCartesJouees(g, this);
		//draw pile cartes defaussées
		table.afficherPileDefausse(g,this);
		//draw buttons
		afficherBoutonsJouerCoup(g);
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
		Partie game = new Partie(3,8,true);
		Joueur[] joue = new Joueur[3];
	    joue[0] = new JoueurHumain("Holmes", game, 0);
	    joue[1] = new DummyJoueurIA("Watson", game, 1);
	    joue[2] = new DummyJoueurIA("Moriaty", game, 2);
	    try {
			game.initPartie(joue);
		} catch (AdditionMainPleineException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (PiocheVideException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    game.getCartesJouees().get(CardColor.BLEU).add(new Carte(CardColor.BLEU,1));
	    game.getCartesJouees().get(CardColor.ROUGE).add(new Carte(CardColor.ROUGE,1));
	    game.getCartesJouees().get(CardColor.BLANC).add(new Carte(CardColor.BLANC,1));
	    game.getCartesJouees().get(CardColor.VERT).add(new Carte(CardColor.VERT,1));
	    game.getCartesJouees().get(CardColor.JAUNE).add(new Carte(CardColor.JAUNE,1));
	    game.getCartesJouees().get(CardColor.MULTI).add(new Carte(CardColor.MULTI,1));
	    new FenetrePartie(game);
	}
}
