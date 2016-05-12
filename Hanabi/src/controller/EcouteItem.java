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
				p.slider2.setEnabled(false);
				p.joueurIA3.setEnabled(false);
				p.slider3.setEnabled(false);
				p.joueurIA4.setEnabled(false);
				p.slider4.setEnabled(false);
				break;
			case 1:
				p.joueurIA3.setEnabled(false);
				p.slider3.setEnabled(false);
				p.joueurIA4.setEnabled(false);
				p.slider4.setEnabled(false);
				p.joueurIA2.setEnabled(true);
				p.slider2.setEnabled(true);
				break;
			case 2:
				p.joueurIA4.setEnabled(false);
				p.slider4.setEnabled(false);
				p.joueurIA2.setEnabled(true);
				p.slider2.setEnabled(true);
				p.joueurIA3.setEnabled(true);
				p.slider3.setEnabled(true);
				break;
			case 3:
				p.joueurIA4.setEnabled(true);
				p.slider4.setEnabled(true);
				p.joueurIA2.setEnabled(true);
				p.slider2.setEnabled(true);
				p.joueurIA3.setEnabled(true);
				p.slider3.setEnabled(true);
				break;
			}
		}
		else if(e.getSource() == p.joueurIA1){
			if(p.joueurIA1.getSelectedIndex()==3){
				p.slider1.setVisible(true);
			}
			else{
				p.slider1.setVisible(false);
			}
		}
		else if(e.getSource() == p.joueurIA2){
			if(p.joueurIA2.getSelectedIndex()==3){
				p.slider2.setVisible(true);
			}
			else{
				p.slider2.setVisible(false);
			}
		}
		else if(e.getSource() == p.joueurIA3){
			if(p.joueurIA3.getSelectedIndex()==3){
				p.slider3.setVisible(true);
			}
			else{
				p.slider3.setVisible(false);
			}
		}
		else if(e.getSource() == p.joueurIA4){
			if(p.joueurIA4.getSelectedIndex()==3){
				p.slider4.setVisible(true);
			}
			else{
				p.slider4.setVisible(false);
			}
		}
	}


}
