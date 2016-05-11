package view;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.MouseListener;
import model.EnleverCarteInexistanteException;
import model.Partie;

public class FenetrePartie extends JFrame{
	private static final long serialVersionUID = 2656325461540137440L;
	private Table table;
	private Partie partie; 
	private boolean annuler = false;
	public int tableWidth = 800;
	public int tableHeight = 400;
    public int karteH = this.tableHeight/4;
    public int karteW =(int)( (float)karteH*0.645);
	private AfficherMains a;
	public boolean afficheDef;
	public int playerSelected = 0;
	public static String R = System.getProperty("user.dir");
	private Menu menu = new Menu();


	public FenetrePartie(Partie p){
		super("Hanabi");
		this.partie = p;
		this.setSize(1000, 600);
		this.setMinimumSize(new Dimension(1000,650));
		this.setResizable(true);
		this.afficheDef = false;
		
		if(System.getProperty("os.name").equals("Mac OS X")){
			R += "/Hanabi";
		}
		R += "/ressources/";

		this.a = new AfficherMains(this);
		this.table = new Table();

		this.addMouseListener(new MouseListener(this));
		
		this.menu.addListeners(this);
		this.setJMenuBar(menu);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void afficherLesJetons(Graphics g){
		Image jeton = new ImageIcon(R+"Jeton_bleu_recto.png").getImage();
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth * 75)/100;
		int startY = (this.getHeight() - this.tableHeight) /2 + this.tableHeight/4;
		//affichage des jetons d'indice
		for(int i=0;i<this.partie.getJetonIndice();i++){
			g.drawImage(jeton, startX+(i%3)*25, startY+(i/3)*30, 20, 20, this);
		}
		jeton = new ImageIcon(R+"Jeton_rouge_recto.png").getImage();
		int i;
		//affichage des jetons eclair "utilises"
		for(i=0;i<this.partie.getJetonEclair();i++){
			g.drawImage(jeton, startX+i*25, startY+3*30, 20, 20, this);
		}
		jeton = new ImageIcon(R+"Jeton_rouge_verso.png").getImage();
		//affichage des jetons eclair "non-utilises", marge d'erreur
		for(int j=i;j<3;j++){
			g.drawImage(jeton, startX+j*25, startY+3*30, 20, 20, this);
		}
	}
	
	public void afficherLeDeck(Graphics g){
		Image deck = new ImageIcon(R+"deck.png").getImage();
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
			Image img = new ImageIcon(R+"multicolor.png").getImage();
			g.drawImage(img,245, this.getHeight()-75, 30, 30, this);
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
	
	public void afficherLaDefausse(Graphics g){
		int x = (partie.getDefausse().size()>7)?7*128:partie.getDefausse().size()*128;
		int y = 200 + (partie.getDefausse().size()/7)*65;
		int startX = this.getWidth()/2 - (x/2);
		int startY = this.getHeight()/2 - (y/2);
		Color c = new Color(128,128,128,127);
		g.setColor(c);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(startX-10, startY-10, x+20, y+20);
		for(int i=0; i<this.partie.getDefausse().size(); i++){
			Image img = new ImageIcon(R+this.partie.getDefausse().get(i).getCardName()).getImage();
			g.drawImage(img, startX+(i%7)*128, startY+(i/7)*65, 128, 200, this);
		}
	}
	

	public void afficherBoutonsJouerCoup(Graphics g){
		int startX = (this.getWidth()/2)-205;
		int startY = (this.getHeight())-80;
		if(!this.annuler){

			if (this.getPartie().getJetonIndice()>0) {
				g.drawImage(new ImageIcon(R + "indice.png").getImage(), startX, startY, 99, 40, this);
			}
			g.drawImage(new ImageIcon(R+"jouer.png").getImage(), startX+109, startY, 151, 40, this);
			g.drawImage(new ImageIcon(R+"defausser.png").getImage(), startX+270, startY, 140, 40, this);
		}
		else{
			g.drawImage(new ImageIcon(R+"annuler.png").getImage(), (this.getWidth()/2)-60, startY, 121, 40, this);
		}
	}
	
	public void afficherMainSelectionnee(Graphics g){
		switch(this.playerSelected){
		case 1:
			switch(this.partie.getNbJoueurs()){
			case 2:
				highlightCenterTop(g);
				break;
			case 3:
				highlightLeftTopCorner(g);
				break;
			case 4:
				highlightLeftTopCorner(g);
				break;
			case 5:
				highlightLeftBottomCorner(g);
				break;
			}
			break;
		case 2:
			switch(this.partie.getNbJoueurs()){
			case 3:
				highlightRightTopCorner(g);
				break;
			case 4:
				highlightCenterTop(g);
				break;
			case 5:
				highlightLeftTop(g);
				break;
			}
			break;
		case 3:
			switch(this.partie.getNbJoueurs()){
			case 4:
				highlightRightTopCorner(g);
				break;
			case 5:
				highlightRightTop(g);
				break;
			}
			break;
		case 4:
			highlightRightBottomCorner(g);
			break;
		}
	}
	
	private void highlightRightBottomCorner(Graphics g) {
		int nbCartes = partie.getJoueurs()[this.playerSelected].getMain().getNbCartes();
        int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/11)*10 ;
        int startY = (this.getHeight() - this.tableHeight) /2 + (this.tableHeight/8)*7;
        g.setColor(Color.GREEN);
        g.fillRect(startX-karteW-3, startY-(karteH/3)*2+karteH, 6+karteW, 3);
        g.fillRect(startX-karteW+(nbCartes-1)*20-3, startY-(karteH/3)*2-(nbCartes-1)*20, karteW+6, 3);
        g.fillRect(startX-karteW-3,startY-(karteH/3)*2-3, 3, karteH+6);
        g.fillRect(startX+(nbCartes-1)*20, startY-(karteH/3)*2-(nbCartes-1)*20, 3, karteH+3);
        for(int i=1;i<4;i++){
        	g.drawLine(startX-karteW-3, startY-(karteH/3)*2-i, startX-karteW+(nbCartes-1)*20-i, startY-(karteH/3)*2-(nbCartes-1)*20);
        	g.drawLine(startX+i, startY-(karteH/3)*2+3+karteH, startX+(nbCartes-1)*20+3, startY-(karteH/3)*2+karteH-(nbCartes-1)*20+i);
        }	
	}

