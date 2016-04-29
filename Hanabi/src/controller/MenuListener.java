package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		}
		
		if(fen != null){
			fen.repaint();
		}
	}

}
