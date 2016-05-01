package view;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.MenuListener;


public class Menu extends JMenuBar {
	private static final long serialVersionUID = 2401343171737574518L;
	JMenu fichier = new JMenu("Fichier");
	JMenu help = new JMenu("Help");
	JMenuItem quitter = new JMenuItem("Quitter");
	JMenuItem nouvPartie = new JMenuItem("Nouvelle Partie");
	JMenuItem chargPartie = new JMenuItem("Charger Partie");
	JMenuItem savePartie = new JMenuItem("Enregistrer Partie");

	JMenuItem aide = new JMenuItem("Aide");
	JMenuItem propos = new JMenuItem("A propos");

	public Menu(){
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		nouvPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		chargPartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		savePartie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		
		fichier.add(nouvPartie);
		fichier.add(chargPartie);
		fichier.add(savePartie);
		fichier.addSeparator();
		fichier.add(quitter);	

		help.add(aide);
		help.add(propos);
		this.add(fichier);
		this.add(help);
		this.setVisible(true);
	}
	
	public void addListeners(final JFrame fen){
		MenuListener ml = new MenuListener(fen);
		this.quitter.addActionListener(ml);
		this.nouvPartie.addActionListener(ml);
		this.chargPartie.addActionListener(ml);
		this.savePartie.addActionListener(ml);
		this.aide.addActionListener(ml);
		this.propos.addActionListener(ml);
	}
}