	private void highlightLeftBottomCorner(Graphics g) {
		int nbCartes = partie.getJoueurs()[this.playerSelected].getMain().getNbCartes();
        int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/11)*2;
        int startY = (this.getHeight() - this.tableHeight) /2 + (this.tableHeight/8)*7;
        g.setColor(Color.GREEN);
        g.fillRect(startX-karteW-3, startY-(karteH/3)*2+karteH, 6+karteW, 3);
        g.fillRect(startX-karteW-(nbCartes-1)*20-3, startY-(karteH/3)*2-(nbCartes-1)*20, karteW+6, 3);
        g.fillRect(startX,startY-(karteH/3)*2-3, 3, karteH+6);
        g.fillRect(startX-karteW-(nbCartes-1)*20-3, startY-(karteH/3)*2-(nbCartes-1)*20, 3, karteH+3);
        for(int i=1;i<4;i++){
        	g.drawLine(startX+3, startY-(karteH/3)*2-i, startX-(nbCartes-1)*20+i, startY-(karteH/3)*2-(nbCartes-1)*20);
        	g.drawLine(startX-karteW-i, startY-(karteH/3)*2+3+karteH, startX-karteW-(nbCartes-1)*20-3, startY-(karteH/3)*2+karteH-(nbCartes-1)*20+i);
        }			
	}

	private void highlightRightTopCorner(Graphics g) {
		int nbCartes = partie.getJoueurs()[this.playerSelected].getMain().getNbCartes();
        int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/10)*9-25;
        int startY = (this.getHeight() - this.tableHeight) /2;
        g.setColor(Color.GREEN);
        g.fillRect(startX-karteW-3, startY-(karteH/3)*2-3, 6+karteW, 3);
        g.fillRect(startX-karteW+(nbCartes-1)*25-3, startY-(karteH/3)*2+(nbCartes-1)*20+karteH, karteW+6, 3);
        g.fillRect(startX-karteW-3,startY-(karteH/3)*2-3, 3, karteH+6);
        g.fillRect(startX+(nbCartes-1)*25,startY-(karteH/3)*2+(nbCartes-1)*20, 3, karteH+3);
        for(int i=1;i<4;i++){
        	g.drawLine(startX+3, startY-(karteH/3)*2-i, startX+(nbCartes-1)*25+i, startY-(karteH/3)*2+(nbCartes-1)*20);
        	g.drawLine(startX-karteW-i, startY-(karteH/3)*2+3+karteH, startX-karteW+(nbCartes-1)*25-3, startY-(karteH/3)*2+karteH+(nbCartes-1)*20+i);
        }	
	}

	private void highlightLeftTopCorner(Graphics g) {
		int nbCartes = partie.getJoueurs()[this.playerSelected].getMain().getNbCartes();
        int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/8)*2-25;
        int startY = (this.getHeight() - this.tableHeight) /2;
        g.setColor(Color.GREEN);
        g.fillRect(startX-karteW-3, startY-(karteH/3)*2-3, 6+karteW, 3);
        g.fillRect(startX-karteW-(nbCartes-1)*25-3, startY-(karteH/3)*2+(nbCartes-1)*20+karteH, karteW+6, 3);
        g.fillRect(startX,startY-(karteH/3)*2-3, 3, karteH+6);
        g.fillRect(startX-karteW-(nbCartes-1)*25-3,startY-(karteH/3)*2+(nbCartes-1)*20, 3, karteH+3);
        for(int i=1;i<4;i++){
        	g.drawLine(startX-karteW-3, startY-(karteH/3)*2-i, startX-karteW-(nbCartes-1)*25-i, startY-(karteH/3)*2+(nbCartes-1)*20);
        	g.drawLine(startX+i, startY-(karteH/3)*2+3+karteH, startX-(nbCartes-1)*25+3, startY-(karteH/3)*2+karteH+(nbCartes-1)*20+i);
        }
	}

	private void highlightRightTop(Graphics g) {
		int nbCartes = partie.getJoueurs()[this.playerSelected].getMain().getNbCartes();
        int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/10)*7;
        int startY = (this.getHeight() - this.tableHeight) /2;
        g.setColor(Color.GREEN);
        g.fillRect(startX-karteW, startY-(karteH/3)*2-3, (nbCartes-1)*25+karteW, 3);
        g.fillRect(startX-karteW-3, startY-(karteH/3)*2-3, 3, karteH+3);
        g.fillRect(startX+(nbCartes-1)*25, startY-(karteH/3)*2-3, 3, karteH+6);
        g.fillRect(startX-karteW-3, startY-(karteH/3)*2+karteH,(nbCartes-1)*25+karteW+3, 3);		
	}

	private void highlightLeftTop(Graphics g) {
		int nbCartes = partie.getJoueurs()[this.playerSelected].getMain().getNbCartes();
		int startX = (this.getWidth() - this.tableWidth) /2 + (this.tableWidth/10)*3;
        int startY = (this.getHeight() - this.tableHeight) /2;
        g.setColor(Color.GREEN);
        g.fillRect(startX-karteW, startY-(karteH/3)*2-3, (nbCartes-1)*25+karteW, 3);
        g.fillRect(startX-karteW-3, startY-(karteH/3)*2-3, 3, karteH+3);
        g.fillRect(startX+(nbCartes-1)*25, startY-(karteH/3)*2-3, 3, karteH+6);
        g.fillRect(startX-karteW-3, startY-(karteH/3)*2+karteH,(nbCartes-1)*25+karteW+3, 3);		
	}

	private void highlightCenterTop(Graphics g) {
		int nbCartes = partie.getJoueurs()[this.playerSelected].getMain().getNbCartes();
		int startX = (this.getWidth() - tableWidth) /2 + tableWidth/2;
        if(partie.getJoueurs()[this.playerSelected].getMain().getNbCartes()==5){
            startX -= 25;
        }
        int startY = (this.getHeight() - this.tableHeight) /2;
        g.setColor(Color.GREEN);
        g.fillRect(startX-karteW, startY-(karteH/3)*2-3, (nbCartes-1)*25+karteW, 3);
        g.fillRect(startX-karteW-3, startY-(karteH/3)*2-3, 3, karteH+3);
        g.fillRect(startX+(nbCartes-1)*25, startY-(karteH/3)*2-3, 3, karteH+6);
        g.fillRect(startX-karteW-3, startY-(karteH/3)*2+karteH,(nbCartes-1)*25+karteW+3, 3);
	}
	
	public void saveToFile(){
		FileDialog dialog = new FileDialog(this,"Enregistrer Partie",FileDialog.SAVE);
		dialog.setVisible(true);
		String dir = dialog.getDirectory();
		String fileName = dialog.getFile();
		String filePath = dir + fileName;
		if(fileName != null && fileName.trim().length() != 0)
		{
			File file = new File(filePath);
			try{
				FileOutputStream fileStream = new FileOutputStream(file);
				ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

				objectStream.writeObject(this.partie);
				
				objectStream.close();
				fileStream.close();
				
				JOptionPane.showConfirmDialog(this, "Enregistrer avec succes ", "Jeu Hanabi", JOptionPane.DEFAULT_OPTION);
				
			}
			catch(Exception e){
				JOptionPane.showConfirmDialog(this, e.toString()+"\nErreur d'enregistrement", 
						"Jeu Hanabi", JOptionPane.DEFAULT_OPTION);			
			}
		}
		else{
			JOptionPane.showConfirmDialog(this,"Erreur d'enregistrement","Jeu Hanabi",
					JOptionPane.DEFAULT_OPTION);					
		}
	}

	public void paint(Graphics g){
		//super.paint(g);
		g.clearRect(0,0,this.getWidth(),this.getHeight());
		this.paintComponents(g);
		//background
		g.drawImage(new ImageIcon(R+"wood.jpg").getImage(), 0, 20, this.getWidth(), this.getHeight()-20, this);
		//draw table
		this.table.paintTable(g, this);
		//draw hands
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
		//draw defausse
		if(this.afficheDef){
			afficherLaDefausse(g);
		}
		//draw selection of a player's hand
		if(this.playerSelected != 0){
			afficherMainSelectionnee(g);
		}
	}
	
	public Partie getPartie() {
		return partie;
	}
	
	public void setPartie(Partie p) {
		this.partie = p;
	}

	public int getTableWidth() {
		return tableWidth;
	}

	public int getTableHeight() {
		return tableHeight;
	}

	public boolean isAnnuler() {
		return annuler;
	}

	public void setAnnuler(boolean annuler) {
		this.annuler = annuler;
	}
}
