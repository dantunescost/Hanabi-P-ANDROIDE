package controller;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.EpistemicJoueurIA;
import model.JoueurHumain;
import model.JoueurIA;
import model.Partie;
import view.FenetrePartie;
import view.Parametres;

public class MenuListener implements ActionListener {
	private JFrame fen;
	
	public MenuListener(JFrame fen){
		this.fen = fen;
	}
	
	public void actionPerformed(ActionEvent arg0) {

		String command = arg0.getActionCommand();
		
		if(command.equals("Quitter")){
			new JOptionPane();
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter le jeu?", "Quitter le jeu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if(option ==JOptionPane.YES_OPTION)
				System.exit(0);
		}else if(command.equals("Enregistrer Partie")){
			((FenetrePartie) fen).saveToFile();
		}else if(command.equals("Charger Partie")){
			Partie p = loadFromFile();
			if(p!=null){
				if(fen.getClass().equals(FenetrePartie.class)){
					if(p.getJoueurs()[0] instanceof EpistemicJoueurIA) {
						JoueurHumain chal = new JoueurHumain("Challenger", p, 0);
						p.getJoueurs()[0] = chal;
					}
					((FenetrePartie)fen).setPartie(p);
					if(p.getaQuiLeTour()!=0){
						for(int i=((FenetrePartie)fen).getPartie().getaQuiLeTour();i<((FenetrePartie)fen).getPartie().getNbJoueurs();i++){
							if(!((FenetrePartie)fen).getPartie().getFinPartie())
							{
								JoueurIA player = (JoueurIA) ((FenetrePartie)fen).getPartie().getJoueurs()[i];
								if( (player.getId() == ((FenetrePartie)fen).getPartie().getDernierJoueur()) && (((FenetrePartie)fen).getPartie().getDernierTour()) ){
									((FenetrePartie)fen).getPartie().finirPartie();
									System.out.println("Partie finie 4, id_ia : " + player.getId());
								}
								player.jouerCoup();
								try {
									Thread.sleep(750);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								((FenetrePartie)fen).update(((FenetrePartie)fen).getGraphics());
							}
						}
					}
				}else{
					fen.dispose();
					fen = new FenetrePartie(p);
					if(p.getaQuiLeTour()!=0){
						for(int i=((FenetrePartie)fen).getPartie().getaQuiLeTour();i<((FenetrePartie)fen).getPartie().getNbJoueurs();i++){
							if(!((FenetrePartie)fen).getPartie().getFinPartie())
							{
								JoueurIA player = (JoueurIA) ((FenetrePartie)fen).getPartie().getJoueurs()[i];
								if( (player.getId() == ((FenetrePartie)fen).getPartie().getDernierJoueur()) && (((FenetrePartie)fen).getPartie().getDernierTour()) ){
									((FenetrePartie)fen).getPartie().finirPartie();
									System.out.println("Partie finie 4, id_ia : " + player.getId());
								}
								player.jouerCoup();
								try {
									Thread.sleep(750);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								((FenetrePartie)fen).update(((FenetrePartie)fen).getGraphics());
							}
						}
					}
				}
			}
		} else if(command.equals("Nouvelle Partie")){
			if(this.fen.getClass().equals(FenetrePartie.class)){
				new Parametres((FenetrePartie)this.fen);
				this.fen.setVisible(false);
			}
			else{
				new Parametres();
				this.fen.dispose();
			}
		}
		if(fen != null){
			fen.repaint();
		}
	}
	
	public Partie loadFromFile(){
		Partie p = null;
		FileDialog dialog = new FileDialog(fen,"Charger Partie",FileDialog.LOAD);
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
		return p;
	}

}
