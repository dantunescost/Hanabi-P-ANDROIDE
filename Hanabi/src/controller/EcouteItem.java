package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.Parametres;

public class EcouteItem implements ItemListener {
	Parametres p;
	
	public EcouteItem(Parametres p){
		this.p = p;
	}

	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == p.nbJoueurs){
			switch(p.nbJoueurs.getSelectedIndex()){
			case 0:
				p.joueurIA2.setEnabled(false);
				p.joueurIA3.setEnabled(false);
				p.joueurIA4.setEnabled(false);
				break;
			case 1:
				p.joueurIA3.setEnabled(false);
				p.joueurIA4.setEnabled(false);
				p.joueurIA2.setEnabled(true);
				break;
			case 2:
				p.joueurIA4.setEnabled(false);
				p.joueurIA2.setEnabled(true);
				p.joueurIA3.setEnabled(true);
				break;
			case 3:
				p.joueurIA4.setEnabled(true);
				p.joueurIA2.setEnabled(true);
				p.joueurIA3.setEnabled(true);
				break;
			}
		}
	}


}
