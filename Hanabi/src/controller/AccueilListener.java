package controller;

import java.awt.FileDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.EpistemicJoueurIA;
import model.JoueurHumain;
import model.JoueurIA;
import model.Partie;
import view.FenetreAccueil;
import view.FenetrePartie;
import view.Parametres;
import view.PartieGagne;

public class AccueilListener extends MouseAdapter {
	FenetreAccueil acc;
	
	public AccueilListener(FenetreAccueil acc){
		this.acc = acc;
	}
	public void mouseClicked(MouseEvent e){
		int x = e.getX();
		int y = e.getY();
		if(x<=acc.getWidth()/2-10 && x>=acc.getWidth()/2-203 && y<= (acc.getHeight()/4)*3+40 && y> (acc.getHeight()/4)*3){
			new Parametres();
			acc.dispose();
		}
		if(x<=acc.getWidth()/2+194 && x>=acc.getWidth()/2+10 && y<= (acc.getHeight()/4)*3+40 && y> (acc.getHeight()/4)*3){
			Partie p = loadFromFile();
			if(p!=null){
				FenetrePartie fen = new FenetrePartie(p);
				if(p.getaQuiLeTour()!=0){
					for(int i=((FenetrePartie)fen).getPartie().getaQuiLeTour();i<((FenetrePartie)fen).getPartie().getNbJoueurs();i++){
						if(!((FenetrePartie)fen).getPartie().getFinPartie())
						{
							JoueurIA player = (JoueurIA) ((FenetrePartie)fen).getPartie().getJoueurs()[i];
							if( (player.getId() == ((FenetrePartie)fen).getPartie().getDernierJoueur()) && (((FenetrePartie)fen).getPartie().getDernierTour()) ){
								((FenetrePartie)fen).getPartie().finirPartie();
								System.out.println("Partie finie 4, id_ia : " + player.getId());
								new PartieGagne(p, fen); // Does this need to be there ?
							}
							player.jouerCoup();
							try {
								Thread.sleep(750);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							((FenetrePartie)fen).update(((FenetrePartie)fen).getGraphics());
						}
					}
				}
				acc.dispose();
			}
		}
	}
	public Partie loadFromFile(){
		Partie p = null;
		FileDialog dialog = new FileDialog(acc,"Charger Partie",FileDialog.LOAD);
		dialog.setVisible(true);
		String dir = dialog.getDirectory();
		String fileName = dialog.getFile();
		String filePath = dir + fileName;
		if(fileName != null && fileName.trim().length() != 0)
		{
			File file = new File(filePath);
			FileInputStream fileStream;
			try {
				fileStream = new FileInputStream(file);
				ObjectInputStream objectStream = new ObjectInputStream(fileStream);
				p = (Partie)objectStream.readObject();
				objectStream.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		if(p != null) {
			if(p.getJoueurs()[0] instanceof EpistemicJoueurIA) {
				JoueurHumain chal = new JoueurHumain("Challenger", p, 0);
				chal.setMain(p.getJoueurs()[0].getMain().clone());
				p.getJoueurs()[0] = chal;
			}
		}
		return p;
	}
}
