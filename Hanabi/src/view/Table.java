package view;

import controller.FenetreListener;
import model.Carte;
import model.Couleur;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Table extends JPanel {
	private static final long serialVersionUID = 1L;

	public static String R = System.getProperty("user.dir");

	Image table;

	public Table(){
		super();
		if(System.getProperty("os.name").equals("Mac OS X")){
			R += "/Hanabi";
		}
		R += "/ressources/";
		this.table = new ImageIcon(R+"table.png").getImage();
	}

	public void paintTable(Graphics g, FenetrePartie fen)
	{
		int width = fen.getTableWidth(), height = fen.getTableHeight();
		int startX = (fen.getWidth() - width) /2;
		int startY = (fen.getHeight() - height) /2;
		g.drawImage(table, startX, startY, width, height, this);
	}

	public void afficherCartesJouees (Graphics g, FenetrePartie fen) {

		HashMap<Couleur.CardColor, ArrayList<Carte>> cartesJouees = fen.getPartie().getCartesJouees();
		int karteH = fen.tableHeight/4;
		int karteW =(int)((float)karteH*0.645);
		int startX = fen.getWidth()/2 ; // Middle of the window, but moves half a card to the left
		int startY = (fen.getHeight() - fen.tableHeight) /2 + (karteH*6/11); // Top of the table, but moves down to create a margin
		if(!fen.getPartie().isMulticolor()){
			startX -= (karteW/2);
		}
		// Avec 5 colonnes

		if (!fen.getPartie().isMulticolor()) {
			int i = 0;
			for (Carte c : cartesJouees.get(Couleur.CardColor.BLANC)) {
				Image carte = new ImageIcon(R + c.getCardName()).getImage();
				g.drawImage(carte, startX, startY+(karteH)/5*i, karteW, karteH, fen);
				i++;
			}
			i = 0;
			for (Carte c : cartesJouees.get(Couleur.CardColor.BLEU)) {
				Image carte = new ImageIcon(R + c.getCardName()).getImage();
				g.drawImage(carte, startX - karteW, startY+(karteH)/5*i, karteW, karteH, fen);
				i++;
			}
			i = 0;
			for (Carte c : cartesJouees.get(Couleur.CardColor.VERT)) {
				Image carte = new ImageIcon(R + c.getCardName()).getImage();
				g.drawImage(carte, startX - karteW * 2, startY+(karteH)/5*i, karteW, karteH, fen);
				i++;
			}
			i = 0;
			for (Carte c : cartesJouees.get(Couleur.CardColor.ROUGE)) {
				Image carte = new ImageIcon(R + c.getCardName()).getImage();
				g.drawImage(carte, startX + karteW, startY+(karteH)/5*i, karteW, karteH, fen);
				i++;
			}
			i = 0;
			for (Carte c : cartesJouees.get(Couleur.CardColor.JAUNE)) {
				Image carte = new ImageIcon(R + c.getCardName()).getImage();
				g.drawImage(carte, startX + karteW * 2, startY+(karteH)/5*i, karteW, karteH, fen);
				i++;
			}
		}

		// Avec 6 colonnes (cartes multicolores)
		else {
			int i = 0;
			for (Carte c : cartesJouees.get(Couleur.CardColor.BLANC)) {
				Image carte = new ImageIcon(R + c.getCardName()).getImage();
				g.drawImage(carte, startX-karteW, startY+(karteH)/5*i, karteW, karteH, fen);
				i++;
			}
			i = 0;
			for (Carte c : cartesJouees.get(Couleur.CardColor.BLEU)) {
				Image carte = new ImageIcon(R + c.getCardName()).getImage();
				g.drawImage(carte, startX - karteW*2, startY+(karteH)/5*i, karteW, karteH, fen);
				i++;
			}
			i = 0;
			for (Carte c : cartesJouees.get(Couleur.CardColor.VERT)) {
				Image carte = new ImageIcon(R + c.getCardName()).getImage();
				g.drawImage(carte, startX - karteW * 3, startY+(karteH)/5*i, karteW, karteH, fen);
				i++;
			}
			i = 0;
			for (Carte c : cartesJouees.get(Couleur.CardColor.ROUGE)) {
				Image carte = new ImageIcon(R + c.getCardName()).getImage();
				g.drawImage(carte, startX, startY+(karteH)/5*i, karteW, karteH, fen);
				i++;
			}
			i = 0;
			for (Carte c : cartesJouees.get(Couleur.CardColor.JAUNE)) {
				Image carte = new ImageIcon(R + c.getCardName()).getImage();
				g.drawImage(carte, startX + karteW, startY+(karteH)/5*i, karteW, karteH, fen);
				i++;
			}
			i=0;
			for (Carte c : cartesJouees.get(Couleur.CardColor.MULTI)) {
				Image carte = new ImageIcon(R + c.getCardName()).getImage();
				g.drawImage(carte, startX + karteW*2, startY+(karteH)/5*i, karteW, karteH, fen);
				i++;
			}
		}

	}

	public void afficherPileDefausse (Graphics g, FenetrePartie fen) {

		ArrayList<Carte> defausse = fen.getPartie().getDefausse();
		int karteH = fen.tableHeight/4;
		int karteW =(int)((float)karteH*0.645);
		int startX = (fen.getWidth()/2 - fen.getTableWidth()/2)/2-karteW/2;
		int startY = (fen.getHeight()/2)-karteH/2;

		// Afficher derniere carte defaussée
		int carteAffichee = fen.getPartie().getDefausse().size()-1;
		g.setColor(Color.white);
		Font police = new Font("Arial",Font.BOLD,15);
		g.setFont(police);
		g.drawString("Défausse", startX-2, startY-5);
		if(defausse.size() != 0){
			Image carte = new ImageIcon(R + defausse.get(carteAffichee).getCardName()).getImage();
			g.drawImage(carte, startX, startY, karteW, karteH, fen);
		}
		else {
			g.setColor(Color.white);
			g.drawRoundRect(startX,startY,karteW,karteH,1,1);
		}
	}
}
