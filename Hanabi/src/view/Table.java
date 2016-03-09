package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Table extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paintTable(Graphics g, FenetrePartie fen)
	{
		int width = 692, height = 365;
		int startX = (fen.getWidth() - width) /2;
		int startY = (fen.getHeight() - height) /2;
		g.drawImage(new ImageIcon("ressources/table.png").getImage(), startX, startY, width, height, this);
	}
}
