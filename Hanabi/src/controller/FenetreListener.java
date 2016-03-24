package controller;

import view.FenetrePartie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Carte;

public class FenetreListener extends MouseAdapter {
	private static final long serialVersionUID = -6040694668648449482L;
	FenetrePartie fen;
	public static String R = System.getProperty("user.dir");


	public FenetreListener(FenetrePartie fen) {
		this.fen = fen;

		if(System.getProperty("os.name").equals("Mac OS X")){
			R += "/Hanabi";
		}
		R += "/ressources/";
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		int karteH = fen.getTableHeight()/4;
		int karteW =(int)((float)karteH*0.645);


		// If clicked on cartes défaussées
		if ((x>=(fen.getWidth()/2 - fen.getTableWidth()/2)/2-karteW/2 && x<= (fen.getWidth()/2 - fen.getTableWidth()/2)/2-karteW/2 + karteW) && (y<=(fen.getHeight()/2)-karteH/2+karteH && y>=(fen.getHeight()/2)-karteH/2)) {
			System.out.println("IT WORKS");

			JOptionPane optionPane = new JOptionPane(){
				public void paint(Graphics g){
					super.paint(g);
					int i=0;
					for (Carte c : fen.getPartie().getDefausse()){
						Image carte = new ImageIcon(R+c.getCardName()).getImage();
						//g.drawImage(carte, 0+karteW*i, 0+karteH*i, karteW, karteH,this);
						i++;
					}
				}
			};
			optionPane.setMinimumSize(new Dimension(800,550));
			optionPane.setVisible(true);

		}
		else{
			System.out.println("NOPE");
		}

	}
}