package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Table extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g)
	{
		g.clearRect(0,0,this.getWidth(),this.getHeight());
		g.drawImage(new ImageIcon("ressources/table.png").getImage(), 0, 0, 692, 365, this);
	}
}
