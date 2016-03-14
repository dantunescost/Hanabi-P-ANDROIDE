package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.AdditionMainPleineException;
import model.DummyJoueurIA;
import model.EnleverCarteInexistanteException;
import model.Joueur;
import model.JoueurHumain;
import model.Main;
import model.Partie;
import model.PiocheVideException;

public class FenetrePartie extends JFrame{
	private static final long serialVersionUID = 2656325461540137440L;
	private Table table;
	private Partie partie; 
	protected int tableWidth = 692;
	protected int tableHeight = 365;

	public FenetrePartie(Partie p){
		super("Hanabi");
		this.partie = p;
		this.setSize(1000, 600);
		this.setMinimumSize(new Dimension(1000,600));
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
	
	public void showHandCenterBottom(Graphics g, Main main) throws EnleverCarteInexistanteException{
		int karteH = this.tableHeight/4;
		int karteW =(int)( (float)karteH*0.645);
		int startX = (this.getWidth() - this.tableWidth) /2 + this.tableWidth/2;
		if(main.getNbCartes()==5){
			startX -= (karteW/2)*5;
		}
		int startY = (this.getHeight() - this.tableHeight) /2+ (this.tableHeight/8)*7;
		for(int i=0;i<main.getNbCartes();i++){
			Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
			g.drawImage(karte, startX+i*karteW, startY-(karteH/3)*2, karteW, karteH, this);
		}
	}
	
	public void showHandRightTop(Graphics g, Main main) throws EnleverCarteInexistanteException{
		int karteH = this.tableHeight/4;
		int karteW =(int)( (float)karteH*0.645);
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/10)*7;
		if(main.getNbCartes()==5){
			startX -= 25;
		}
		int startY = (this.getHeight() - this.tableHeight) /2;
		for(int i=0;i<main.getNbCartes();i++){
			Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
			g.drawImage(karte, startX-karteW+i*25, startY-(karteH/3)*2, karteW, karteH, this);
		}
	}
	
	public void showHandLeftTop(Graphics g, Main main) throws EnleverCarteInexistanteException{
		int karteH = this.tableHeight/4;
		int karteW =(int)( (float)karteH*0.645);
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/10)*3;
		if(main.getNbCartes()==5){
			startX -= 25;
		}
		int startY = (this.getHeight() - this.tableHeight) /2;
		for(int i=0;i<main.getNbCartes();i++){
			Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
			g.drawImage(karte, startX-karteW+i*25, startY-(karteH/3)*2, karteW, karteH, this);
		}
	}
	
	public void showHandRightTopCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
		int karteH = this.tableHeight/4;
		int karteW =(int)( (float)karteH*0.645);
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/10)*9;
		int startY = (this.getHeight() - this.tableHeight) /2;
		if(main.getNbCartes()==5){
			startX -= 25;
		}
		for(int i=0;i<main.getNbCartes();i++){
			Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
			g.drawImage(karte, startX-karteW+i*25, startY-(karteH/3)*2+i*20, karteW, karteH, this);
		}
	}
	
	public void showHandLeftTopCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
		int karteH = this.tableHeight/4;
		int karteW =(int)( (float)karteH*0.645);
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/8)*2;
		int startY = (this.getHeight() - this.tableHeight) /2;
		if(main.getNbCartes()==5){
			startX -= 25;
		}
		for(int i=0;i<main.getNbCartes();i++){
			Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
			g.drawImage(karte, startX-karteW-i*25, startY-(karteH/3)*2+i*20, karteW, karteH, this);
		}
	}
	
	public void showHandLeftBottomCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
		int karteH = this.tableHeight/4;
		int karteW =(int)( (float)karteH*0.645);
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/11)*2;
		int startY = (this.getHeight() - this.tableHeight) /2 + (this.tableHeight/8)*7;
		for(int i=0;i<main.getNbCartes();i++){
			Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
			g.drawImage(karte, startX-karteW-i*20, startY-(karteH/3)*2-i*20, karteW, karteH, this);
		}
	}
	
	public void showHandRightBottomCorner(Graphics g, Main main) throws EnleverCarteInexistanteException{
		int karteH = this.tableHeight/4;
		int karteW =(int)( (float)karteH*0.645);
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/11)*10 ;
		int startY = (this.getHeight() - this.tableHeight) /2 + (this.tableHeight/8)*7;
		for(int i=0;i<main.getNbCartes();i++){
			Image karte = new ImageIcon("ressources/"+main.getCarte(i).getCardName()).getImage();
			g.drawImage(karte, startX-karteW+i*20, startY-(karteH/3)*2-i*20, karteW, karteH, this);
		}
	}
	
	public void afficherLesJetons(Graphics g){
		Image jeton = new ImageIcon("ressources/Jeton_bleu_recto.png").getImage();
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth * 7)/10;
		int startY = (this.getHeight() - this.tableHeight) /2 + this.tableHeight/5;
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
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth)/7;
		int startY = (this.getHeight() - this.tableHeight) /2 + this.tableHeight/5;
		g.drawImage(deck, startX, startY, 100, 150, this);
	}
	
	public void afficherBoutonsIndices(Graphics g){
		g.setColor(Color.BLUE);
		g.drawArc(20,this.tableHeight-45,0,360,0,0);
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
			showHandCenterBottom(g,this.partie.getJoueurs()[1].getMain());
			showHandLeftBottomCorner(g,this.partie.getJoueurs()[1].getMain());
			showHandRightBottomCorner(g,this.partie.getJoueurs()[1].getMain());
			showHandLeftTopCorner(g,this.partie.getJoueurs()[1].getMain());
			showHandRightTop(g,this.partie.getJoueurs()[1].getMain());
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
		Joueur[] joue = new Joueur[2];
	    joue[0] = new JoueurHumain("Holmes", 5, game, 0);
	    joue[1] = new DummyJoueurIA("Watson", 5, game, 1);
	    try {
			game.initPartie(joue);
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
