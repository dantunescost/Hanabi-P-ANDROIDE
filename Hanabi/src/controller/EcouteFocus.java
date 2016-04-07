package controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class EcouteFocus implements FocusListener {

	public void focusGained(FocusEvent e) {
		JTextField text = (JTextField)e.getSource();
		text.setForeground(Color.BLACK);
		if(text.getText().contains(" "))
			text.setText("");
	}

	public void focusLost(FocusEvent e) {
		JTextField text = (JTextField)e.getSource();
		if(text.getText().isEmpty() || text.getText().contains(" ") ){
			text.setForeground(Color.GRAY);
			if(text.getText().isEmpty())
				text.setText("Entrez un ID!");
			else if(text.getText().contains(" "))
				text.setText("ID sans espace s.v.p.");
		}
	}

}
